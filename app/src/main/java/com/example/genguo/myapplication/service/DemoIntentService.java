package com.example.genguo.myapplication.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.genguo.myapplication.Constant;

/**
 * Created by genguo on 3/4/16.
 */
public class DemoIntentService extends IntentService{
    public DemoIntentService(){
        super("DemoIntentService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("wgg","onHandleIntent");
        SystemClock.sleep(5000);
        Intent localIntent = new Intent(Constant.BROADCAST_ACTION).putExtra(Constant.EXTENDED_DATA_STATUS,"kingo");
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}
