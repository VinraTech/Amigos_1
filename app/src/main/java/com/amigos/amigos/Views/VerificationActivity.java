package com.amigos.amigos.Views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.amigos.amigos.ModelClass.DriverLoginModelClass;
import com.amigos.amigos.ModelClass.RegistrationModalClass;
import com.amigos.amigos.R;
import com.amigos.amigos.Retrofit.ApiClient;
import com.amigos.amigos.Retrofit.ApiInterface;
import com.amigos.amigos.Storage.SharePrefernce;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity {
    ImageView menuImage;
    LinearLayout submitDetails,liVerification;
    LinearLayout liTakeSelfi;
    EditText etTemp;
    Uri resultUri;
    Switch chShower, chCharger, chPowerBank, chUniForm, chBagPack, chSwippingMachine, chIdCard;
    String strShower="No", strCharger="No", strPowerBank="No", strUniform="No", strBagPack="No", strSwippingMachine="No", strIdCard="No", strTemprature="0.00";
    ImageView imguser;
    MultipartBody.Part filePart;
    File imgFile;
    String strDriverId;
    private static final int CAMERA_REQUEST = 1888;

    public static final String MyPREFERENCES = "MyAmigoPrefs";
    SharedPreferences sharedpreferences;
    ApiInterface apiInterface;
    String strIsBack, userChoosenTask;
    SweetAlertDialog pDialog;
    SharedPreferences.Editor adderssEditior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report6);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        adderssEditior = sharedpreferences.edit();
        apiInterface = ApiClient.getPostService().create(ApiInterface.class);
        strDriverId = sharedpreferences.getString("DRIVER_ID", "");

        menuImage = findViewById(R.id.menuImage);
        etTemp = findViewById(R.id.etTemp);
        liTakeSelfi = findViewById(R.id.liTakeSelfi);
        imguser = findViewById(R.id.imguser);
        chBagPack = findViewById(R.id.chBagPack);
        chCharger = findViewById(R.id.chCharger);
        chIdCard = findViewById(R.id.chIdCard);
        chPowerBank = findViewById(R.id.chPowerBank);
        chShower = findViewById(R.id.chShower);
        chSwippingMachine = findViewById(R.id.chSwippingMachine);
        chUniForm = findViewById(R.id.chUniform);
        liVerification = findViewById(R.id.liVerification);

        strIsBack = getIntent().getStringExtra("isback");
        if ((strIsBack != null && strIsBack.equalsIgnoreCase("true"))) {
            menuImage.setVisibility(View.VISIBLE);
            liVerification.setVisibility(View.GONE);
        } else {
            menuImage.setVisibility(View.GONE);
            liVerification.setVisibility(View.VISIBLE);
        }
        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        submitDetails = findViewById(R.id.submitDetails);
        submitDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //SharePrefernce.setSharePrefernceBoolean(VerificationActivity.this, "driverlogin", true);
              // if (etTemp.getText().length() == 0) {
                    strTemprature = etTemp.getText().toString().trim();
                    pDialog = new SweetAlertDialog(VerificationActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                    pDialog.setCancelable(false);
                    pDialog.show();
                    driverVerification();
              /*  } else {
                    Toast.makeText(VerificationActivity.this, "Enter your body temprature", Toast.LENGTH_LONG).show();
                }*/


            }
        });

        liTakeSelfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final CharSequence[] items = {"Take Photo", "Cancel"};
                AlertDialog.Builder builder = new AlertDialog.Builder(VerificationActivity.this);
                builder.setTitle("Add Photo!");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (items[item].equals("Take Photo")) {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);

                        } else if (items[item].equals("Cancel")) {
                            dialog.dismiss();
                        }
                    }
                });
                builder.show();
            }
        });

        chShower.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    strShower = "Yes";

                } else {
                    strShower = "No";

                }
            }
        });
        chUniForm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    strUniform = "Yes";

                } else {
                    strUniform = "No";

                }
            }
        });
        chSwippingMachine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    strSwippingMachine = "Yes";

                } else {
                    strSwippingMachine = "No";

                }
            }
        });

        chPowerBank.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    strPowerBank = "Yes";

                } else {
                    strPowerBank = "No";

                }
            }
        });
        chIdCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    strIdCard = "Yes";

                } else {
                    strIdCard = "No";

                }
            }
        });
        chCharger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    strCharger = "Yes";

                } else {
                    strCharger = "No";

                }
            }
        });
        chBagPack.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    strBagPack = "Yes";

                } else {
                    strBagPack = "No";

                }
            }
        });
    }


    private void cameraIntent() {
        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(VerificationActivity.this);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imguser.setImageBitmap(photo);
            imguser.setVisibility(View.VISIBLE);


            // CALL THIS METHOD TO GET THE URI FROM THE BITMAP
            Uri tempUri = getImageUri(getApplication(), photo);

            // CALL THIS METHOD TO GET THE ACTUAL PATH
            imgFile = new File(getRealPathFromURI(tempUri));

            Log.d("sdfsdfsdfdsf", imgFile.getAbsolutePath());
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);


       /* super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
          //  imguser.setImageBitmap(photo);
          //  imguser.setVisibility(View.VISIBLE);
            resultUri = Uri.parse(data.getData().getPath());
            imgFile = new File(resultUri.getPath());
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imguser.setVisibility(View.VISIBLE);
            imguser.setImageBitmap(myBitmap);

        }*/
    }

    /* @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
         super.onActivityResult(requestCode, resultCode, data);
         if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
             CropImage.ActivityResult result = CropImage.getActivityResult(data);
             if (resultCode == RESULT_OK) {
                 resultUri = result.getUri();
                 imgFile = new File(resultUri.getPath());
                 if (imgFile.exists())
                 {
                     Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                     imguser.setVisibility(View.VISIBLE);
                     imguser.setImageBitmap(myBitmap);

                 }

             }
             else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                 Exception error = result.getError();
             }
         }
     }*/
    public void driverVerification() {
        if (imgFile != null) {
            filePart = MultipartBody.Part.createFormData("driverimageUrl", imgFile.getName(), RequestBody.create(MediaType.parse("image/*"), imgFile));
            RequestBody driver = RequestBody.create(MediaType.parse("text/plain"), strDriverId);
            RequestBody shower = RequestBody.create(MediaType.parse("text/plain"), strShower);
            RequestBody charger = RequestBody.create(MediaType.parse("text/plain"), strCharger);
            RequestBody powerbank = RequestBody.create(MediaType.parse("text/plain"), strPowerBank);
            RequestBody uniform = RequestBody.create(MediaType.parse("text/plain"), strUniform);
            RequestBody bagPack = RequestBody.create(MediaType.parse("text/plain"), strBagPack);
            RequestBody swipingMachine = RequestBody.create(MediaType.parse("text/plain"), strSwippingMachine);
            RequestBody idCard = RequestBody.create(MediaType.parse("text/plain"), strIdCard);
            RequestBody bodyTemperature = RequestBody.create(MediaType.parse("text/plain"), strTemprature);
            Call<DriverLoginModelClass> register = apiInterface.driverVerification(driver,shower, charger, powerbank, uniform, bagPack, swipingMachine, idCard, bodyTemperature,null );
            register.enqueue(new Callback<DriverLoginModelClass>() {
                @Override
                public void onResponse(Call<DriverLoginModelClass> call, Response<DriverLoginModelClass> response) {


                    try {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus() == 200 && response.body().getMessage().equalsIgnoreCase("Success!")) {
                                new SweetAlertDialog(VerificationActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Successfull")
                                        .setContentText(response.body().getMessage())
                                        .setConfirmText("Ok")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                SharePrefernce.setSharePrefernceBoolean(VerificationActivity.this, "login", true);
                                                adderssEditior.putString("DRIVER_LOGIN", "driverlogin");
                                                adderssEditior.remove("DRIVER_VERIFY");
                                                adderssEditior.commit();
                                                startActivity(new Intent(VerificationActivity.this, AdminLoginPage.class));
                                                finish();

                                                sDialog.dismissWithAnimation();
                                            }
                                        })

                                        .show();


                            } else {

                                new SweetAlertDialog(VerificationActivity.this, SweetAlertDialog.ERROR_TYPE)
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
                public void onFailure(Call<DriverLoginModelClass> call, Throwable t) {
                    // pDialog.dismiss();


                }
            });
        }
        else {
            RequestBody image = RequestBody.create(MediaType.parse("text/plain"), "nothing image");
            RequestBody driver = RequestBody.create(MediaType.parse("text/plain"), strDriverId);
            RequestBody shower = RequestBody.create(MediaType.parse("text/plain"), strShower);
            RequestBody charger = RequestBody.create(MediaType.parse("text/plain"), strCharger);
            RequestBody powerbank = RequestBody.create(MediaType.parse("text/plain"), strPowerBank);
            RequestBody uniform = RequestBody.create(MediaType.parse("text/plain"), strUniform);
            RequestBody bagPack = RequestBody.create(MediaType.parse("text/plain"), strBagPack);
            RequestBody swipingMachine = RequestBody.create(MediaType.parse("text/plain"), strSwippingMachine);
            RequestBody idCard = RequestBody.create(MediaType.parse("text/plain"), strIdCard);
            RequestBody bodyTemperature = RequestBody.create(MediaType.parse("text/plain"), strTemprature);
            Call<DriverLoginModelClass> register = apiInterface.driverVerificationWithoutImage(driver,shower, charger, powerbank, uniform, bagPack, swipingMachine, idCard, bodyTemperature,image );
            register.enqueue(new Callback<DriverLoginModelClass>() {
                @Override
                public void onResponse(Call<DriverLoginModelClass> call, Response<DriverLoginModelClass> response) {


                    try {

                        if (response.isSuccessful()) {
                            if (response.body().getStatus() == 200 && response.body().getMessage().equalsIgnoreCase("Success!")) {


                                new SweetAlertDialog(VerificationActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Successfull")
                                        .setContentText(response.body().getMessage())
                                        .setConfirmText("Ok")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                SharePrefernce.setSharePrefernceBoolean(VerificationActivity.this, "login", true);
                                                adderssEditior.putString("DRIVER_LOGIN", "driverlogin");
                                                adderssEditior.remove("DRIVER_VERIFY");
                                                adderssEditior.commit();
                                                startActivity(new Intent(VerificationActivity.this, AdminLoginPage.class));
                                                finish();

                                                sDialog.dismissWithAnimation();
                                            }
                                        })

                                        .show();
                            } else {

                                new SweetAlertDialog(VerificationActivity.this, SweetAlertDialog.ERROR_TYPE)
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
                public void onFailure(Call<DriverLoginModelClass> call, Throwable t) {
                    // pDialog.dismiss();


                }
            });
        }
    }
}