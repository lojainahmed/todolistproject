package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText name=(EditText)findViewById(R.id.L_name);
        final EditText password=(EditText)findViewById(R.id.L_password);
        Button login=(Button)findViewById(R.id.login_btn);
        Button reg=(Button)findViewById(R.id.Register);
// object of database class
        final project_database db=new project_database(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor val = db.checkuser();
                while (!val.isAfterLast()) {
                    if (name.getText().toString().equals(val.getString(0))) {
                        if (!(password.getText().toString().equals(val.getString(1)))) {
                            password.setError("Wrong Password");
                            return;
                        }
                    }
                    val.moveToNext();

                }
                boolean nx = true;
                if (name.getText().toString().trim().length() == 0) {
                    name.setError("Username is not entered");
                    name.requestFocus();
                    nx = false;
                }
                if (password.getText().toString().trim().length() == 0) {
                    password.setError("Password is not entered");
                    password.requestFocus();
                    nx = false;
                }
                if (nx) {
                    String x = "Null";
                    boolean found = false;

                    Cursor curso = db.checkuser(); // feha all the users : uname in the index 0 and password in index 1
                    while (!curso.isAfterLast()) { // tool ma hya mawsltsh l el akhr
                        if (name.getText().toString().equals(curso.getString(0)) && password.getText().toString().equals(curso.getString(1))) {
                            found = true;
                            break;
                        }
                        curso.moveToNext();
                    }
                    if (found) {
                        Toast.makeText(getApplicationContext(), "user found", Toast.LENGTH_LONG).show();
                        //  Toast.makeText(getApplicationContext(),String.valueOf(db.getuserID(name.getText().toString(),password.getText().toString())),Toast.LENGTH_LONG).show();

                        Intent i = new Intent(Login.this, option.class);

                        String x2 = String.valueOf(db.getuserID(name.getText().toString(), password.getText().toString()));
                        i.putExtra("taskid", x2);
                        startActivity(i);
                    } else {
                        Toast.makeText(getApplicationContext(), "user not found you should register first", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(Login.this,MainActivity.class);
                startActivity(I);
            }
        });
    }
}