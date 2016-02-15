package com.example.genguo.myapplication.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.genguo.myapplication.R;
import com.example.genguo.myapplication.image.ImageWorkerTask;

/**
 * Created by genguo on 2/10/16.
 */
public class AnotherActivity extends AppCompatActivity implements ImageWorkerTask.ImageWorkerTaskCallback{
    private ImageView imageView;
    static final String url = "https://lh3.googleusercontent.com/-PyggXXZRykM/URquh-kVvoI/AAAAAAAAAbs/hFtDwhtrHHQ/s1024/Colorado%252520River%252520Sunset.jpg";
    @Override
    public void onImageReady(ImageWorkerTask task, Bitmap image) {
        imageView.setImageBitmap(image);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Intent intent = getIntent();
        imageView = (ImageView)findViewById(R.id.image_view);
        if(intent != null){
            TextView tv = (TextView)findViewById(R.id.another_text_view);
            tv.setText(intent.getStringExtra("text"));
        }
        ImageWorkerTask task = new ImageWorkerTask(url, this);
        task.execute();
    }
}
