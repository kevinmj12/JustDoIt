//fragment_make_todo.xml
//add todo to todo list
package com.example.justdoit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Calendar;

public class MakeTodoFragment extends Fragment {

    ImageButton createTodo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_make_todo, container, false);
        //MakeTodoBack버튼 클릭 시 전 페이지로 이동
        v.findViewById(R.id.MakeTodoBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            }
        });
        //MakeTodoDone버튼 클릭 시 name, deadlineMonth, deadlineDate, totalProgress, presentProgress, isDailyTodo를 입력받아 TodoListFragment로 전달
        v.findViewById(R.id.MakeTodoDone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) v.findViewById(R.id.MakeTodoName);

                //DatePickerFragment에서 받아온 deadlineMonth, deadlineDate 저장
            }
        });
        //DatePicker 클릭 시 DatePickerFragment로 이동
        v.findViewById(R.id.DateTime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });
        return v;

    }
}

