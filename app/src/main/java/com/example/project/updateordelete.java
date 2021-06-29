package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class updateordelete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateordelete);
// id of the user
        int id=Integer.parseInt(getIntent().getExtras().getString("ID"));

       final project_database dd=new project_database(getApplicationContext());

       final ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.row);

       Cursor c=dd.findusertasks(id);
        int count=c.getCount();

        if(count==0){
            Toast.makeText(getApplicationContext(),"There is no tasks for you",Toast.LENGTH_SHORT).show();
        }
        else {
            while (!c.isAfterLast()) {
                adapter.add(c.getString(0));
                c.moveToNext();
            }
        }

        final String[] str = {""};

        final ListView list=(ListView)findViewById(R.id.ooooli);

        Spinner spin=(Spinner)findViewById(R.id.myspinner);
spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        str[0] =((TextView)view).getText().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
Button btn=(Button)findViewById(R.id.l_btn);
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent i=new Intent(updateordelete.this,MainActivity.class);
        startActivity(i);
    }
});

        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (str[0].equals("Update")) {
                    String strr = ((TextView) view).getText().toString();
                    // adapter.remove(((TextView)view).getText().toString());
                    project_database p = new project_database(getApplicationContext());
                    int i = Integer.parseInt(getIntent().getExtras().getString("ID"));
                    Cursor c2 = p.findusertasks(i);
                    p.update(c2, strr);
                    Toast.makeText(getApplicationContext(), "Task updated successfully", Toast.LENGTH_LONG).show();
                } else if (str[0].equals("Delete")) {
                    String strr = ((TextView) view).getText().toString();
                    adapter.remove(((TextView) view).getText().toString());
                    project_database p = new project_database(getApplicationContext());
                    int i = Integer.parseInt(getIntent().getExtras().getString("ID"));
                    Cursor c2 = p.findusertasks(i);
                    p.del(c2, strr);
                    Toast.makeText(getApplicationContext(), "Task deleted successfully", Toast.LENGTH_LONG).show();
                }
            }
        });
             }
            }
