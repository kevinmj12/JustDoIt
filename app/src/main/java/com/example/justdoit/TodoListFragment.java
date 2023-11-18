package com.example.justdoit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class TodoListFragment extends Fragment {
    RecyclerView todoRecyclerView;
    RecyclerView dailyTodoRecyclerView;
    RecyclerAdapter todoRecyclerAdapter;
    RecyclerAdapter dailyTodoRecyclerAdapter;
    ArrayList<Todo> todoArrayList;
    ArrayList<Todo> dailyTodoArrayList;
    LinearLayoutManager linearLayoutManager;
    LinearLayoutManager linearLayoutManager2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_todolist, container, false);
        Todo todo1 = new Todo(1, "소아키", 11, 20, 10, 100, false);
        Todo todo2 = new Todo(1, "소아키", 11, 20, 10, 100, false);
        Todo todo3 = new Todo(1, "소아키", 11, 20, 10, 100, false);
        Todo todo4 = new Todo(1, "소아키", 11, 20, 10, 100, false);
        todoArrayList = new ArrayList<>();
        dailyTodoArrayList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        linearLayoutManager2 = new LinearLayoutManager(getActivity().getApplicationContext());

        todoRecyclerView = v.findViewById(R.id.todoRecyclerView);
        todoRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(todoRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        todoRecyclerView.addItemDecoration(dividerItemDecoration);

        todoRecyclerAdapter = new RecyclerAdapter(todoArrayList, getContext());
        todoRecyclerView.setAdapter(todoRecyclerAdapter);

        todoRecyclerAdapter.addItem(todo1);
        todoRecyclerAdapter.addItem(todo2);
        todoRecyclerAdapter.addItem(todo3);
        todoRecyclerAdapter.addItem(todo4);
        todoRecyclerAdapter.notifyDataSetChanged();


//        dailyTodoRecyclerView = v.findViewById(R.id.dailyTodoRecyclerView);
//        dailyTodoRecyclerView.setLayoutManager(linearLayoutManager2);
//
//        dailyTodoRecyclerAdapter = new RecyclerAdapter(dailyTodoArrayList, getContext());
//        dailyTodoRecyclerView.setAdapter(dailyTodoRecyclerAdapter);
//
//        dailyTodoRecyclerAdapter.addItem(todo1);
//        dailyTodoRecyclerAdapter.addItem(todo2);
//        dailyTodoRecyclerAdapter.addItem(todo3);
//        dailyTodoRecyclerAdapter.addItem(todo4);
//        dailyTodoRecyclerAdapter.notifyDataSetChanged();




//        todoArrayList.add(todo1);







        return v;
    }
}