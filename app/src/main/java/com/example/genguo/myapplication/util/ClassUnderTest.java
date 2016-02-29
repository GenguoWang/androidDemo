package com.example.genguo.myapplication.util;

import android.content.Context;

import com.example.genguo.myapplication.R;

/**
 * Created by genguo on 2/28/16.
 */
public class ClassUnderTest {
    private Context context;
    public ClassUnderTest(Context context) {
        this.context = context;
    }

    public String getAppName(){
        return context.getString(R.string.app_name);
    }
}
