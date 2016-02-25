package com.example.genguo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by genguo on 2/15/16.
 * UI Automator test
 */
@RunWith(AndroidJUnit4.class)
public class LaunchTest {
    private UiDevice mDevice;
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String MY_PACKAGE_NAME = "com.example.genguo.myapplication";
    @Before
    public void startFromHome(){
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();
        final String launcherPackageName = getLauncherPackageName();
        assertThat(launcherPackageName, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackageName).depth(0)), LAUNCH_TIMEOUT);

        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(MY_PACKAGE_NAME);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
        mDevice.wait(Until.hasObject(By.pkg(MY_PACKAGE_NAME).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void checkPreCondition(){
        assertThat(mDevice, notNullValue());
    }

    @Test
    public void gotoVolley(){
        mDevice.findObject(By.res(MY_PACKAGE_NAME,"volleyBtn")).click();
        UiObject2 object = mDevice.wait(Until.findObject(By.res(MY_PACKAGE_NAME, "volley_text")), 5000);
        assertThat(object.getText(), is(equalTo("kingo")));
    }

    private String getLauncherPackageName(){
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        PackageManager pm  = InstrumentationRegistry.getContext().getPackageManager();
        ResolveInfo info = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return info.activityInfo.packageName;
    }
}
