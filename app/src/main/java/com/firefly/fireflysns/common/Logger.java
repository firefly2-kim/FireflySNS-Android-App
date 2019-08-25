package com.firefly.fireflysns.common;

import android.util.Log;

/**
 * @author firefly2.kim
 * @since 19. 8. 24
 */
public class Logger {

    private static final String APP_TAG = "firefly-sns";

    public static void d(String tag, String msg) {
        Log.d(APP_TAG, "[" + tag + "]" + msg);
    }

    public static void e(String tag, String msg) {
        Log.e(APP_TAG, "[" + tag + "]" + msg);
    }
}
