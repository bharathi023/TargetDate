package com.example.targetdate.util;


public class Helper {
    public static boolean hasInArray(String[] values, String value) {
        for (String s : values) {
            if (s.equals(value))
                return true;
        }
        return false;
    }
}
