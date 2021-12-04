package com.example.studyplanner_200050013_200050130;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    ImageView navButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        navButton = findViewById(R.id.nav_button);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);

        FragmentAdapter fragmentAdapter = new FragmentAdapter(this);
        viewPager2.setAdapter(fragmentAdapter);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> {
                    switch(position){
                        case 0:
                            tab.setText("Study Plan");
                            break;
                        case 1:
                            tab.setText("Assignment");
                            break;
                        case 2:
                            tab.setText("Exam");
                            break;
                        case 3:
                            tab.setText("Lecture");
                            break;
                        default:
                            tab.setText("New Section");
                    }
                }
        ).attach();
        
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                if(item== findViewById(R.id.nav_home)){
//                    Toast.makeText(getApplicationContext(), "HOME", Toast.LENGTH_SHORT).show();
//                }
//                else if(item== findViewById(R.id.nav_calendar)){
//                    Toast.makeText(getApplicationContext(), "CALENDAR", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(), "NOTHING", Toast.LENGTH_SHORT).show();
//                }
//                return true;
//            }
//        });

        navigationView.setNavigationItemSelectedListener(this);



    }

    public void openCloseNavigationDrawer(android.view.View navButton) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                Toast.makeText(this, "HOME", Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_calendar:
                Toast.makeText(this, "CALENDAR", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "NOTHING", Toast.LENGTH_SHORT).show();
                break;
        }


        return true;
    }
}