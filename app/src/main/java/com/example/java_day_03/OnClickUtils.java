package com.example.java_day_03;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Time: 2020/9/2 0002 9:44
 * Author: Sch
 * Description:
 */
public class OnClickUtils {

    public static String TAG = OnClickUtils.class.getSimpleName();

    public static void init(final Activity activity) {

        //获取操作对象实例
        Class<? extends Activity> activityClass = activity.getClass();
        //获取实例中所有得方法
        Method[] activityMethods = activityClass.getDeclaredMethods();

        //遍历方法
        for (Method method : activityMethods) {
            //声明方法上得注解
            Annotation[] annotations = method.getAnnotations();
            //遍历方法注解
            for (Annotation annotation : annotations) {
                //获取注解类型
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType.isAnnotationPresent(EventType.class)){
                    //设置获取要添加得事件
                    EventType eventType = annotationType.getAnnotation(EventType.class);
                    // OnClickListener OnLongClickListener
                    Class listenerType = eventType.listenerType();
                    //set方法名
                    String setListener = eventType.setListener();
                    try {

                        //不需要区分注解 onClick onLongClick
                        Method value = annotationType.getDeclaredMethod("value");
                        int[] viewIds = (int[]) value.invoke(annotation);
                        method.setAccessible(true);

                        ListenerInvocationHandler<Activity> invocationHandler =
                                new ListenerInvocationHandler(activity,method);
                        Object obj = Proxy.newProxyInstance(listenerType.getClassLoader(),
                                new Class[]{listenerType},invocationHandler);

                        //遍历赋值
                        for (int id : viewIds) {
                            View view = activity.findViewById(id);
                            //获取到对应打方法
                            // 获得setOnClickListener方法，参数为OnClickListener
                            // 获得setOnLongClickListener，则参数为OnLongClickListener
                            Method setter = view.getClass().getMethod(setListener, listenerType);
                            //再塞入
                            setter.invoke(view,obj);
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

        }
    }

    /**
     * 动态代理回调
     *
     * @param <T> activity
     */
    public static class ListenerInvocationHandler<T> implements InvocationHandler{

        T target;
        Method method;

        public ListenerInvocationHandler(T target , Method method) {
            this.target = target;
            this.method = method;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            return this.method.invoke(target,objects);
        }
    }




}
