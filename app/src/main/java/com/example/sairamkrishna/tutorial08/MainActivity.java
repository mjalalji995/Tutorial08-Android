package com.example.sairamkrishna.tutorial08;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class    MainActivity extends AppCompatActivity {

    EditText fname,lname,email,password;
    RadioGroup gender;
    RadioButton Gender;
    CheckBox checkBox;
    Button signup;
    Spinner city;
    Switch branch;
    MyDatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = findViewById(R.id.edxfname);
        lname = findViewById(R.id.edxlname);
        email = findViewById(R.id.edxemail);
        password = findViewById(R.id.edxpass);
        gender = findViewById(R.id.rdggen);
        branch = findViewById(R.id.swcbranch);
        checkBox = findViewById(R.id.chkprofile);
        city = findViewById(R.id.spncity);
        signup = findViewById(R.id.btnregister);

        signup.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N_MR1)
            @Override
            public void onClick(View v) {
                Boolean Fname = fname.getText().toString().trim().isEmpty();
                Boolean Lname = lname.getText().toString().trim().isEmpty();
                String Email = email.getText().toString().trim();
                Boolean Email_val = email.getText().toString().trim().isEmpty();
                String Password = password.getText().toString().trim();
                Boolean Password_val = password.getText().toString().trim().isEmpty();
                Boolean Branch = branch.isChecked();
                int id = gender.getCheckedRadioButtonId();
                String set_city = city.getSelectedItem().toString();

                if(!Fname && !Lname && !Email_val && !Password_val && Patterns.EMAIL_ADDRESS.matcher(Email).matches() && Password.length()>=8 && !set_city.equals("Select Your City...")){
                    Gender = findViewById(id);
                    //*****************"Tutorial 07"***********************
                    mydb = new MyDatabaseHelper(MainActivity.this);
                    Boolean res=mydb.reg_insert(fname.getText().toString().trim(),lname.getText().toString().trim(),Email,Branch,Password,Gender.getText().toString(),set_city);
                    Intent intent = new Intent(MainActivity.this,login.class);
                    if(res){
                        Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                        intent.putExtra("user_id",Email);
                        intent.putExtra("pass",Password);
                        startActivity(intent);
                    }else {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                    //*****************"Tutorial 07"***********************
                }
                else {
                    if(Fname){
                        fname.setError("Name is Require");
                    }
                    if(Lname){
                        lname.setError("Surname is Require");
                    }
                    if(Email_val){
                        email.setError("Email is Require");
                    }
                    else{
                        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
                            email.setError("Email is Invalid");
                        }
                    }
                    if(Password_val){
                        password.setError("Password is Invalid");
                    }
                    else{
                        if(Password.length()<8) {
                            password.setError("Password to short");
                        }
                    }
                    if(set_city.equals("Select Your City...")){
                        TextView textView = (TextView) city.getSelectedView();
                        textView.setError("Selected Item is Invalid");
                    }
                    Toast.makeText(MainActivity.this,"Invalid Credentials...",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}