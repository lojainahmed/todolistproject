package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Display extends AppCompatActivity {
    project_database myDB;
    ArrayList<Task>tasklist;
      Task task;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        int id=Integer.parseInt(getIntent().getExtras().getString("ID"));

        myDB=new project_database(this);
        tasklist=new ArrayList<>();
        Cursor data=myDB.findusertasks(id);
        int numRows=data.getCount();
        if(numRows==0){
            Toast.makeText(getApplicationContext(),"There is no tasks for you",Toast.LENGTH_SHORT).show();
        }
        else{
        while (!data.isAfterLast()){
            task=new Task(data.getString(0),data.getString(1),data.getString(2)
                    ,data.getString(3));
            tasklist.add(task);
            data.moveToNext();
        }
        FourColumn_ListAdapter adapter=new FourColumn_ListAdapter(this,R.layout.list_adapter_view,tasklist);
            listView=(ListView)findViewById(R.id.displaylistview);
        listView.setAdapter(adapter);
        }
        Button log=(Button)findViewById(R.id.logout);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Display.this,MainActivity.class);
                startActivity(i);
            }
        });
        //****************************
    }

}