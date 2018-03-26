// ussdtest.cpp : Defines the entry point for the console application.
//

#include "StdAfx.h"
#include "ussd.h"
#include <stdio.h>

CUSSD* ussd; //instancia maestra
int counter = 0;

void ussdService(int dialogId,char* shortCode,char* phoneNumber){
	//printf("Service\r\n");
	counter++;
	ussd->ussd_end(dialogId,"bye"\
		"test012345678900123456789001234567890012345678900123456789001234567890"\
		"test012345678900123456789001234567890012345678900123456789001234567890");
}
void ussdResponse(int dialogId,char* str){
	printf("Response\r\n");
}
void ussdEnd(int dialogId){
	printf("End\r\n");
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

	if(!ussd->connectgw("192.168.1.210",5404,0,0)){
		printf("Error connecting");
		return 0;
	}

	while(1){
		//do some statistics
		printf("%d\r\n",counter);
		counter = 0;
		Sleep(1000);
	}
	return 0;
}
