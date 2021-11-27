package com.example.studyplanner_200050013_200050130;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        Intent intent = getIntent();

        Spinner spinner=findViewById(R.id.spinner_options);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.taskTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinner.setAdapter(adapter);

        Button addTask = (Button) findViewById(R.id.addTask);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Click working", Toast.LENGTH_SHORT).show();
            }
        });

    }
}