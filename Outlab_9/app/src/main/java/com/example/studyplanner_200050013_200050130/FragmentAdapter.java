package com.example.studyplanner_200050013_200050130;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;



public class FragmentAdapter extends FragmentStateAdapter {


    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new StudyPlan();
            case 1:
                return new Assignment();
            case 2:
                return new Exam();
            case 3:
                return new Lecture();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
