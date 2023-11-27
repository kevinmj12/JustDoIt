package com.example.justdoit;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.justdoit.Retrofit.RetrofitAPI;
import com.example.justdoit.Retrofit.RetrofitClient;
import com.example.justdoit.Retrofit.TestModel;
import com.example.justdoit.Retrofit.TodoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.POST;

import javax.xml.transform.Result;

public class DailyTodoListFragment extends Fragment {
    TextView testTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_daily_todo_list, container, false);

        testTextView = v.findViewById(R.id.testTextView1);

        Call<List<TodoModel>> call = RetrofitClient.getApiService().getTodo(1);
        call.enqueue(new Callback<List<TodoModel>>() {
            @Override
            public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                if (response.isSuccessful()){
                    List<TodoModel> data = response.body();
                    testTextView.setText(data.get(0).getTodoName());
                }
            }

            @Override
            public void onFailure(Call<List<TodoModel>> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });

        return v;
    }
}