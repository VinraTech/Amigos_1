package com.amigos.amigos.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.amigos.amigos.R;

public class OurServicesDeatailActivity extends AppCompatActivity {
    AppCompatButton btnRequest;
    CheckBox cbLIcence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_services_deatail);

        btnRequest = findViewById(R.id.btnRequest);

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(OurServicesDeatailActivity.this);

                Toast.makeText(OurServicesDeatailActivity.this,"Coming soon",Toast.LENGTH_LONG).show();

               /*


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
                                                                 startActivity(new Intent(OurServicesDeatailActivity.this, OurServices.class));
                                                                 dialog.dismiss();
                                                             }

                                                         }
                                                     }
                );

                dialog.show();
                dialog.setCancelable(true);*/
            }
        });

    }
}