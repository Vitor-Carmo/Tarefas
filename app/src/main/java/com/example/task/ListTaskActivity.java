package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListTaskActivity extends AppCompatActivity {
    private ListView listTask;
    private ArrayAdapter<String> itemsAdapter;
    private ImageView go_to_home;

    private ArrayList<String> items;
    private ArrayList<Integer> ids;

    private TaskDatabase database = new TaskDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);

        database.setDatabase(openOrCreateDatabase("Task", MODE_PRIVATE, null));
        database.database();


        go_to_home = findViewById(R.id.gotohome);
        listTask = findViewById(R.id.listTask);


        go_to_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                database.deleteTask(ids.get(position));
                loadListView();
            }
        });

        loadListView();


    }




    private void loadListView(){

        ids =  new ArrayList<Integer>();

        items =  new ArrayList<String>();

        itemsAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_view_text, R.id.list_content, items);

        listTask.setAdapter(itemsAdapter);

        database.loadTask(items, ids);

    }

}
