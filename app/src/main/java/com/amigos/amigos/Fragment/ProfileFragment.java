package com.amigos.amigos.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import com.amigos.amigos.R;
import com.amigos.amigos.Views.CustomerDropActivity;
import com.amigos.amigos.Views.EditProfileActivity;
import com.amigos.amigos.Views.OurServicesDeatailActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    ImageView imgEdit;
    TextView tvName,tvEmail,tvMobile,tvAddress;
    AppCompatButton btnyourvehicle;
    CircleImageView profile;
    ImageView header_cover_image;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imgEdit = view.findViewById(R.id.imgEdit);
        tvName = view.findViewById(R.id.tvName);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvMobile = view.findViewById(R.id.tvMobile);
        tvAddress = view.findViewById(R.id.tvAddress);
        profile = view.findViewById(R.id.profile);
        header_cover_image = view.findViewById(R.id.header_cover_image);


        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));            }
        });

        return view;
    }
}
