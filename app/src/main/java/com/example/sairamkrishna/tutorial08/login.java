package com.example.sairamkrishna.tutorial08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    Button login;
    EditText email,pass;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    MyDatabaseHelper myDB;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.submit);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        preferences = getSharedPreferences("Session",MODE_PRIVATE);
        editor = preferences.edit();
        String pref_email = preferences.getString("email","");
        if(!pref_email.equals("")){
            Intent intent = new Intent(login.this, welcome.class);
            startActivity(intent);
            finish();
        }
        Intent intent = getIntent();
        String user_id = intent.getStringExtra("user_id");
        String user_pass = intent.getStringExtra("pass");
        email.setText(user_id);
        pass.setText(user_pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email_val = email.getText().toString();
                String password = pass.getText().toString();
                int count = 0;
                //*****************"Tutorial 07"***********************
                myDB = new MyDatabaseHelper(login.this);
                cursor = myDB.checkLogin(email_val, password);
                if(cursor.getCount()!=0){
                    Toast.makeText(login.this,"You have Authenticated Successfully", Toast.LENGTH_SHORT).show();
                    //*****************"Tutorial 07"***********************
                    Intent intent = new Intent(login.this, welcome.class);
                    // tut 6   add value in SharedPreferences
                    editor.putString("email",email.getText().toString().trim());
                    editor.commit();
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(login.this,"Username or Password is incorrect",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void signup(View view) {
        startActivity(new Intent(login.this,MainActivity.class));

    }

}