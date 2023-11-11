package com.example.justdoit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment todoListFragment;
    Fragment dailyTodoCalendarFragment;
    Fragment settingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fregment 생성
        todoListFragment = new TodoListFragment();
        dailyTodoCalendarFragment = new DailyTodoCalendarFragment();
        settingFragment = new SettingFragment();

        // BottomNavigationView 설정
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // 처음 실행 시 To-Do List 화면 출력
        getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, todoListFragment).commit();
        // BottomNavigationView의 각 버튼 클릭 시 화면 전환
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.todoList){
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, todoListFragment).commit();
                    return true;
                }
                else if (item.getItemId() == R.id.dailyTodoCalendar){
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, dailyTodoCalendarFragment).commit();
                    return true;
                }
                else if (item.getItemId() == R.id.userSetting){
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainLayout, settingFragment).commit();
                    return true;
                }
                else{
                    return false;
                }
            }
        });
    }
}