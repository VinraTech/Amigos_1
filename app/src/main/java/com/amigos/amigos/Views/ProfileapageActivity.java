package com.amigos.amigos.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.amigos.amigos.R;

public class ProfileapageActivity extends AppCompatActivity {

    ImageView imgEdit;
    AppCompatButton btnyourvehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileapage);

        imgEdit = findViewById(R.id.imgEdit);

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileapageActivity.this, EditProfileActivity.class));            }
        });

    }
}