package com.example.day8lianxice1;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    private String desc;
    public MyReceiver(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        desc = intent.getStringExtra("desc");
        Log.i("TAG",desc);
    }


}
