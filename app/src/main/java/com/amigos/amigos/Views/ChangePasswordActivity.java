package com.amigos.amigos.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.amigos.amigos.ModelClass.ChangePasswordModelClass;
import com.amigos.amigos.ModelClass.DriverLoginModelClass;
import com.amigos.amigos.R;
import com.amigos.amigos.Retrofit.ApiClient;
import com.amigos.amigos.Retrofit.ApiInterface;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText etOldPassword,etNewPassword,etConfirmPassword;
    AppCompatButton btnChangeSubmit;
    ApiInterface apiInterface;
    public static final String MyPREFERENCES = "MyAmigoPrefs";
    SharedPreferences.Editor adderssEditior;
    SharedPreferences sharedpreferences;
    String strCustomerId;
    SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        adderssEditior = sharedpreferences.edit();


        apiInterface = ApiClient.getPostService().create(ApiInterface.class);

        strCustomerId = sharedpreferences.getString("CONSUMER_ID", "");

        etConfirmPassword = findViewById(R.id.etConfirmpassword);
        etNewPassword  = findViewById(R.id.etNewPasword);
        etOldPassword = findViewById(R.id.etOldPassword);
        btnChangeSubmit = findViewById(R.id.btnChangeSubmit);


        btnChangeSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etOldPassword.getText().toString().length() != 0 && etNewPassword.getText().toString().length() != 0 && etConfirmPassword.getText().toString().length() != 0) {

                    if (etNewPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {
                        pDialog = new SweetAlertDialog(ChangePasswordActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setCancelable(false);
                        pDialog.show();
                        changePassword();

                    }
                    else {
                        Toast.makeText(ChangePasswordActivity.this, "Password are not match", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(ChangePasswordActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();

                }
            }
        });



    }
    public void changePassword(){
        RequestBody oldPassword = RequestBody.create(MediaType.parse("text/plain"), etOldPassword.getText().toString());
        RequestBody newPasswordPassword = RequestBody.create(MediaType.parse("text/plain"), etNewPassword.getText().toString());
        RequestBody consumerId = RequestBody.create(MediaType.parse("text/plain"), strCustomerId);

        final Call<ChangePasswordModelClass> register = apiInterface.changePassword(consumerId, oldPassword, newPasswordPassword);

        register.enqueue(new Callback<ChangePasswordModelClass>() {
            @Override
            public void onResponse(Call<ChangePasswordModelClass> call, Response<ChangePasswordModelClass> response) {
                pDialog.dismiss();

                try {

                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200 && response.body().getMessage().equalsIgnoreCase("Password changed Sucessfully!")) {
                            new SweetAlertDialog(ChangePasswordActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Successfull")
                                    .setContentText(response.body().getMessage())
                                    .setConfirmText("Ok")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                           /* startActivity(new Intent(ChangePasswordActivity.this, DashBoardPageActivity.class));
                                            finish();*/
                                            startActivity(new Intent(ChangePasswordActivity.this, DashBoardPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                            finish();
                                           /* adderssEditior.putString("DRIVER_VERIFY", "verify");
                                            adderssEditior.putString("LOGIN_DATE", currentDate);
                                            adderssEditior.putString("DRIVER_LOGIN_DATE", "1");*/

                                            //adderssEditior.commit();
                                        }
                                    })

                                    .show();

                        }
                       else {
                            new SweetAlertDialog(ChangePasswordActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Please try again!")
                                    .setContentText(response.body().getMessage())
                                    .show();
                        }
                    }


                    if (response.code() == 400) {
                        new SweetAlertDialog(ChangePasswordActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Please try again!")
                                .setContentText(response.body().getMessage())
                                .show();
                        Toast.makeText(ChangePasswordActivity.this, "Please 1 your Internet Connection", Toast.LENGTH_LONG).show();
                    } else {
                        //  Toast.makeText(LoginPageActivity.this, "Please 2 your Internet Connection", Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordModelClass> call, Throwable t) {
                pDialog.dismiss();
                new SweetAlertDialog(ChangePasswordActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Please try again!")
                        .setContentText(t.getMessage())
                        .show();

            }
        });
    }
}