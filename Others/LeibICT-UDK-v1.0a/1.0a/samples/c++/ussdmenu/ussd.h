#ifndef USSD_H
#define USSD_H

#ifdef _WIN32
#include <windows.h>
#endif

typedef struct _USSDCallbacks{
	void (*ussdService)(int dialogId,char* shortCode,char* phoneNumber);
	void (*ussdResponse)(int dialogId,char* str);
	void (*ussdEnd)(int dialogId);
	void (*disconnected)();
	void (*connected)();
	void (*ping)();
} USSDCallbacks;

/*
	Programador: Alejandro Leib
*/
typedef int (*pongFn)();
typedef int (*ussd_endFn)(int dialogId, char* text);
typedef int (*ussd_requestFn)(int dialogId,char* prompt);
typedef int (*connectgwFn)(char* addr, int port, int jrx, int jtx);
typedef int (*initializeFn)(USSDCallbacks* callbacks);

class CUSSD
{
public:
	CUSSD();
	~CUSSD();

	int pong();
	int ussd_end(int dialogId, char* text);
	int ussd_request(int dialogId,char* prompt);
	int connectgw(char* addr, int port, int jrx, int jtx);
	int initialize(USSDCallbacks* callbacks);

private:
	pongFn 				dll_pong;
	ussd_endFn			dll_ussd_end;
	ussd_requestFn			dll_ussd_request;
	connectgwFn			dll_connectgw;
	initializeFn			dll_initialize;

#ifdef _WIN32
	HINSTANCE ussdDll;
#else
	void*     ussdDll;
#endif
};

#endif // USSD_H
