package com.amigos.amigos.Views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.amigos.amigos.ModelClass.RegistrationModalClass;
import com.amigos.amigos.R;
import com.amigos.amigos.Retrofit.ApiClient;
import com.amigos.amigos.Retrofit.ApiInterface;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPageActivity extends AppCompatActivity {
    AppCompatButton btnRegistration, verify;
    ImageView close;
    EditText etName, etEmail, etPassword, etConfirmPassword, etMobile;
    String valid_email = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    LinearLayout liRegistration;
    ApiInterface apiInterface;
    public static final String MyPREFERENCES = "MyAmigoPrefs";
    SharedPreferences.Editor adderssEditior;
    SharedPreferences sharedpreferences;
    SweetAlertDialog pDialog;
    String strIpAddress, strFcmToken, strLatitude, strLongitude, strDeviceID, strAppVersin,strMyCurrentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        adderssEditior = sharedpreferences.edit();


        apiInterface = ApiClient.getPostService().create(ApiInterface.class);

        strDeviceID = sharedpreferences.getString("Device_ID", "");
        strFcmToken = sharedpreferences.getString("FCM_TOKEN", "");
        strIpAddress = sharedpreferences.getString("IP_Address", "");
        strLatitude = sharedpreferences.getString("lat_tude", "");
        strLongitude = sharedpreferences.getString("longi_tude", "");
        strAppVersin = sharedpreferences.getString("APP_VERSION", "");
        strMyCurrentLocation = sharedpreferences.getString("MYCURRENT_LOCATION", "");


        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        etMobile = findViewById(R.id.etMobile);
        liRegistration = findViewById(R.id.liRegistration);


        btnRegistration = findViewById(R.id.btnRegistration);

        etEmail.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                // TODO Auto-generated method stub
                Is_Valid_Email(etEmail); // pass your EditText Obj here.
            }

            public void Is_Valid_Email(EditText edt) {
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid Email Address");
                    valid_email = null;
                } else if (isEmailValid(edt.getText().toString()) == false) {
                    edt.setError("Invalid Email Address");
                    valid_email = null;
                } else {
                    valid_email = edt.getText().toString();
                }
            }

            boolean isEmailValid(CharSequence email) {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                        .matches();
            } // end of TextWatcher (email)
        });

        etMobile.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                // TODO Auto-generated method stub
                isValidPhoneNumbers(etMobile); // pass your EditText Obj here.
            }

            public void isValidPhoneNumbers(EditText edt) {
                String strtext = edt.getText().toString();
                if (edt.getText().toString() == null) {
                    edt.setError("Invalid Mobile Number");
                    valid_email = null;
                } else if (isValidPhoneNumber(strtext) == false) {
                    edt.setError("Invalid Mobile Number");
                    valid_email = null;
                } else {
                    valid_email = edt.getText().toString();
                }
            }


            public final boolean isValidPhoneNumber(CharSequence target) {
                if (target == null || target.length() < 10 || target.length() > 13) {
                    return false;
                } else {
                    return android.util.Patterns.PHONE.matcher(target).matches();
                }

            }
        });


        liRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPageActivity.this, LoginPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                finish();
            }
        });


        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etName.getText().toString().length() != 0 && etMobile.getText().toString().length() != 0 && etPassword.getText().toString().length() != 0 && etConfirmPassword.getText().toString().length() != 0) {

                    if (etPassword.getText().toString().equals(etConfirmPassword.getText().toString())) {

                        if (etMobile.getText().toString().trim().length()==10) {

                            pDialog = new SweetAlertDialog(SignUpPageActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                            pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                            pDialog.setCancelable(false);
                            pDialog.show();

                            registration();
                           // mdeDialog();
                        } else {
                            Toast.makeText(getApplicationContext(), "Invalid mobile number", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(SignUpPageActivity.this, "Password are not match", Toast.LENGTH_LONG).show();

                    }

                } else {
                    Toast.makeText(SignUpPageActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();

                }


            }
        });
    }

    public void mdeDialog() {
        final Dialog dialog = new Dialog(SignUpPageActivity.this);
        dialog.setContentView(R.layout.activity_verification_page);
        close = dialog.findViewById(R.id.close);
        verify = dialog.findViewById(R.id.verify);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                startActivity(new Intent(SignUpPageActivity.this, LoginPageActivity.class));
            }
        });

        dialog.show();
        dialog.setCancelable(false);
    }

    public void registration() {

        RequestBody consumerEmail = RequestBody.create(MediaType.parse("text/plain"), etEmail.getText().toString());
        RequestBody consumerMobile = RequestBody.create(MediaType.parse("text/plain"), etMobile.getText().toString());
        RequestBody consumerName = RequestBody.create(MediaType.parse("text/plain"), etName.getText().toString());
        RequestBody consumerIpAddress = RequestBody.create(MediaType.parse("text/plain"), strIpAddress);
        RequestBody consumerPassword = RequestBody.create(MediaType.parse("text/plain"), etPassword.getText().toString());
        RequestBody consumerFCMtoken = RequestBody.create(MediaType.parse("text/plain"), strFcmToken);
        RequestBody consumerAddress = RequestBody.create(MediaType.parse("text/plain"), strMyCurrentLocation);
        RequestBody userType = RequestBody.create(MediaType.parse("text/plain"), "Credential");
        RequestBody longitude = RequestBody.create(MediaType.parse("text/plain"), strLongitude);
        RequestBody latitude = RequestBody.create(MediaType.parse("text/plain"), strLatitude);
        RequestBody deviceId = RequestBody.create(MediaType.parse("text/plain"), strDeviceID);
        RequestBody appVersion = RequestBody.create(MediaType.parse("text/plain"), strAppVersin);

        Call<RegistrationModalClass> register = apiInterface.consumerRegistration(consumerEmail, consumerMobile, consumerName, consumerPassword,userType, consumerIpAddress, consumerFCMtoken, longitude,latitude,deviceId,appVersion,consumerAddress);

        register.enqueue(new Callback<RegistrationModalClass>() {
            @Override
            public void onResponse(Call<RegistrationModalClass> call, Response<RegistrationModalClass> response) {
                pDialog.dismiss();

                try {

                        if (response.body().getStatus()==200 && response.body().getMessage().equalsIgnoreCase("Success!")) {
                            new SweetAlertDialog(SignUpPageActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Registration Successfull")
                                    .setContentText("Enter otp from given your mobile")
                                    .setConfirmText("Ok")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            mdeDialog();
                                            sDialog.dismissWithAnimation();
                                        }
                                    })
                                    .show();

                        }
                        else {
                            new SweetAlertDialog(SignUpPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Please try again!")
                                    .setContentText(response.body().getMessage())
                                    .show();
                        }


                    if (response.code() == 400) {
                        new SweetAlertDialog(SignUpPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Please try again!")
                                .setContentText(response.body().getMessage())
                                .show();
                        Toast.makeText(SignUpPageActivity.this, "Please 1 your Internet Connection", Toast.LENGTH_LONG).show();
                    } else {
                       // Toast.makeText(SignUpPageActivity.this, "Please 2 your Internet Connection", Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<RegistrationModalClass> call, Throwable t) {
              pDialog.dismiss();
                new SweetAlertDialog(SignUpPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Please try again!")
                        .setContentText(t.getMessage())
                        .show();

            }
        });
    }
}