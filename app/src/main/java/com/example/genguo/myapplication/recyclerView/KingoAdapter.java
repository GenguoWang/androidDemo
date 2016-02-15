package com.example.genguo.myapplication.recyclerView;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.genguo.myapplication.R;

/**
 * Created by genguo on 2/9/16.
 */
public class KingoAdapter extends RecyclerView.Adapter<KingoAdapter.ViewHolder>{
    private String [] dataset;
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public ViewHolder(View v){
            super(v);
            textView = (TextView) v.findViewById(R.id.text_view);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v,textView.getText(),Snackbar.LENGTH_SHORT).show();
                    Log.d("wgg", "Element  " + textView.getText() + "clicked.");
                }
            });
        }
        public TextView getTextView(){
            return textView;
        }
    }
    public KingoAdapter(String [] dataset){
        this.dataset = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.text_row,viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView().setText(dataset[position]);
    }

    @Override
    public int getItemCount() {
        return dataset.length;
    }
}
