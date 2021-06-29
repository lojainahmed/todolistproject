package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
       final project_database db=new project_database(this);
        final EditText name=(EditText)findViewById(R.id.name_txt);
        final EditText pass1=(EditText)findViewById(R.id.pass1_txt);
        final EditText pass2=(EditText)findViewById(R.id.pass2_txt);
        final EditText email=(EditText)findViewById(R.id.email_txt);
        final EditText phone=(EditText)findViewById(R.id.phone_txt);
        final EditText address=(EditText)findViewById(R.id.address_txt);
        Button register=(Button)findViewById(R.id.register_btn);
        Button log=(Button)findViewById(R.id.log_btn);

        final Pattern pass=Pattern.compile("^" +
                "(?=.*[0-9])" +
                "(?=.*[a-z])" +
                "(?=.*[A-Z])" +
                "(?=.*[a-zA-Z])" +
                "(?=\\S+$)" +
                ".{6,}" +
                "$");
register.setOnClickListener(new View.OnClickListener() {
    @Override

    public void onClick(View v) {
        boolean f=true;
        if(name.getText().toString().length()==0){
            name.setError("user name shouldn't be null");
            name.requestFocus();
            f=false;
        }
        if(phone.getText().toString().length()==0){
            name.setError("phone shouldn't be empty");
            name.requestFocus();
            f=false;
        }
        if(address.getText().toString().length()==0){
            name.setError("address shouldn't be null");
            name.requestFocus();
            f=false;
        }
        if(pass2.getText().toString().length()==0) {
            pass2.setError("Please enter confirm password");
            pass2.requestFocus();
            f=false;
        }
        if(phone.getText().toString().length()!=11) {
            phone.setError("phone number should be 11 numbers");
            phone.requestFocus();
            f=false;
        }
        if(!pass1.getText().toString().equals(pass2.getText().toString())) {
            pass2.setError("Password Not matched");
            pass2.requestFocus();
            f=false;
        }
        if(!pass.matcher((pass1.getText().toString())).matches()){
           pass1.setError("Password should contain at least 6 characters including at least 1 lower case letter, 1 upper case letter, 1 digit");
            f=false;
        }
        if(pass1.getText().toString().length()<8){
            pass1.setError("password should be at least 8 characters");
            pass1.requestFocus();
            f=false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()){
            email.setError("Please Enter a valid Email");
            f=false;
        }
        if(f){
             db.adduser(name.getText().toString(),pass1.getText().toString(),email.getText().toString(),phone.getText().toString(),address.getText().toString());
             Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT).show();
             Intent I=new Intent(MainActivity.this,Login.class);
             startActivity(I);
        }

    }
});

log.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent I=new Intent(MainActivity.this,Login.class);
        startActivity(I);
    }
});
}
}