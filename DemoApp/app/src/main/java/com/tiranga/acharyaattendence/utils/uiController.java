package com.tiranga.acharyaattendence.utils;

import android.app.Activity;
import android.content.Intent;

public class uiController {

    public static void gotoActivity(Activity activity, Class<?> activityClass,boolean isFinish) {
        sendIntent(activity, new Intent(activity, activityClass),  isFinish);
    }

    public static void gotoIntent(Activity activity, Intent intent,  boolean isFinish) {
        sendIntent(activity, intent,  isFinish);
    }

    public static void sendIntent(Activity activity, Intent intent,boolean isFinish) {
        activity.startActivity(intent);
        if (isFinish) activity.finish();
    }
}