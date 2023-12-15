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


public class StreakRecyclerAdapter extends RecyclerView.Adapter<StreakRecyclerAdapter.ViewHolder> {

    private CardView cardStreak;
    ArrayList<DailyTodo> DailyTodoArray = new ArrayList<>();
    TextView todoName;
    TextView todoStreak;
    Context context;


    class ViewHolder extends RecyclerView.ViewHolder {


        ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            cardStreak = view.findViewById(R.id.card_streak);
            todoName = view.findViewById(R.id.streak_todo_name;
            todoStreak = view.findViewById(R.id.streak_streak);
        }

        void onBind(DailyTodo dailyTodo){
            todoName.setText(dailyTodo.getName());
            String streak = dailyTodo.getStreak();
            String _streak = "";
            for (int i = 0;  i < 10; i++){
                if (streak.charAt(i) == 'X'){
                    _streak += '□';
                }
                else{
                    _streak += '■';
                }
            }
            todoStreak.setText(_streak);
        }
    }
    void addItem(DailyTodo dailyTodo) {
        // 외부에서 item을 추가시킬 함수입니다.
        DailyTodoArray.add(dailyTodo);
    }

    public DailyTodoRecyclerAdapter(ArrayList<DailyTodo> DailyTodoDataArray, Context context) {
        this.DailyTodoArray = DailyTodoDataArray;
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
