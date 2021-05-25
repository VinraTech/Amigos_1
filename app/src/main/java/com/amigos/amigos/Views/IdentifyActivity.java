package com.amigos.amigos.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.amigos.amigos.R;

public class IdentifyActivity extends AppCompatActivity {
    AppCompatButton btnCountinue;
    LinearLayout liBuyers, liSeller;
    String strUserType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify);

        btnCountinue = findViewById(R.id.btnContinue);
        liBuyers = findViewById(R.id.liBuyers);
        liSeller = findViewById(R.id.liSeller);

        liSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liBuyers.setBackground(getResources().getDrawable(R.drawable.identificationbackground));
                liSeller.setBackground(getResources().getDrawable(R.drawable.identificationlayoutsecond));

                strUserType = "ServiceCenter";
            }
        });

        liBuyers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liSeller.setBackground(getResources().getDrawable(R.drawable.identificationlayout));
                liBuyers.setBackground(getResources().getDrawable(R.drawable.identificationbackgroundsecond));
                strUserType = "Consumer";
            }
        });

        btnCountinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (strUserType.equals("ServiceCenter")) {
                    startActivity(new Intent(IdentifyActivity.this, LoginPageActivity.class));
                }
                if (strUserType.equals("Consumer")) {
                    startActivity(new Intent(IdentifyActivity.this, LoginPageActivity.class));
                }
                if(strUserType.equals("")) {
                    Toast.makeText(IdentifyActivity.this,"Select one to continue",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}