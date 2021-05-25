package com.amigos.amigos.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amigos.amigos.R;

public class RunningTaskActivity extends AppCompatActivity {
    LinearLayout liTicket,liTicket1;
    ImageView menuImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_task);
        menuImage = findViewById(R.id.menuImage);

        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        liTicket = findViewById(R.id.liTicket);
        liTicket1 = findViewById(R.id.liTicket1);
        liTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liTicket.setVisibility(View.GONE);
                liTicket1.setVisibility(View.VISIBLE);

            }
        });
        liTicket1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liTicket.setVisibility(View.VISIBLE);
                liTicket1.setVisibility(View.GONE);
            }
        });
    }
}