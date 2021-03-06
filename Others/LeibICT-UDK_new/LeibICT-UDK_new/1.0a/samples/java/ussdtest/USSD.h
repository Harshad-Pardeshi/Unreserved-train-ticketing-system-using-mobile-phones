/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_leibict_ussd_USSD */

#ifndef _Included_com_leibict_ussd_USSD
#define _Included_com_leibict_ussd_USSD
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_leibict_ussd_USSD
 * Method:    initialize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_leibict_ussd_USSD_initialize
  (JNIEnv *, jclass);

/*
 * Class:     com_leibict_ussd_USSD
 * Method:    connect
 * Signature: (Ljava/lang/String;I)I
 */
JNIEXPORT jint JNICALL Java_com_leibict_ussd_USSD_connect
  (JNIEnv *, jclass, jstring, jint);

/*
 * Class:     com_leibict_ussd_USSD
 * Method:    ussd_request
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_leibict_ussd_USSD_ussd_1request
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     com_leibict_ussd_USSD
 * Method:    ussd_end
 * Signature: (ILjava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_leibict_ussd_USSD_ussd_1end
  (JNIEnv *, jclass, jint, jstring);

/*
 * Class:     com_leibict_ussd_USSD
 * Method:    pong
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_leibict_ussd_USSD_pong
  (JNIEnv *, jclass);

#ifdef __cplusplus
}
#endif
#endif
