// ussdtest.cpp : Defines the entry point for the console application.
//

#include "StdAfx.h"
#include "ussd.h"
#include <stdio.h>

CUSSD* ussd; //service instance

//state machine
#define IDLE		0
#define FIRST		1
#define TEST		2
#define REPLY		3

typedef struct _dialog{
	int state;
	//here you store all the info needed for the individual dialog
} DIALOG;

#define MAX_DIALOGS 32768
//#define INCOMING_DIALOG_START	32768
#define INCOMING_DIALOG_START	1

DIALOG* dialogs;

char* firstMenu = "First Menu\n"\
				"1. test\n"\
				"2. imput\n"\
				"3. to exit";

void ussdService(int dialogId,char* shortCode,char* phoneNumber){
	printf("Service %d %s %s\r\n",dialogId,shortCode,phoneNumber);
	int idx = dialogId - INCOMING_DIALOG_START;
	dialogs[idx].state = FIRST;
	ussd->ussd_request(dialogId,
		firstMenu);
}
void ussdResponse(int dialogId,char* str){
	printf("Response %d %s\r\n",dialogId,str);
	int idx = dialogId - INCOMING_DIALOG_START;
	switch(dialogs[idx].state){
	case FIRST:{
		if(!strcmp(str,"3")){
			dialogs[idx].state = IDLE;
			ussd->ussd_end(dialogId,"good luck");
		}
		else if(!strcmp(str,"1")){
			dialogs[idx].state = TEST;
			ussd->ussd_request(dialogId,
				"You entered test!\n"\
				"1. go back\n"\
				"2. to exit");
		}
		else if(!strcmp(str,"2")){
			dialogs[idx].state = REPLY;
			ussd->ussd_request(dialogId,
				"Enter something");
		}
		else{
			dialogs[idx].state = FIRST;
			ussd->ussd_request(dialogId,
				firstMenu);
		}
		break;
				   }
	case TEST:{
		if(!strcmp(str,"2")){
			dialogs[idx].state = IDLE;
			ussd->ussd_end(dialogId,"good luck");
		}
		else{
			dialogs[idx].state = FIRST;
			ussd->ussd_request(dialogId,
				firstMenu);
		}
		break;
			  }
	case REPLY:{
		char reply[128];
		sprintf(reply,"You entered \"%s\"",str);
		dialogs[idx].state = IDLE;
		ussd->ussd_end(dialogId,reply);
		break;
			   }
	}
}
void ussdEnd(int dialogId){
	printf("End %d\r\n",dialogId);
	int idx = dialogId - INCOMING_DIALOG_START;
	dialogs[idx].state = IDLE;
}

void disconnected(){
	printf("Disconnected\r\n");
}
void connected(){
	printf("Connected\r\n");
}
void ping(){
	ussd->pong();
}

USSDCallbacks callbacks;

int main(int argc, char* argv[])
{
	int i;
	dialogs = (DIALOG*)malloc(sizeof(DIALOG) * MAX_DIALOGS);
	for(i=0;i<MAX_DIALOGS;i++){
		dialogs[i].state = IDLE;
	}

	callbacks.ussdService = ussdService;
	callbacks.connected = connected;
	callbacks.disconnected = disconnected;
	callbacks.ping = ping;
	callbacks.ussdResponse = ussdResponse;
	callbacks.ussdEnd = ussdEnd;

	ussd = new CUSSD();
	//printf("\r\n\r\n");
	if(!ussd->initialize(&callbacks)){
		printf("Error initializing");
		return 0;
	}

	if(!ussd->connectgw("localhost",5454,0,0)){
		printf("Error connecting");
		return 0;
	}

	while(1){
		//do some statistics
		Sleep(1000);
	}
	return 0;
}
