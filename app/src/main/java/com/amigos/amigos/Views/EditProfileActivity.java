package com.amigos.amigos.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.amigos.amigos.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity {
    EditText etName,etEmail,etMobile,etAddress;
    AppCompatButton btnProfile;
    ImageView imageView2;
    CircleImageView imgUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etAddress = findViewById(R.id.etAddress);
        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        btnProfile = findViewById(R.id.btnUpdateProfile);
        imageView2 = findViewById(R.id.imageView2);
        imgUser = findViewById(R.id.imgUser);

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}