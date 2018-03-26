@SET JDKPATH="C:\Program Files\Java\jdk1.6.0\bin"

%JDKPATH%\javah -o USSD.h  -classpath "build\classes" -jni com.leibict.ussd.USSD.class
@pause