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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.justdoit.Retrofit.RetrofitClient;
import com.example.justdoit.Retrofit.TodoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        todoArrayList = new ArrayList<>();
        dailyTodoArrayList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());

        todoRecyclerView = v.findViewById(R.id.todoRecyclerView);
        todoRecyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(todoRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        todoRecyclerView.addItemDecoration(dividerItemDecoration);

        todoRecyclerAdapter = new RecyclerAdapter(todoArrayList, getContext());
        todoRecyclerView.setAdapter(todoRecyclerAdapter);

        // 나중에 로그인 구현이 완료되면 user_id를 받아와 저장하면 됨
        Call<List<TodoModel>> call = RetrofitClient.getApiService().getTodo(1);
        call.enqueue(new Callback<List<TodoModel>>() {
            @Override
            public void onResponse(Call<List<TodoModel>> call, Response<List<TodoModel>> response) {
                if (response.isSuccessful()){
                    List<TodoModel> data = response.body();
                    for (int i = 0; i < data.size(); i++){
                        int todoID = data.get(i).getUserId();
                        String todoName = data.get(i).getTodoName();
                        int presentProgress = data.get(i).getPresentProgress();
                        String getDeadline = data.get(i).getDeadline();
                        int deadlineMonth = Integer.parseInt(getDeadline.substring(5,7));
                        int deadlineDate = Integer.parseInt(getDeadline.substring(8,10));
                        Todo todo = new Todo(0, todoName, deadlineMonth, deadlineDate, 100, presentProgress, false);

                        todoRecyclerAdapter.addItem(todo);
                    }
                    todoRecyclerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<TodoModel>> call, Throwable t) {
                Log.d("error", t.getMessage());
            }
        });
//        Todo todo1 = new Todo(0, "!", 3, 4, 100, 50, false);
//        todoRecyclerAdapter.addItem(todo1);
//        todoRecyclerAdapter.notifyDataSetChanged();

        return v;
    }
}