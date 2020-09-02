package com.example.java_day_03;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Time: 2020/9/2 0002 9:35
 * Author: Sch
 * Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@EventType(listenerType = View.OnClickListener.class , setListener = "setOnClickListener")
public @interface OnClick {
    int[] value();
}
