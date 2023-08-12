package com.example.targetdate.util;

import android.util.Log;


public class Logger {

    private static boolean isLogEnable = true;

    public static void d(String tag, String msg) {
        if (isLogEnable && tag != null && msg != null)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (isLogEnable && tag != null && msg != null)
            Log.e(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (isLogEnable && tag != null && msg != null)
            Log.i(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (isLogEnable && tag != null && msg != null)
            Log.v(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (isLogEnable && tag != null && msg != null)
                Log.w(tag, msg);
    }

    public static void wtf(String tag, String msg) {
        if (isLogEnable && tag != null && msg != null)
                Log.wtf(tag, msg);
    }

}
