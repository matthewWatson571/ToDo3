package com.example.matthewwatson.todo3;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matthew.Watson on 10/25/16.
 */

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.noteViewHolder>{
    private Context context;
    protected ArrayList<ThumbNail> thumbNailArrayList;
    private LayoutInflater inflater;

    public MyCustomAdapter(Context context, ArrayList<ThumbNail> inflaterArrayList){
        this.context = context;
        this.thumbNailArrayList = inflaterArrayList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public noteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item_row, parent, false);

        noteViewHolder holder = new noteViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(noteViewHolder holder, int position) {
        holder.textview.setText(thumbNailArrayList.get(position).thumbTitle);
        holder.imageView.setImageResource(thumbNailArrayList.get(position).imageId);

    }

    @Override
    public int getItemCount() {
        if(thumbNailArrayList !=null){
        return thumbNailArrayList.size();
        }
        return 0;
    }

    public static class noteViewHolder extends RecyclerView.ViewHolder{
        TextView textview;
        ImageView imageView;

        public noteViewHolder(View itemView) {
            super(itemView);
            textview = (TextView) itemView.findViewById(R.id.txv_row);
            imageView = (ImageView) itemView.findViewById(R.id.img_row);
        }
    }
}
