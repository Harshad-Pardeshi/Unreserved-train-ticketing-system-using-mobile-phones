

#include "StdAfx.h"
#include <stdio.h>
#include "ussd.h"

#define STDLOG printf

#ifdef _WIN32
#define DLLSYM GetProcAddress
#define DLLUNLOAD FreeLibrary
#define DLLERROR GetLastError
#else
#include <dlfcn.h>
#define DLLSYM dlsym
#define DLLUNLOAD dlclose
#define DLLERROR dlerror
#endif

CUSSD::CUSSD(){
	dll_pong		=0;
	dll_ussd_end		=0;
	dll_ussd_request	=0;
	dll_connectgw		=0;
	dll_initialize		=0;

#ifdef _WIN32
	ussdDll = LoadLibrary("ussd.dll");
#else
	ussdDll = dlopen("libussd.so.1.0", RTLD_LAZY);
#endif

	if(!ussdDll){
		STDLOG("ERROR ussd library not found %s",DLLERROR());
		//AfxMessageBox("xml parser.dll not found",MB_SYSTEMMODAL|MB_ICONSTOP);
		return;
	}
	dll_pong			= (pongFn)				DLLSYM(ussdDll,"pong");
	dll_ussd_end		= (ussd_endFn)			DLLSYM(ussdDll,"ussd_end");
	dll_ussd_request	= (ussd_requestFn)		DLLSYM(ussdDll,"ussd_request");
	dll_initialize		= (initializeFn)		DLLSYM(ussdDll,"initialize");
	dll_connectgw		= (connectgwFn)			DLLSYM(ussdDll,"connectgw");

	if(	!dll_pong			||
		!dll_ussd_end		||
		!dll_ussd_request	||
		!dll_initialize		||
		!dll_connectgw		){
		STDLOG("ERROR ussd library bad link %d:%d:%d:%d:%d",dll_pong,dll_ussd_end,dll_ussd_request,dll_initialize,dll_connectgw);
		DLLUNLOAD(ussdDll);
		ussdDll = NULL;
		return;
	}
}

CUSSD::~CUSSD(){
	if(ussdDll)		DLLUNLOAD(ussdDll);
}

int CUSSD::pong(){
	return dll_pong();
}
int CUSSD::ussd_end(int dialogId, char* text){
	return dll_ussd_end(dialogId,text);
}
int CUSSD::ussd_request(int dialogId,char* prompt){
	return dll_ussd_request(dialogId,prompt);
}
int CUSSD::connectgw(char* addr, int port, int jrx, int jtx){
	return dll_connectgw(addr,port,jrx,jtx);
}
int CUSSD::initialize(USSDCallbacks* callbacks){
	if(!ussdDll){ return 0; }
	return dll_initialize(callbacks);
}
