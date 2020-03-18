package com.yulin.library.util;

public class ClassUtils {

    public static boolean isPresent(String className){
        try {
            Thread.currentThread().getContextClassLoader().loadClass(className);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

}
