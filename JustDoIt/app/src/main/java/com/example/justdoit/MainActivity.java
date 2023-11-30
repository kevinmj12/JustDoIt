package com.example.justdoit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment todoListFragment;
    Fragment dailyTodoListFragment;
    Fragment dailyTodoCalendarFragment;
    Fragment settingFragment;
    Fragment makeTodoFragment;

    ImageButton createTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fragment 생성
        todoListFragment = new TodoListFragment();
        dailyTodoListFragment = new DailyTodoListFragment();
        dailyTodoCalendarFragment = new DailyTodoCalendarFragment();
        settingFragment = new SettingFragment();
        makeTodoFragment = new MakeTodoFragment();

        // BottomNavigationView 설정
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // 처음 실행 시 To-Do List 화면 출력
        getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, todoListFragment).commit();

        // BottomNavigationView의 각 버튼 클릭 시 화면 전환
        // 상황에 따라 To-Do 추가버튼을 활성화하거나 비활성화해야 함
        createTodo = findViewById(R.id.createTodo);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.todoList) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, todoListFragment).commit();
                    activateImageButton(createTodo, true);
                    return true;
                } else if (item.getItemId() == R.id.dailyTodoList) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, dailyTodoListFragment).commit();
                    activateImageButton(createTodo, true);
                    return true;
                } else if (item.getItemId() == R.id.dailyTodoCalendar) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, dailyTodoCalendarFragment).commit();
                    activateImageButton(createTodo, false);
                    return true;
                } else if (item.getItemId() == R.id.userSetting) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, settingFragment).commit();
                    activateImageButton(createTodo, false);
                    return true;
                } else {
                    return false;
                }
            }
        });

        // To-Do 추가 버튼 클릭 시 화면 전환
        createTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, makeTodoFragment).commit();
            }
        });
    }

    // 이미지버튼을 활성화하거나 비활성화하는 함수
    public void activateImageButton(ImageButton btn, Boolean bool){
        if (bool){
            btn.setEnabled(true);
            btn.setVisibility(View.VISIBLE);
        }
        else{
            btn.setEnabled(false);
            btn.setVisibility(View.INVISIBLE);
        }
    }
}