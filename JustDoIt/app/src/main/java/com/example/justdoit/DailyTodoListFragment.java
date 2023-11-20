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

        Call<List<TestModel>> call = RetrofitClient.getApiService().getData("1");
        call.enqueue(new Callback<List<TestModel>>() {
            @Override
            public void onResponse(Call<List<TestModel>> call, Response<List<TestModel>> response) {
                if (response.isSuccessful()){
                    List<TestModel> data = response.body();
                    testTextView.setText(data.get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<TestModel>> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });

        return v;
    }
}