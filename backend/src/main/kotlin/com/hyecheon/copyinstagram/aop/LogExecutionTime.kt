package com.hyecheon.copyinstagram.aop

@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class LogExecutionTime 