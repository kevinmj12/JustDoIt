package com.example.justdoit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justdoit.Retrofit.Result;
import com.example.justdoit.Retrofit.RetrofitClient;
import com.example.justdoit.Retrofit.TodoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class TodoRecyclerAdapter extends RecyclerView.Adapter<TodoRecyclerAdapter.ViewHolder> {

    private CardView cardTodo;
    ArrayList<Todo> TodoArray = new ArrayList<>();
    TextView todoName;
    TextView todoDeadline;
    TextView todoProgress;
    String userId;
    //    Context context;
    Context context;
    ImageButton pop_up_menu;
    String todo_name;


    class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            cardTodo = view.findViewById(R.id.card_todo);
            todoName = view.findViewById(R.id.todo_name);
            todoDeadline = view.findViewById(R.id.todo_deadline);
            todoProgress = view.findViewById(R.id.todo_progress);
            pop_up_menu = view.findViewById(R.id.pop_up_menu);
            Call<List<TodoModel>> call2 = RetrofitClient.getApiService().getTodo(1);
            call2.enqueue(new Callback<List<TodoModel>>() {
                @Override
                public void onResponse(Call<List<TodoModel>> call2, retrofit2.Response<List<TodoModel>> response) {
                    if(response.isSuccessful()) {
                        List<TodoModel> todoModels = response.body();
                        for(TodoModel todoModel : todoModels) {
                            todo_name = todoModel.getTodoName();
                        }
                    }
                }
                @Override
                public void onFailure(Call<List<TodoModel>> call2, Throwable t) {
                    Toast.makeText(context, "불러오기에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            pop_up_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                        @Override
                        public boolean onMenuItemClick(MenuItem item){
                            if(item.getItemId() == R.id.edit) {
                                //RecyclerView에서 EditTodoFragment로 이동
                                FragmentManager fragmentManager = ((MainActivity)context).getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.mainLayout, new EditTodoFragment()).commit();
                                return true;
                            }else if(item.getItemId() == R.id.delete) {
                                //서버에서 Todo 삭제
                                System.out.println(todo_name);
                                Call<Result> call = RetrofitClient.getApiService().DeleteTodo(
                                        todo_name
                                );
                                call.enqueue(new Callback<Result>() {
                                    @Override
                                    public void onResponse(Call<Result> call, retrofit2.Response<Result> response) {
                                        System.out.println(response.code());
                                        if(response.isSuccessful()) {
                                            Result result = response.body();
                                            if (result.getMsg() == "success"){
                                                Toast.makeText(context, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                                            } else {
                                                System.out.println(response.code());
                                            }
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<Result> call, Throwable t) {
                                        Toast.makeText(context, "삭제에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else {
                                //Todo의 진행도 수정
                            }
                            return false;
                        }
                    });
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                }
            });
        }

        void onBind(Todo todo){
            todoName.setText(todo.getName());
            todoDeadline.setText("마감: "+Integer.toString(todo.getDeadlineMonth())+"월 "+ Integer.toString(todo.getDeadlineDate())+"일");
            todoProgress.setText(Integer.toString(todo.getPresentProgress())+"%");
        }
    }
    void addItem(Todo todo) {
        // 외부에서 item을 추가시킬 함수입니다.
        TodoArray.add(todo);
    }

    public TodoRecyclerAdapter(ArrayList<Todo> TodoDataArray, Context context) {
        this.TodoArray = TodoDataArray;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_todo, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.onBind(TodoArray.get(position));
        int todoId = TodoArray.get(position).getTodoID();

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return TodoArray.size();
    }
}
