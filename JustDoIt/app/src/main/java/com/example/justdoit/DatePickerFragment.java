package com.example.justdoit;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;
import java.util.zip.Inflater;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DATE);
        return new DatePickerDialog(getActivity(), this, year, month, date);
    }
    public void onDateSet(DatePicker datePicker, int yeat, int month, int date) {
        populateSetDate(yeat, month+1, date);
    }
    public void populateSetDate(int year, int month, int date) {
        //MakeTodoFragment의 deadlineMonth, deadlineDate에 저장
    }

}
