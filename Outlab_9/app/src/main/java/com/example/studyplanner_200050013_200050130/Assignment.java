package com.example.studyplanner_200050013_200050130;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Assignment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Assignment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Assignment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Assignment.
     */
    // TODO: Rename and change types and number of parameters
    public static Assignment newInstance(String param1, String param2) {
        Assignment fragment = new Assignment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_assignment, container, false);
        // Inflate the layout for this fragment
        Button assignment_button = (Button) rootView.findViewById(R.id.button_assignment);

        ArrayList<TasksModel> tasksModelArrayList;
        DatabaseClass databaseClass;
        TaskRecyclerViewAdapter taskRecyclerViewAdapter;
        RecyclerView recyclerViewTasks;


        // initializing our all variables.
        tasksModelArrayList = new ArrayList<>();
        databaseClass = new DatabaseClass(getActivity().getApplicationContext());

        // getting our task array
        // list from db handler class.
        tasksModelArrayList = databaseClass.readTasks(3);

        // on below line passing our array lost to our adapter class.
        taskRecyclerViewAdapter = new TaskRecyclerViewAdapter(tasksModelArrayList, getContext());
        recyclerViewTasks = rootView.findViewById(R.id.idRVAssignments);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerViewTasks.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        recyclerViewTasks.setAdapter(taskRecyclerViewAdapter);

        assignment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddTask.class);
                intent.putExtra("name", 3);
                getActivity().startActivity(intent);
            }
        });

        return rootView;
    }

//    public void addTask(View v)
//    {
//        Intent intent = new Intent(getActivity(), AddTask.class);
//        getActivity().startActivity(intent);
//    }

}