package com.amigos.amigos.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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

public class RequestPageActivity extends AppCompatActivity {
    EditText etName,etEmail,etMobile,etAddress,etCarModelNumber,etCarNumber;
    Spinner spService;
    String strServiceType;
    Button btnSubmit;
    String valid_email = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ApiInterface apiInterface;
    public static final String MyPREFERENCES = "MyAmigoPrefs";
    SharedPreferences.Editor adderssEditior;
    SharedPreferences sharedpreferences;
    SweetAlertDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_page);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        adderssEditior = sharedpreferences.edit();


        apiInterface = ApiClient.getPostService().create(ApiInterface.class);

        etAddress = findViewById(R.id.etAddress);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etCarModelNumber = findViewById(R.id.etCarNumber);
        etMobile = findViewById(R.id.etMobile);
        etCarNumber = findViewById(R.id.etCarNumber);
        spService= findViewById(R.id.spServiceType);
        btnSubmit = findViewById(R.id.btnSubmit);

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

        spService.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code for setting the image based on the item clicked....here


                strServiceType = String.valueOf(spService.getSelectedItem());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etName.getText().toString().length() != 0 && etMobile.getText().toString().length() != 0 && etCarNumber.getText().toString().length() != 0 && etCarModelNumber.getText().toString().length() != 0) {
                    if (etMobile.getText().toString().trim().length()==10) {
                        pDialog = new SweetAlertDialog(RequestPageActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setCancelable(false);
                        pDialog.show();

                        registration();

                        Toast.makeText(RequestPageActivity.this,strServiceType,Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid mobile number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RequestPageActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    public void registration() {

        RequestBody consumerEmail = RequestBody.create(MediaType.parse("text/plain"), etEmail.getText().toString());
        RequestBody consumerMobile = RequestBody.create(MediaType.parse("text/plain"), etMobile.getText().toString());
        RequestBody consumerName = RequestBody.create(MediaType.parse("text/plain"), etName.getText().toString());
        RequestBody consumerCarNumber = RequestBody.create(MediaType.parse("text/plain"), etCarNumber.getText().toString());
        RequestBody consumerCarModelNumber = RequestBody.create(MediaType.parse("text/plain"), etCarModelNumber.getText().toString());
        RequestBody consumerAddress = RequestBody.create(MediaType.parse("text/plain"), etAddress.getText().toString());
        RequestBody serviceTYpe = RequestBody.create(MediaType.parse("text/plain"), strServiceType);


        Call<RegistrationModalClass> register = apiInterface.consumerRequest(consumerEmail, consumerMobile, consumerName, consumerCarNumber,consumerCarModelNumber, serviceTYpe,consumerAddress);

        register.enqueue(new Callback<RegistrationModalClass>() {
            @Override
            public void onResponse(Call<RegistrationModalClass> call, Response<RegistrationModalClass> response) {
                pDialog.dismiss();

                try {

                    if (response.body().getStatus()==200 && response.body().getMessage().equalsIgnoreCase("Success!")) {
                        new SweetAlertDialog(RequestPageActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("Request Successfull")
                                .setContentText(response.body().getMessage())
                                .setConfirmText("Ok")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        startActivity(new Intent(RequestPageActivity.this, DashBoardPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                                        sDialog.dismissWithAnimation();
                                    }
                                })
                                .show();

                    }
                    else {
                        new SweetAlertDialog(RequestPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Please try again!")
                                .setContentText(response.body().getMessage())
                                .show();
                    }


                    if (response.code() == 400) {
                        new SweetAlertDialog(RequestPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Please try again!")
                                .setContentText(response.body().getMessage())
                                .show();
                        Toast.makeText(RequestPageActivity.this, "Please 1 your Internet Connection", Toast.LENGTH_LONG).show();
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
                new SweetAlertDialog(RequestPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Please try again!")
                        .setContentText(t.getMessage())
                        .show();

            }
        });
    }

}