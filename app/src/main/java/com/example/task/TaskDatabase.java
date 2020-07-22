package com.example.task;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskDatabase {

    private SQLiteDatabase database;
    private Activity activity;

    TaskDatabase(Activity activity){
        this.activity = activity;
    }



    void database(){
        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS myTasks(id INTEGER PRIMARY KEY AUTOINCREMENT, task VARCHAR)");

        }catch (Exception e){
            e.printStackTrace();

        }
    }


    void addTask(String addTask){
        try{

            if(addTask.equals("")){
                Toast.makeText(activity, "Por Favor, Inserir uma tarefa valida", Toast.LENGTH_SHORT).show();
            }else{
                database.execSQL("INSERT INTO myTasks(task) VALUES ('"+addTask+"')");
                Toast.makeText(activity, "Tarefa "+ addTask + " inserida!", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();

        }
    }

    void loadTask(ArrayList<String> items, ArrayList<Integer> ids){
        Cursor cursor = database.rawQuery("SELECT * FROM myTasks ORDER BY id DESC", null);

        int indexColumnID = cursor.getColumnIndex("id");
        int indexColumnTask = cursor.getColumnIndex("task");

        // Achei esse código na interner e ele funcionou
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ids.add(Integer.parseInt(cursor.getString(indexColumnID)));
                items.add(cursor.getString(indexColumnTask));
            }
            cursor.close();
        }

    }

    void deleteTask(Integer id){
        try{
            database.execSQL("DELETE FROM myTasks WHERE id="+id);
            Toast.makeText(activity, "Tarefa Removida", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }
}
