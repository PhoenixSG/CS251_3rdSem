package com.example.studyplanner_200050013_200050130;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private TextView onClickDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();
    }



    private void initWidgets() {
        calendarRecyclerView = (RecyclerView) findViewById(R.id.calendarRecyclerView);
        monthYearText = (TextView) findViewById(R.id.monthYearTV);
        onClickDate = (TextView) findViewById(R.id.today);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));

        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);

        RecyclerView.LayoutManager layoutManager =
                new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; ++i){
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek){

                daysInMonthArray.add("");
            }
            else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }

        return daysInMonthArray;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String num_monthFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private String num_yearFromDate(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return date.format(formatter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view){
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view){
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onItemClick(int position, Integer dayText) {
        if(dayText!=0){
            String msg = String.format("%02d/%s/%s", dayText, num_monthFromDate(selectedDate), num_yearFromDate(selectedDate));
            Toast.makeText(this, ""+selectedDate, Toast.LENGTH_SHORT).show();
            onClickDate.setText(msg);
            DatabaseClass databaseClass = new DatabaseClass(CalendarActivity.this);
            int selected_position= dayText+selectedDate.withDayOfMonth(1).getDayOfWeek().getValue();

            ((TextView)findViewById(R.id.calendar_studyplan_count)).setText(""+databaseClass.readTasks(0, msg).size());
            ((TextView)findViewById(R.id.calendar_exams_count)).setText(""+databaseClass.readTasks(1, msg).size());
            ((TextView)findViewById(R.id.calendar_lecture_count)).setText(""+databaseClass.readTasks(2, msg).size());
            ((TextView)findViewById(R.id.calendar_assignment_count)).setText(""+databaseClass.readTasks(3, msg).size());
        }
        else{
            Toast.makeText(getApplicationContext(), "Please Select a Date :)", Toast.LENGTH_SHORT).show();
        }
    }
}