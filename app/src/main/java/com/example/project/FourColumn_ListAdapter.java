package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;

import java.util.ArrayList;

public class FourColumn_ListAdapter extends ArrayAdapter<Task> {
    private LayoutInflater mInflatter;
    private ArrayList<Task>tasks;
    private int mViewResourceId;

    public FourColumn_ListAdapter(Context context,int textViewResourceId,ArrayList<Task> tasks){
        super(context,textViewResourceId,tasks);
        this.tasks=tasks;
        mInflatter=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId=textViewResourceId;
    }
    public View getView(int position , View convertView, ViewGroup parents){
        convertView=mInflatter.inflate(mViewResourceId,null);

        Task task=tasks.get(position);

        if(task!=null){
            TextView title=(TextView)convertView.findViewById(R.id.texttitle);
            title.setTextColor(Color.WHITE);

            TextView description=(TextView)convertView.findViewById(R.id.textdesc);
            description.setTextColor(Color.WHITE);

            TextView date=(TextView)convertView.findViewById(R.id.textdate);
            date.setTextColor(Color.WHITE);

            TextView status=(TextView)convertView.findViewById(R.id.textstatus);
            status.setTextColor(Color.WHITE);

            if(title!=null){
                title.setText(task.getTitle());
            }
            if(description!=null){
                description.setText(task.getDescription());
            }
            if(date!=null){
                date.setText(task.getDate());
            }
            if(status!=null){
                status.setText(task.getStatus());
            }
        }
    return convertView;
    }
}
