package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText newTask;
    private Button addTask;
    private TaskDatabase database = new TaskDatabase(this);

    private SQLiteDatabase data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database.setDatabase(openOrCreateDatabase("Task", MODE_PRIVATE, null));

        database.database();

        newTask = findViewById(R.id.newTask);
        addTask = findViewById(R.id.addTask);


        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.addTask(newTask.getText().toString());
                newTask.setText("");
            }
        });



    }


    public void listTask(View v){
        Intent intent = new Intent(this, ListTaskActivity.class);
        startActivity(intent);


    }




}
