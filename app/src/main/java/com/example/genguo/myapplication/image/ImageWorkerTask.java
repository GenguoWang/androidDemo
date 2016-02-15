package com.example.genguo.myapplication.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by genguo on 2/11/16.
 */
public class ImageWorkerTask extends AsyncTask<Void, Void, Bitmap>{
    public interface ImageWorkerTaskCallback{
        void onImageReady(ImageWorkerTask task, Bitmap image);
    }
    private ImageWorkerTaskCallback callback;
    private String url;
    public ImageWorkerTask(String url, ImageWorkerTaskCallback callback){
        this.callback = callback;
        this.url = url;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if(callback != null){
            callback.onImageReady(this, bitmap);
        }
    }

    @Override
    protected Bitmap doInBackground(Void... params) {
        HttpURLConnection connection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        byte [] bytes = new byte[1024*1024*3];
        int len = 0;
        try{
            URL url = new URL(this.url);
            connection = (HttpURLConnection)url.openConnection();
            in = new BufferedInputStream(connection.getInputStream(),1024);
            int b;
            while((b = in.read()) != -1){
                bytes[len] = (byte)(b&0xff);
                len += 1;
            }

        }catch (IOException e){
            Log.e("wgg","wgg "+e);
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, len);
        return bitmap;
    }
}
