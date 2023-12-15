//fragment_make_todo.xml
//add todo to todo list
package com.example.justdoit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.justdoit.Retrofit.RetrofitAPI;
import com.example.justdoit.Retrofit.TodoModel;
import com.example.justdoit.Retrofit.RetrofitClient;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeTodoFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_make_todo, container, false);
        EditText name1 = (EditText) v.findViewById(R.id.MakeTodoName);
        Switch switch1 = (Switch) v.findViewById(R.id.IsDailyTodo);
        //MakeTodoBack버튼 클릭 시 전 페이지로 이동
        v.findViewById(R.id.MakeTodoBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(MakeTodoFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });
        //MakeTodoDone버튼 클릭 시 name, deadline, presentProgress, isDailyTodo를 입력받아 TodoListFragment로 전달
        v.findViewById(R.id.MakeTodoDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = name1.getText().toString();
                String deadline = new DatePickerFragment().deadline;
                boolean isDailyTodo = switch1.isChecked();
                //데이터 저장
                if(isDailyTodo) {
                    Call<List<TodoModel>> Todo = RetrofitClient.getApiService().createDailyTodo(
                            1,
                            name
                    );
                    Todo.enqueue(new Callback<List<TodoModel>>() {
                        @Override
                        public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                            if(response.isSuccessful()) {
                                List<TodoModel> todo = response.body();
                            }
                        }
                        @Override
                        public void onFailure(Call<List<TodoModel>> call, Throwable t) {
                            System.out.println("error");
                        }
                    });
                }else {
                    Call<List<TodoModel>> Todo = RetrofitClient.getApiService().createTodo(
                            new RetrofitAPI.PostTodo(
                                    1,
                                    name,
                                    deadline
                            )
                    );
                    Todo.enqueue(new Callback<List<TodoModel>>() {
                        @Override
                        public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                            if(response.isSuccessful()) {
                                List<TodoModel> todo = response.body();
                                name1.setText(todo.get(0).getTodoName());
                            }
                        }
                        @Override
                        public void onFailure(Call<List<TodoModel>> call, Throwable t) {
                            System.out.println("error");
                        }
                    });
                }
                //mainActivity로 이동
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(MakeTodoFragment.this).commit();
                fragmentManager.popBackStack();
            }
        });
        //DatePicker 클릭 시 DatePickerFragment로 이동
        v.findViewById(R.id.DateTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });
        return v;

    }
}

