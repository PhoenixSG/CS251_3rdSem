package com.example.studyplanner_200050013_200050130;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class UpdateTask extends AppCompatActivity {
    // variables for our edit text, button, strings and dbhandler class.

    private EditText taskTitleUpdate, taskDescUpdate;

    private Spinner spinnerUpdate;
    private Button UpdateTask,taskDateUpdate, taskTimeUpdate, DeleteTask;
    private DatabaseClass databaseClass;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    int hour, minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        // initializing all our variables.
        taskTitleUpdate = findViewById(R.id.UpdateTaskTitle);
        taskDescUpdate = findViewById(R.id.UpdateTaskDescription);
        taskDateUpdate = findViewById(R.id.UpdateTaskDate);
        taskTimeUpdate = findViewById(R.id.UpdateTaskTime);
        spinnerUpdate = findViewById(R.id.Update_spinner_options);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.taskTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        spinnerUpdate.setAdapter(adapter);

        UpdateTask = findViewById(R.id.UpdateTask);
        DeleteTask = findViewById(R.id.DeleteTask);

        // on below line we are initialing our database class.
        databaseClass = new DatabaseClass(getApplicationContext());
        initDatePicker();

        taskTitleUpdate.setText(getIntent().getStringExtra("name"));
        taskDescUpdate.setText(getIntent().getStringExtra("description"));
        taskDateUpdate.setText(getIntent().getStringExtra("date"));
        taskTimeUpdate.setText(getIntent().getStringExtra("time"));
        String old_type = getIntent().getStringExtra("type");
        if(old_type.equals("Study Plan")){
            spinnerUpdate.setSelection(0);
        }
        else if(old_type.equals("Assignments")){
            spinnerUpdate.setSelection(1);
        }
        else if(old_type.equals("Exams")){
            spinnerUpdate.setSelection(2);
        }
        else if(old_type.equals("Lectures")){
            spinnerUpdate.setSelection(3);
        }

        String old_task_name = getIntent().getStringExtra("name");

        // adding on click listener to our update task button.
        UpdateTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // validating if the text fields are empty or not.
                if (taskTitleUpdate.getText().toString().isEmpty() || taskDescUpdate.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // inside this method we are calling an update task
                // method and passing all our edit text values.
                databaseClass.updateTask(old_task_name, taskTitleUpdate.getText().toString(), taskDateUpdate.getText().toString(), taskDescUpdate.getText().toString(), taskTimeUpdate.getText().toString(), spinnerUpdate.getSelectedItem().toString());

                // displaying a toast message that our task has been updated.
                Toast.makeText(UpdateTask.this, "Task Details Updated Successfully", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent intent = new Intent(UpdateTask.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        DeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our task.
                AlertDialog.Builder alert = new AlertDialog.Builder(UpdateTask.this);
                alert.setTitle("Delete");
                alert.setMessage("Are you sure you want to delete "+old_task_name+"?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseClass.deleteTask(old_task_name);
                        Toast.makeText(UpdateTask.this, "Deleted the task- "+old_task_name, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateTask.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                alert.show();

            }
        });
    }


    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                taskDateUpdate.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    private String makeDateString(int day, int month, int year) {
        return String.format("%02d/%02d/%02d",day, month,year);
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    public void openTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute)
            {
                hour = selectedHour;
                minute = selectedMinute;
                taskTimeUpdate.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };


        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

}