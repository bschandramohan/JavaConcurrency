package com.bschandramohan.learn.util;

import java.util.Calendar;

public class Utils {

    public static void logMessage(String message) {
        System.out.printf("[%d] - %s %n", Calendar.getInstance().getTimeInMillis(), message);
    }
}
