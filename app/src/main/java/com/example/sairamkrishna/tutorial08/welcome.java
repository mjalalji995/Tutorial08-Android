package com.example.sairamkrishna.tutorial08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class welcome extends AppCompatActivity {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ListView lstData;
    MyDatabaseHelper myDB;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        preferences = getSharedPreferences("Session",MODE_PRIVATE);
        editor = preferences.edit();

        lstData = findViewById(R.id.lstDataView);
        myDB = new MyDatabaseHelper(this);

        adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myDB.getUserList()
        ){@Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            TextView tv = (TextView) view.findViewById(android.R.id.text1);

           tv.setTextColor(Color.RED);

            return view;
        }
        };
        lstData.setAdapter(adapter);
        lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String username = ((TextView)view).getText().toString();
                Intent intent = new Intent(welcome.this,display.class);
                intent.putExtra("username",username);
                startActivity(intent);

            }
        });
   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.abt_menu:
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lgt_menu:
                editor.remove("email");
                editor.commit();
                startActivity(new Intent(welcome.this,MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
        }
}