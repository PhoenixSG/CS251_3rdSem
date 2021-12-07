package com.example.studyplanner_200050013_200050130;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    private TextView onClickDate;
    private TextView highlighted_date;

    ImageView navButton;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_activity_calendar);
        initWidgets();
        selectedDate = LocalDate.now();
        setMonthView();

        navButton = findViewById(R.id.nav_button);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.nav_home){
                    Intent intent = new Intent(CalendarActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "HOME PRESSED", Toast.LENGTH_SHORT).show();
                }
                else if(item.getItemId()==R.id.nav_calendar){
                    Toast.makeText(getApplicationContext(), "Already on CALENDAR", Toast.LENGTH_SHORT).show();
                }

                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }



    private void initWidgets() {
        calendarRecyclerView = (RecyclerView) findViewById(R.id.calendarRecyclerView);
        monthYearText = (TextView) findViewById(R.id.monthYearTV);
        onClickDate = (TextView) findViewById(R.id.today);
        highlighted_date = null;
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
    private void reset(){
        ((TextView)findViewById(R.id.calendar_studyplan_count)).setText(""+0);
        ((TextView)findViewById(R.id.calendar_exams_count)).setText(""+0);
        ((TextView)findViewById(R.id.calendar_lecture_count)).setText(""+0);
        ((TextView)findViewById(R.id.calendar_assignment_count)).setText(""+0);
        ((TextView)findViewById(R.id.today)).setText("Select a Date");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void previousMonthAction(View view){
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
        reset();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void nextMonthAction(View view){
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
        reset();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onItemClick(int position, Integer dayText, TextView dayOfMonth) {
        if(dayText!=0){

            if(highlighted_date!=null){
                highlighted_date.setBackgroundColor(Color.parseColor("#ffe4e1"));
            }
            highlighted_date = dayOfMonth;
            highlighted_date.setBackgroundColor(Color.parseColor("#F7CAC9"));

            String msg = String.format("%02d/%s/%s", dayText, num_monthFromDate(selectedDate), num_yearFromDate(selectedDate));
//            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

            onClickDate.setText(""+dayText+" "+monthYearFromDate(selectedDate));

            DatabaseClass databaseClass = new DatabaseClass(CalendarActivity.this);

            ((TextView)findViewById(R.id.calendar_studyplan_count)).setText(""+databaseClass.readTasks("Study Plan", msg).size());
            ((TextView)findViewById(R.id.calendar_exams_count)).setText(""+databaseClass.readTasks("Exams", msg).size());
            ((TextView)findViewById(R.id.calendar_lecture_count)).setText(""+databaseClass.readTasks("Lectures", msg).size());
            ((TextView)findViewById(R.id.calendar_assignment_count)).setText(""+databaseClass.readTasks("Assignments", msg).size());
        }
        else{
            Toast.makeText(getApplicationContext(), "Please Select a Date :)", Toast.LENGTH_SHORT).show();
        }
    }

    public void openCloseNavigationDrawer(android.view.View navButton) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }
}