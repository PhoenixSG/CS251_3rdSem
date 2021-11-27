package com.example.studyplanner_200050013_200050130;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    }
}