package com.amigos.amigos.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amigos.amigos.R;

public class MonthEarningActivity extends AppCompatActivity {
    LinearLayout liNawPayout,liNawPayout1,liBasePayout,liBasePayout1,liIncentive,liIncentive1,liDeduction,liDeduction1,liRefundable,liRefundable1;
    ImageView menuImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month_earning);

        liNawPayout = findViewById(R.id.liNewPayout);
        liNawPayout1 = findViewById(R.id.liNewPayout1);

        liBasePayout = findViewById(R.id.liBasePayout);
        liBasePayout1 = findViewById(R.id.liBasePayout1);

        liIncentive = findViewById(R.id.liIncentive);
        liIncentive1 = findViewById(R.id.liIncentive1);

        liDeduction = findViewById(R.id.liDeduction);
        liDeduction1 = findViewById(R.id.liDeduction1);

        liRefundable = findViewById(R.id.liRefundable);
        liRefundable1 = findViewById(R.id.liRefundable1);
        menuImage = findViewById(R.id.menuImage);
        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        liNawPayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liNawPayout.setVisibility(View.GONE);
                liNawPayout1.setVisibility(View.VISIBLE);
            }
        });
        liNawPayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liNawPayout1.setVisibility(View.GONE);
                liNawPayout.setVisibility(View.VISIBLE);
            }
        });

        liBasePayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liBasePayout.setVisibility(View.GONE);
                liBasePayout1.setVisibility(View.VISIBLE);
            }
        });
        liBasePayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liBasePayout1.setVisibility(View.GONE);
                liBasePayout.setVisibility(View.VISIBLE);
            }
        });
        liIncentive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liIncentive.setVisibility(View.GONE);
                liIncentive1.setVisibility(View.VISIBLE);
            }
        });
        liIncentive1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liIncentive1.setVisibility(View.GONE);
                liIncentive.setVisibility(View.VISIBLE);
            }
        });

        liDeduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liDeduction.setVisibility(View.GONE);
                liDeduction1.setVisibility(View.VISIBLE);
            }
        });
        liDeduction1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liDeduction1.setVisibility(View.GONE);
                liDeduction.setVisibility(View.VISIBLE);
            }
        });

        liRefundable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liRefundable.setVisibility(View.GONE);
                liRefundable1.setVisibility(View.VISIBLE);
            }
        });
        liRefundable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liRefundable1.setVisibility(View.GONE);
                liRefundable.setVisibility(View.VISIBLE);
            }
        });


    }
}