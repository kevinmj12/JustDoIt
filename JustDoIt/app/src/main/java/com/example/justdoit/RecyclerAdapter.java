package com.example.justdoit;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.justdoit.Retrofit.RetrofitClient;
import com.example.justdoit.Retrofit.TodoModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private CardView cardTodo;
    ArrayList<Todo> TodoArray = new ArrayList<>();
    TextView todoName;
    TextView todoDeadline;
    TextView todoProgress;
    String userId;
    //    Context context;
    Context context;
    ImageButton pop_up_menu;


    class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            cardTodo = view.findViewById(R.id.card_todo);
            todoName = view.findViewById(R.id.todo_name);
            todoDeadline = view.findViewById(R.id.todo_deadline);
            todoProgress = view.findViewById(R.id.todo_progress);
            pop_up_menu = view.findViewById(R.id.pop_up_menu);
            pop_up_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                        @Override
                        public boolean onMenuItemClick(MenuItem item){
                            if(item.getItemId() == R.id.edit) {
                                //EditTodoFragment.java로 이동
                                Intent intent = new Intent(v.getContext(), EditTodoFragment.class);
                                v.getContext().startActivity(intent);
                            }else if(item.getItemId() == R.id.delete) {
                                //서버에서 Todo 삭제
                                Call<List<TodoModel>> call = RetrofitClient.getApiService().DeleteTodo(
                                        //user_id 받아오기
                                        1,
                                        todoName.getText().toString()
                                );
                                Toast.makeText(v.getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
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
            todoDeadline.setText(Integer.toString(todo.getDeadlineMonth())+"월 "+ Integer.toString(todo.getDeadlineDate())+"일");
            todoProgress.setText(Integer.toString(todo.getPercentProgress())+"%");
        }
    }
    void addItem(Todo todo) {
        // 외부에서 item을 추가시킬 함수입니다.
        TodoArray.add(todo);
    }

    public RecyclerAdapter(ArrayList<Todo> CrowdDataArray, Context context) {
        this.TodoArray = CrowdDataArray;
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
