package com.example.studyplanner_200050013_200050130;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {

    private EditText taskTitle, taskDate, taskTime, taskDesc;
    private Spinner spinner;
    private Button addTask, viewTasks;
    private DatabaseClass databaseClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Intent intent = getIntent();
        int position = intent.getIntExtra("name",0);


        spinner=findViewById(R.id.spinner_options);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.taskTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(position);


        addTask = (Button) findViewById(R.id.addTask);
        viewTasks = (Button) findViewById(R.id.readTasks);

        taskTitle = (EditText) findViewById(R.id.TaskTitle);
        taskDesc =  (EditText) findViewById(R.id.TaskDescription);
        taskDate =  (EditText) findViewById(R.id.TaskDate);
        taskTime =  (EditText) findViewById(R.id.TaskTime);

        databaseClass = new DatabaseClass(getApplicationContext());


       taskDate.setText("today");
       taskTime.setText("now");



        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // below line is to get data from all edit text fields.
                String input_taskTitle = taskTitle.getText().toString();
                String input_taskDesc = taskDesc.getText().toString();
                String input_taskDate = taskDate.getText().toString();
                String input_taskTime = taskTime.getText().toString();
                String input_taskType = spinner.getSelectedItem().toString();

                // validating if the text fields are empty or not.
                if (input_taskTitle.isEmpty() || input_taskDesc.isEmpty() || input_taskDate.isEmpty() || input_taskTime.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                databaseClass.addNewTask(input_taskTitle, input_taskDate, input_taskDesc, input_taskTime, input_taskType);

                // after adding the data we are displaying a toast message.
                Toast.makeText(getApplicationContext(), "Task has been added.", Toast.LENGTH_SHORT).show();
                taskTitle.setText("");
                taskDesc.setText("");
                taskDate.setText("");
                taskTime.setText("");
                spinner.setSelected(false);
                Intent intent = new Intent(AddTask.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                
            }
        });


        viewTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddTask.this, ViewTasks.class);
                startActivity(i);
                AddTask.this.finish();
            }
        });


    }
}