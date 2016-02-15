package com.example.genguo.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.genguo.myapplication.activity.AnotherActivity;
import com.example.genguo.myapplication.daggerTest.PrinterModule;
import com.example.genguo.myapplication.daggerTest.WorkStation;
import com.example.genguo.myapplication.recyclerView.RecyclerViewActivity;
import com.example.genguo.myapplication.volley.VolleyActivity;

import javax.inject.Singleton;

import dagger.Component;

public class MainActivity extends AppCompatActivity {
    @Singleton
    @Component(modules = { PrinterModule.class })
    public interface WorkGetter {
        WorkStation station();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Another Activity", Snackbar.LENGTH_LONG)
                        .setAction("Open", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, AnotherActivity.class);
                                intent.putExtra("text","I'm Miley, loves Kingo");
                                startActivity(intent);
                            }
                        }).show();
            }
        });
        WorkGetter workGetter = DaggerMainActivity_WorkGetter.builder().build();
        workGetter.station().work();
    }

    public void gotoRecyclerView(View v){
        Intent intent = new Intent(MainActivity.this, RecyclerViewActivity.class);
        startActivity(intent);
    }

    public void gotoVolleyActivity(View v){
        Intent intent = new Intent(MainActivity.this, VolleyActivity.class);
        startActivity(intent);
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
}
