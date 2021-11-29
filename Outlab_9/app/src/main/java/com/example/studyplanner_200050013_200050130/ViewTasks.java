package com.example.studyplanner_200050013_200050130;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewTasks extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<TasksModel> tasksModelArrayList;
    private DatabaseClass databaseClass;
    private TaskRecyclerViewAdapter taskRecyclerViewAdapter;
    private RecyclerView recyclerViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tasks);

        // initializing our all variables.
        tasksModelArrayList = new ArrayList<>();
        databaseClass = new DatabaseClass(ViewTasks.this);

        // getting our course array
        // list from db handler class.
        tasksModelArrayList = databaseClass.readCourses();

        // on below line passing our array lost to our adapter class.
        taskRecyclerViewAdapter = new TaskRecyclerViewAdapter(tasksModelArrayList, ViewTasks.this);
        recyclerViewTasks = findViewById(R.id.idRVTasks);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewTasks.this, RecyclerView.VERTICAL, false);
        recyclerViewTasks.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        recyclerViewTasks.setAdapter(taskRecyclerViewAdapter);
    }
}
