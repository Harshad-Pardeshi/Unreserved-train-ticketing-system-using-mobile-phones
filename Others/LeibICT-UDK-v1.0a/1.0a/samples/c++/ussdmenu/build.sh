#!/bin/sh

rm -f -v ussdtest

FLAGS='-O3'
WARNS='-Wall -Wno-comments -Wno-parentheses -Wno-return-type -Wno-unused-variable -Wno-uninitialized -Wno-format -Wno-conversion -Wno-char-subscripts -Wno-deprecated'

LC_ALL=C g++ $FLAGS $WARNS -o ussdtest \
	ussd.cpp \
	StdAfx.cpp \
	ussdtest.cpp -ldl
