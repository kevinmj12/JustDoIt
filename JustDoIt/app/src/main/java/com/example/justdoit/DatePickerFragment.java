package com.example.justdoit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.zip.Inflater;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    public int year;
    public int month;
    public int date;
    public String deadline;
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        date = c.get(Calendar.DATE);
        deadline = year + "-" + month + "-" + date;
        return new DatePickerDialog(getActivity(), this, year, month, date);
    }
    public void onDateSet(DatePicker datePicker, int yeat, int month, int date) {
        populateSetDate(yeat, month+1, date);
    }
    public void populateSetDate(int year, int month, int date) {
        //make_todo에 있는 DateTime 버튼에 선택한 날짜를 넣어줌
        Button dateTime = (Button) getActivity().findViewById(R.id.DateTime);
        dateTime.setText(year + "-" + month + "-" + date);
    }

}
