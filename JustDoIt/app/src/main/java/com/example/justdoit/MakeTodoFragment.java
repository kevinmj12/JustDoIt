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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justdoit.Retrofit.Result;
import com.example.justdoit.Retrofit.RetrofitAPI;
import com.example.justdoit.Retrofit.TodoModel;
import com.example.justdoit.Retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class MakeTodoFragment extends Fragment {
    Call<Result> call;
    EditText name1;
    Switch switch1;
    Button deadline1;
    DatePickerFragment datePickerFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_make_todo, container, false);
        name1 = (EditText) v.findViewById(R.id.MakeTodoName);
        switch1 = (Switch) v.findViewById(R.id.IsDailyTodo);
        deadline1 = (Button) v.findViewById(R.id.DateTime);
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
                String deadline = deadline1.getText().toString();
                boolean isDailyTodo = switch1.isChecked();
                //데이터 저장
                if(isDailyTodo) {
//                    Call<TodoModel> Todo = RetrofitClient.getApiService().createDailyTodo(
//                            1,
//                            name
//                    );
//                    Todo.enqueue(new Callback<TodoModel>() {
//                        @Override
//                        public void onResponse(Call<TodoModel> call, Response<TodoModel> response) {
//                            if(response.isSuccessful()) {
//                                TodoModel todo = response.body();
//                            }
//                        }
//                        @Override
//                        public void onFailure(Call<TodoModel> call, Throwable t) {
//                            System.out.println("error");
//                        }
//                    });
                }else {
                    call = RetrofitClient.getApiService().createTodo("1", name, deadline);
                    call.enqueue(new Callback<Result>() {
                        @Override
                        public void onResponse(Call<Result> call, Response<Result> response) {
                            if (response.code()==200){
                                Result result = response.body();
                                if (result.getMsg() == "success"){
                                    Toast.makeText(getActivity(), "To-Do 추가 완료", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else{
                                System.out.println(response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<Result> call, Throwable t) {
                            Log.e("fail", t.getMessage());
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
                datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "datePicker");
            }
        });
        return v;

    }
}
