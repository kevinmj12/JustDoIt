package com.example.justdoit;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private CardView cardTodo;
    ArrayList<Todo> TodoArray = new ArrayList<>();
    TextView todoName;
    TextView todoDeadline;
    TextView todoProgress;
    String userId;
    //    Context context;
    Context context;


    class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            cardTodo = view.findViewById(R.id.card_todo);
            todoName = view.findViewById(R.id.todo_name);
            todoDeadline = view.findViewById(R.id.todo_deadline);
            todoProgress = view.findViewById(R.id.todo_progress);
        }

        void onBind(Todo todo){
            todoName.setText(todo.getName());
            todoDeadline.setText(Integer.toString(todo.getDeadlineMonth())+"월 "+ Integer.toString(todo.getDeadlineDate())+"일");
            todoProgress.setText(Integer.toString(todo.getPercentProgress())+"%");
//            image.setImageResource(crowdData.getImage());
//            textviewName.setText(crowdData.getName());
//            textviewIntroduction.setText(crowdData.getIntroduction());
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
