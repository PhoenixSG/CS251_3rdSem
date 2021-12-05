package com.example.studyplanner_200050013_200050130;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;

    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if((String)dayOfMonth.getText()!="") {
            onItemListener.onItemClick(getAdapterPosition(), Integer.parseInt((String) dayOfMonth.getText()));
//            dayOfMonth.setBackgroundColor(Color.parseColor("#00FF00"));
        }
        else{
            onItemListener.onItemClick(getAdapterPosition(), 0);
        }


    }
}
