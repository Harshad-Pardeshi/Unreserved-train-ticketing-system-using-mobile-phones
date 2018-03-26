// stdafx.cpp : source file that includes just the standard includes
//	ussdtest.pch will be the pre-compiled header
//	stdafx.obj will contain the pre-compiled type information

#include "StdAfx.h"

// TODO: reference any additional headers you need in STDAFX.H
// and not in this file
#ifndef _WIN32
void Sleep(int i){
	usleep((i*1000));
}
#endif
