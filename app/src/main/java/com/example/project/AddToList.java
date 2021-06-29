package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddToList extends AppCompatActivity {
//date
    private static final String TAG="AddToList";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    //////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_list);

        final EditText name=(EditText)findViewById(R.id.taskname_txt);
        final EditText disc=(EditText)findViewById(R.id.taskdisc_txt);
        final EditText date=(EditText)findViewById(R.id.taskdate_txt);
final project_database data=new project_database(getApplicationContext());

        Button add=(Button)findViewById(R.id.addtask_btn); // add button
        Button log=(Button)findViewById(R.id.log_btn); // logout button

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int y=Integer.parseInt(getIntent().getExtras().getString("uesrid")); // user_id
                boolean f=true;
                if(name.getText().toString().length()==0){
                    name.setError("task name shouldn't be null");
                    name.requestFocus();
                    f=false;
                }
                if(disc.getText().toString().length()==0){
                    disc.setError("task discription shouldn't be null");
                    disc.requestFocus();
                    f=false;
                }
                if(date.getText().toString().length()==0){
                    date.setError("task date shouldn't be null");
                    date.requestFocus();
                    f=false;
                }
                if(f) {
                    data.addtask(name.getText().toString(), date.getText().toString(), disc.getText().toString(), y);
                    Toast.makeText(getApplicationContext(), "Task added", Toast.LENGTH_LONG).show();
                    name.getText().clear();
                    disc.getText().clear();
                    date.getText().clear();
                }
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(AddToList.this,MainActivity.class);
                startActivity(i);
            }
        });

        //// date
        mDisplayDate=(TextView)findViewById(R.id.taskdate_txt);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog=new DatePickerDialog(
                        AddToList.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                Log.d(TAG, "onDateset: mm/dd/yyyy: " + month + "/" + dayOfMonth + "/" + year);
                String date = month + "/" + dayOfMonth + "/" + year;
                mDisplayDate.setText(date);
            }
        };

    }
}