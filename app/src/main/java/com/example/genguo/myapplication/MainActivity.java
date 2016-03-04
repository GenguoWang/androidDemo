package com.example.genguo.myapplication;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.genguo.myapplication.activity.AnotherActivity;
import com.example.genguo.myapplication.daggerTest.PrinterModule;
import com.example.genguo.myapplication.daggerTest.WorkStation;
import com.example.genguo.myapplication.recyclerView.RecyclerViewActivity;
import com.example.genguo.myapplication.service.DemoIntentService;
import com.example.genguo.myapplication.volley.VolleyActivity;

import javax.inject.Singleton;

import dagger.Component;

class ResponseReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("wgg","response received: "+intent.getStringExtra(Constant.EXTENDED_DATA_STATUS));
    }
}

public class MainActivity extends AppCompatActivity {
    @Singleton
    @Component(modules = { PrinterModule.class })
    public interface WorkGetter {
        WorkStation station();
    }

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editText = (EditText)findViewById(R.id.editText);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Another Activity", Snackbar.LENGTH_LONG)
                        .setAction("Open", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                                intent.putExtra("text", "I'm Miley, loves Kingo");
                                startActivity(intent);
                            }
                        }).show();
            }
        });
        WorkGetter workGetter = DaggerMainActivity_WorkGetter.builder().build();
        workGetter.station().work();
        IntentFilter filter = new IntentFilter(Constant.BROADCAST_ACTION);
        ResponseReceiver receiver = new ResponseReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(receiver,filter);
    }

    public void gotoRecyclerView(View v){
        Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
        startActivity(intent);
    }

    public void gotoVolleyActivity(View v){
        Intent intent = new Intent(MainActivity.this, VolleyActivity.class);
        startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void testDelayedTransition(View v){
        ViewGroup rootView = (ViewGroup) findViewById(R.id.main_content);
        TransitionManager.beginDelayedTransition(rootView, new Fade());
        rootView.removeView(editText);
        Button btn = new Button(this);
        btn.setText("changeBack");
        ViewGroup.LayoutParams param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btn.setLayoutParams(param);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.testDelayedTransitionBack(null);
            }
        });
        rootView.addView(btn);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void testDelayedTransitionBack(View v){
        ViewGroup rootView = (ViewGroup) findViewById(R.id.main_content);
        TransitionManager.beginDelayedTransition(rootView, new Fade(Fade.IN));
        rootView.addView(editText);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void testSceneTransition(){
        //todo
        Transition mFadeTransition =
                TransitionInflater.from(this).
                        inflateTransition(R.transition.test_transition);
        Transition fromCodeFadeTransition = new Fade();
        //TransitionManager.go();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void testIntentService(View v){
        Intent intent = new Intent(this, DemoIntentService.class);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startService(intent);
    }
}
