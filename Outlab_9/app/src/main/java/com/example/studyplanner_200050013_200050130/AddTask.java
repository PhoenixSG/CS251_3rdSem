package com.example.studyplanner_200050013_200050130;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

public class AddTask extends AppCompatActivity {

    private EditText taskTitle, taskDesc;

    private Spinner spinner;
    private Button addTask, viewTasks, viewCalendar,taskDate, taskTime;
    private DatabaseClass databaseClass;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    int hour, minute;


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
        viewCalendar = (Button) findViewById(R.id.viewCalendar);

        taskTitle = (EditText) findViewById(R.id.TaskTitle);
        taskDesc =  (EditText) findViewById(R.id.TaskDescription);
        taskDate =  (Button) findViewById(R.id.TaskDate);
        taskTime =  (Button) findViewById(R.id.TaskTime);

        databaseClass = new DatabaseClass(getApplicationContext());

        initDatePicker();
        taskDate.setText(getTodaysDate());
        taskTime.setText(getCurrentTime());




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

        viewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AddTask.this, CalendarActivity.class);
                startActivity(i);
                AddTask.this.finish();
            }
        });
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return String.format(Locale.getDefault(), "%02d:%02d",hour, minute);
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
                taskDate.setText(date);
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
                taskTime.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
            }
        };


        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

}