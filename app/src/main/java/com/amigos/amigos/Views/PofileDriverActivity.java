package com.amigos.amigos.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.amigos.amigos.ModelClass.DriverDetailsMOdelClass;
import com.amigos.amigos.R;
import com.amigos.amigos.Retrofit.ApiClient;
import com.amigos.amigos.Retrofit.ApiInterface;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PofileDriverActivity extends AppCompatActivity {

    SweetAlertDialog pDialog;
    TextView tvDriverMobile, tvDriverName, tvDL, tvDateOfBirth;
    public static final String MyPREFERENCES = "MyAmigoPrefs";
    SharedPreferences sharedpreferences;
    ApiInterface apiInterface;
    String strDriverId;
    SharedPreferences.Editor adderssEditior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pofile_driver);

        tvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        tvDriverMobile = findViewById(R.id.tvDriverMobile);
        tvDriverName = findViewById(R.id.tvDriverName);
        tvDL = findViewById(R.id.tvDL);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        adderssEditior = sharedpreferences.edit();
        apiInterface = ApiClient.getPostService().create(ApiInterface.class);
        strDriverId = sharedpreferences.getString("DRIVER_ID", "");
        getDriverData();


    }

    public void getDriverData() {
        Map<String, String> prams = new HashMap<String, String>();
        prams.put("driver_id", String.valueOf(strDriverId));
        Call<DriverDetailsMOdelClass> profile;
        profile = apiInterface.getDriverDetails(prams);
        pDialog = new SweetAlertDialog(PofileDriverActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setCancelable(false);
        pDialog.show();
        profile.enqueue(new Callback<DriverDetailsMOdelClass>() {
            @Override
            public void onResponse(Call<DriverDetailsMOdelClass> call, Response<DriverDetailsMOdelClass> response) {
                pDialog.dismiss();
                try {
                    if (response.body() != null) {
                        pDialog.dismiss();
                        if (response.body().getStatus() == 200) {

                            tvDriverName.setText(response.body().getResult().getDriverName());
                            tvDateOfBirth.setText(response.body().getResult().getDateOfBoth());
                            tvDL.setText(response.body().getResult().getDrivingLicense());
                            tvDriverMobile.setText(response.body().getResult().getDriverMobile());


                        } else {
                            new SweetAlertDialog(PofileDriverActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText(response.body().getMessage())
                                    .show();
                        }
                    } else {

                        pDialog.dismiss();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DriverDetailsMOdelClass> call, Throwable t) {
                pDialog.dismiss();

            }
        });
    }
}