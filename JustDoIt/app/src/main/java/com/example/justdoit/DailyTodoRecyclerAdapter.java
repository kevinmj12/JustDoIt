package com.example.justdoit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class DailyTodoRecyclerAdapter extends RecyclerView.Adapter<DailyTodoRecyclerAdapter.ViewHolder> {

    private CardView cardTodo;
    ArrayList<DailyTodo> DailyTodoArray = new ArrayList<>();
    TextView todoName;
    TextView todoProgress;
    String userId;
    Context context;
    ImageButton pop_up_menu;


    class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            cardTodo = view.findViewById(R.id.card_todo);
            todoName = view.findViewById(R.id.todo_name);
            todoProgress = view.findViewById(R.id.todo_progress);
            pop_up_menu = view.findViewById(R.id.pop_up_menu);
            pop_up_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                        @Override
                        public boolean onMenuItemClick(MenuItem item){
                            return false;
                        }
                    });
                    popupMenu.inflate(R.menu.popup_menu);
                    popupMenu.show();
                }
            });
        }

        void onBind(DailyTodo dailyTodo){
            todoName.setText(dailyTodo.getName());
            todoProgress.setText(Integer.toString(dailyTodo.getPresentProgress())+"%");
        }
    }
    void addItem(DailyTodo dailyTodo) {
        // 외부에서 item을 추가시킬 함수입니다.
        DailyTodoArray.add(dailyTodo);
    }

    public DailyTodoRecyclerAdapter(ArrayList<DailyTodo> CrowdDataArray, Context context) {
        this.DailyTodoArray = CrowdDataArray;
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
        viewHolder.onBind(DailyTodoArray.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return DailyTodoArray.size();
    }
}
