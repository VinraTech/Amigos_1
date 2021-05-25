package com.amigos.amigos.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.amigos.amigos.R;

public class ServicePickUpActivity extends AppCompatActivity {
    ImageView menuImage;
    LinearLayout liReachedtask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report5);
        menuImage = findViewById(R.id.menuImage);
        liReachedtask = findViewById(R.id.liReachedtask);

        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        liReachedtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServicePickUpActivity.this, PickUpActivity.class));

            }
        });
    }
}