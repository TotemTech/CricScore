package com.totem.cricscore;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateMatchScreen extends AppCompatActivity {


    private EditText dateAndTime;


    final Calendar myCalendar = Calendar.getInstance();
    private TextInputEditText dateTime;
    private TextInputLayout Check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_match_screen);
        setTitle("Create Match");

        dateTime = findViewById(R.id.Check1);
        Check = findViewById(R.id.Check);
        dateAndTime = findViewById(R.id.dateAndTime);
        //For Date and Time Picker
        dateAndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateMatchScreen.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        new TimePickerDialog(CreateMatchScreen.this, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                myCalendar.set(Calendar.MINUTE, minute);

                                updateLabel();
                            }
                        }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), false).show();
                        myCalendar.set(Calendar.YEAR, year);
                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }

        });

    }

    // updates the time and date in Date Picker
    private void updateLabel() {
        String myFormat = "EEE , dd/MM/yyyy HH:mm aa"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        Log.e("Time Selected ", sdf.format(myCalendar.getTime()));
        dateAndTime.setText(sdf.format(myCalendar.getTime()));
    }
}
