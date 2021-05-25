package com.amigos.amigos.Views;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amigos.amigos.AdapterClass.DashAdapter;
import com.amigos.amigos.ModelClass.DashClass;
import com.amigos.amigos.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OurServices extends AppCompatActivity {
    DashAdapter dashAdapter;
    RecyclerView dashboardRecycle;
    private TextView dateView;
    List<DashClass> dashClasses;
    ImageView imgdate;
    private int mYear, mMonth, mDay;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_services);

        dashboardRecycle = findViewById(R.id.dashRecycler);
        dashClasses = new ArrayList<>();

        dashClasses.add(new DashClass("Raaj service center", "Available Slot - 01:22pm", R.drawable.first));
        dashClasses.add(new DashClass("Ak service center", "Available Slot - 08:22pm", R.drawable.seconds));
        dashClasses.add(new DashClass("Mohit service center", "Available Slot - 12:22pm", R.drawable.thirds));
        dashClasses.add(new DashClass("Bajaj service center", "Available Slot - 05:22pm", R.drawable.fours));
        dashClasses.add(new DashClass("TVS service center", "Available Slot - 11:22pm", R.drawable.fives));


        dashboardRecycle.setLayoutManager(new LinearLayoutManager(OurServices.this));
        dashboardRecycle.setHasFixedSize(true);
        dashboardRecycle.setNestedScrollingEnabled(false);
        dashAdapter = new DashAdapter(OurServices.this, dashClasses);
        dashboardRecycle.setAdapter(dashAdapter);

        dateView = (TextView) findViewById(R.id.textView3);
       ;
        imgdate = findViewById(R.id.imgdate);

        imgdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(OurServices.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                dateView.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }


}