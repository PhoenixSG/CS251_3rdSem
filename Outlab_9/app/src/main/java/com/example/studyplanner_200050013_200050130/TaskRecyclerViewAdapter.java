package com.example.studyplanner_200050013_200050130;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<TasksModel> tasksModelArrayList;
    private Context context;

    // constructor
    public TaskRecyclerViewAdapter(ArrayList<TasksModel> tasksModelArrayList, Context context) {
        this.tasksModelArrayList = tasksModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        TasksModel tasksModel = tasksModelArrayList.get(position);
        holder.taskDate.setText(tasksModel.getTaskDate());
        holder.taskDesc.setText(tasksModel.getTaskDesc());
        holder.taskTime.setText(tasksModel.getTaskTime());
        holder.taskName.setText(tasksModel.getTaskTitle());
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return tasksModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView taskName, taskDesc, taskDate, taskTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            taskName = itemView.findViewById(R.id.idTVTaskName);
            taskDesc = itemView.findViewById(R.id.idTVTaskDescription);
            taskDate = itemView.findViewById(R.id.idTVTaskDate);
            taskTime = itemView.findViewById(R.id.idTVTaskTime);
        }
    }
}
