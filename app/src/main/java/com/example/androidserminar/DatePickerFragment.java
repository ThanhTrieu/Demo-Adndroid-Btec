package com.example.androidserminar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    @Nullable
    private  TextView tvShowDate;
    @Nullable
    private EditText edtShowDate;
    public DatePickerFragment(@Nullable TextView _tvShowDate, @Nullable EditText _edtShowDate){
        this.tvShowDate = _tvShowDate;
        this.edtShowDate = _edtShowDate;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        populateSetDate(year, month+1, dayOfMonth);
    }
    public void populateSetDate(int year, int month, int day) {
        if (tvShowDate != null) {
            tvShowDate.setText(day + "/" + month + "/" + year);
        }
        if (edtShowDate != null){
            edtShowDate.setText(day + "/" + month + "/" + year);
        }
    }
}
