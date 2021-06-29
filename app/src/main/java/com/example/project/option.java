package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class option extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        ListView listView=(ListView)findViewById(R.id.optionlist);
        Button back=(Button)findViewById(R.id.back);
        String []myarr=getResources().getStringArray(R.array.arr);
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.row,myarr);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String option=((TextView)view).getText().toString();
                String str=getIntent().getExtras().getString("taskid");
                if(option.equals("Add Task")){
                    Intent i=new Intent(option.this,AddToList.class);
                    i.putExtra("uesrid",str);
                    startActivity(i);
                }
                else if (option.equals("Display tasks")){
                   Intent i=new Intent(option.this,Display.class);
                   i.putExtra("ID",str);
                    startActivity(i);
                }
                else if(option.equals("Update or delete")){
                    Intent i=new Intent(option.this,updateordelete.class);
                    i.putExtra("ID",str);
                    startActivity(i);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(option.this,Login.class);
                startActivity(i);
            }
        });
    }
}