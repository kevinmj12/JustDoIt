package com.example.justdoit;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.justdoit.Retrofit.DailyTodoModel;
import com.example.justdoit.Retrofit.RetrofitClient;
import com.example.justdoit.Retrofit.TodoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DailyTodoCalendarFragment extends Fragment {

    RecyclerView streakRecyclerView;
    StreakRecyclerAdapter streakRecyclerAdapter;
    LinearLayoutManager linearLayoutManager;
    ArrayList<DailyTodo> streakDailyTodoArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_daily_todo_calendar, container, false);
        int user_id = 123;

        streakDailyTodoArrayList = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        streakRecyclerView = v.findViewById(R.id.streakRecyclerView);
        streakRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(streakRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        streakRecyclerView.addItemDecoration(dividerItemDecoration);

        streakRecyclerAdapter = new StreakRecyclerAdapter(streakDailyTodoArrayList, getContext());
        streakRecyclerView.setAdapter(streakRecyclerAdapter);

        Call<List<DailyTodoModel>> call = RetrofitClient.getApiService().getDailyTodo(user_id);
        call.enqueue(new Callback<List<DailyTodoModel>>() {
            @Override
            public void onResponse(Call<List<DailyTodoModel>> call, Response<List<DailyTodoModel>> response) {
                if (response.isSuccessful()){
                    List<DailyTodoModel> data = response.body();
                    for (int i = 0; i < data.size(); i++){
                        String todoName = data.get(i).getTodoName();
                        int presentProgress = data.get(i).getPresentProgress();
                        String streak = data.get(i).getStreak();
                        String startDate = data.get(i).getStartDate();
                        DailyTodo dailyTodo = new DailyTodo(todoName, presentProgress, streak, startDate);
                        streakRecyclerAdapter.addItem(dailyTodo);
                    }
                    streakRecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<DailyTodoModel>> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });


        return v;
    }
}