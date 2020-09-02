package com.example.java_day_03;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Time: 2020/9/2 0002 10:28
 * Author: Sch
 * Description: 区分实现得是那个接口
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventType {

    Class listenerType();

    String setListener();



}
