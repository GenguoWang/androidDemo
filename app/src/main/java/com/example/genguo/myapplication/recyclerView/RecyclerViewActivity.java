package com.example.genguo.myapplication.recyclerView;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.genguo.myapplication.R;

/**
 * Created by genguo on 2/13/16.
 */
public class RecyclerViewActivity extends AppCompatActivity{
    private String [] dataSet;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private KingoAdapter adapter;

    private void setupRecycleView(){
        dataSet = new String[100];
        for(int i=0;i<100;++i){
            dataSet[i] = "Kingo loves Miley " + i*100;
        }
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new KingoAdapter(dataSet);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        setupRecycleView();
    }
}
