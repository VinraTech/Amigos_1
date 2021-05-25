package com.amigos.amigos.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.amigos.amigos.R;
import com.amigos.amigos.Views.CustomerDropActivity;
import com.amigos.amigos.Views.OurServices;

public class HomeFragment extends Fragment {
    LinearLayout liPick,liDrop;
CheckBox cbLIcence;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
/*

        liPick = view.findViewById(R.id.liPick);
        liDrop = view.findViewById(R.id.liDrop);
        liDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), CustomerDropActivity.class));
            }
        });

        liPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.language_layout);

                cbLIcence = dialog.findViewById(R.id.cbLicence);
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(layoutParams);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                cbLIcence.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                       @Override
                                                       public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                           if (cbLIcence.isChecked()){
                                                               startActivity(new Intent(getActivity(), OurServices.class));
                                                               dialog.dismiss();
                                                           }

                                                       }
                                                   }
                );

                dialog.show();
                dialog.setCancelable(true);
            }
        });
*/


        return view;
    }
}
