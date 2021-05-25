package com.amigos.amigos;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import com.amigos.amigos.AdapterClass.SpinnerAdapter;
import com.amigos.amigos.Views.DashBoardPageActivity;
import com.amigos.amigos.Views.RequestPageActivity;

import java.util.Timer;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class AddServiceActivity extends AppCompatActivity {


    String strselectedbrandname,strFuelType,strCar,strVarient;
    int strimagecra,inSpCar,inSpVarient;
    EditText etCarPlateNumber;
    AppCompatButton btnSubmit;
    Spinner stFuelTyep;
    private String[] contentArray = {"BMW", "FORD", "Maruti", "Mahindra"};
    private Integer[] imageArray = {R.drawable.bmw, R.drawable.scoda, R.drawable.marutisuzuki, R.drawable.honda};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        btnSubmit = findViewById(R.id.btnSubmit);
        etCarPlateNumber = findViewById(R.id.etCarPlateNumber);
        stFuelTyep = findViewById(R.id.spFuelType);

        final Spinner spBrand = (Spinner) findViewById(R.id.spinner);
        Spinner spCar = (Spinner) findViewById(R.id.spinnerCar);
        Spinner spVarient = (Spinner) findViewById(R.id.spinnerVarient);
        SpinnerAdapter adapters = new SpinnerAdapter(this, R.layout.spinner_layout, contentArray, imageArray);
        spBrand.setAdapter(adapters);
        spCar.setAdapter(adapters);
        spVarient.setAdapter(adapters);

        spBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code for setting the image based on the item clicked....here

                strselectedbrandname = contentArray[position];
                strimagecra = imageArray[position];


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spCar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code for setting the image based on the item clicked....here

                strCar = contentArray[position];
                inSpCar = imageArray[position];


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        spVarient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code for setting the image based on the item clicked....here

                strVarient = contentArray[position];
                inSpVarient = imageArray[position];


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        stFuelTyep.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code for setting the image based on the item clicked....here

                strFuelType = String.valueOf(stFuelTyep.getSelectedItem());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etCarPlateNumber.getText().toString().length() != 0) {
                    Toast.makeText(AddServiceActivity.this,strFuelType+" "+strCar+""+strVarient+""+strselectedbrandname+""+etCarPlateNumber.getText().toString(),Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(AddServiceActivity.this, DashBoardPageActivity.class);
                    intent.putExtra("brandname", strselectedbrandname);
                    intent.putExtra("picture", strimagecra);
                    startActivity(intent);

                } else {
                    Toast.makeText(AddServiceActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
}