package com.amigos.amigos.Views;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import com.amigos.amigos.ModelClass.DriverLoginModelClass;
import com.amigos.amigos.ModelClass.LoginModalClass;
import com.amigos.amigos.ModelClass.RegistrationModalClass;
import com.amigos.amigos.R;
import com.amigos.amigos.Retrofit.ApiClient;
import com.amigos.amigos.Retrofit.ApiInterface;
import com.amigos.amigos.Storage.SharePrefernce;
import com.amigos.amigos.Utils.GpsTracker;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPageActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout liRegistration, liForgot;
    AppCompatButton btnLogin, btnGoogle, btnFacebook;
    EditText etEmail, etPassword;
    GoogleSignInClient mGoogleSignInClient;
    String strNumber = "9667336909";
    CallbackManager callbackManager;
    String android_id, strIp_Address, strMobileIpAddresss, ipAddress, strcurrentlocation,currentDate,strLoginType;
    private GpsTracker gpsTracker;
    String strlatitude, strlongitude;
    String strPass = "123456";
    private static final int RC_SIGN_IN = 1;
    String strFcmtoken, strversion;
    private FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    public static final String MyPREFERENCES = "MyAmigoPrefs";
    SharedPreferences.Editor adderssEditior;
    SharedPreferences sharedpreferences;
    LoginButton loginButton;
    PackageInfo info = null;
    FusedLocationProviderClient fusedLocationProviderClient;
    String valid_email = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ApiInterface apiInterface;
    SweetAlertDialog pDialog;
    int consumerId,driverId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);

        PackageInfo infos;
        try {
            infos = getPackageManager().getPackageInfo("com.amigos.amigos", PackageManager.GET_SIGNATURES);
            for (Signature signature : infos.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA1");
                md.update(signature.toByteArray());
                String hash= new String(Base64.encode(md.digest(), 0));
                Log.e("hash", hash);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                        } else if (!report.areAllPermissionsGranted()) {
                            // showSettingsDialog();
                        }
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).withErrorListener(new PermissionRequestErrorListener() {
            @Override
            public void onError(DexterError error) {
            }
        })
                .onSameThread()
                .check();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        adderssEditior = sharedpreferences.edit();
        apiInterface = ApiClient.getPostService().create(ApiInterface.class);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(LoginPageActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getAddress();
        } else {
            ActivityCompat.requestPermissions(LoginPageActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        getIPAddress();
        getCurrentIP();
        getLocation();

        PackageManager manager = this.getPackageManager();
        try {
            info = manager.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        strversion = info.versionName;


        liRegistration = findViewById(R.id.liRegistration);
        btnLogin = findViewById(R.id.btnLogin);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnFacebook = findViewById(R.id.btnFacebook);
        loginButton = findViewById(R.id.login_button);
        liForgot = findViewById(R.id.liForgot);

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
                isValidPhoneNumbers(etEmail); // pass your EditText Obj here.
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


        mAuth = FirebaseAuth.getInstance();

        liRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPageActivity.this, SignUpPageActivity.class));

            }
        });

        liForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginPageActivity.this, ForgotPasswordActivity.class));

            }
        });
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
        currentDate = sdf.format(new Date());



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etEmail.getText().toString().length() != 0 &&  etPassword.getText().toString().length() != 0) {
                    if (etEmail.getText().toString().length() == 10) {
                        pDialog = new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                        pDialog.setCancelable(false);
                        pDialog.show();

                        driverLogin();

                       consumerLogin();
                    }
                    else {
                        Toast.makeText(LoginPageActivity.this, "Please enter valid mobile number", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(LoginPageActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
                }


            }
        });


        // for google login 148 to 166
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        callbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions("email", "public_profile");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                facebookHandlerCode(loginResult.getAccessToken());
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(intent, RC_SIGN_IN);

            }
        });

        if (!TextUtils.isEmpty(strIp_Address)) {
            ipAddress = strIp_Address;
        } else {
            ipAddress = "";
        }
        if (!TextUtils.isEmpty(strMobileIpAddresss)) {
            ipAddress = strMobileIpAddresss;
        } else {
            ipAddress = "";
        }
        android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);


        FirebaseMessaging.getInstance().isAutoInitEnabled();
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isComplete()) {
                    return;
                } else {
                    FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            if (!task.isSuccessful()) {
                                strFcmtoken = task.getException().getMessage();
                            } else {
                                strFcmtoken = task.getResult().getToken();


                                adderssEditior.putString("FCM_TOKEN", strFcmtoken);
                                adderssEditior.commit();
                                Log.d("fcmt+5saasaoken", strFcmtoken);
                            }
                        }
                    });
                }
            }
        });


        adderssEditior.putString("IP_Address", ipAddress);
        adderssEditior.putString("Device_ID", android_id);
        adderssEditior.putString("FCM_TOKEN", strFcmtoken);
        adderssEditior.putString("APP_VERSION", strversion);
        adderssEditior.commit();

        Log.v("allgittokenandipaddress", "all fields" + ipAddress + "" + android_id + "" + strFcmtoken);

    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginPageActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                handleSignInResult(task);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {

            }
        }

    }

    // for google login 188 to 228
    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //  loginButton.setVisibility(View.INVISIBLE);
                            firebaseUser = mAuth.getCurrentUser();
                            if (firebaseUser != null) {

                                String strName = firebaseUser.getDisplayName();
                                String stremail = firebaseUser.getEmail();
                                Uri strImage = firebaseUser.getPhotoUrl();
                                String strMobile ="1234567890";
                                String strPassword ="google";
                                String strRegisterType ="Google";
                                String strLatitudes = strlatitude;
                                String strlongitudes = strlongitude;
                                String strIpadres = ipAddress;
                                String stdevice = android_id;
                                String strfcm = strFcmtoken;

                                SocialLogn(strName,stremail,strMobile,strPassword,strRegisterType,strLatitudes,strlongitudes,strIpadres,stdevice,strfcm);
                                SharePrefernce.setSharePrefernceBoolean(LoginPageActivity.this, "login", true);
                                startActivity(new Intent(LoginPageActivity.this, DashBoardPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                finish();
                            }


                        }
                        else {

                        }
                    }
                });
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            String strName = account.getDisplayName();
            String stremail = account.getEmail();
            Uri strImage = account.getPhotoUrl();
            String strMobile ="1234567890";
            String strPassword ="google";
            String strRegisterType ="Google";
            String strLatitudes = strlatitude;
            String strlongitudes = strlongitude;
            String strIpadres = ipAddress;
            String stdevice = android_id;
            String strfcm = strFcmtoken;

            SocialLogn(strName,stremail,strMobile,strPassword,strRegisterType,strLatitudes,strlongitudes,strIpadres,stdevice,strfcm);
            SharePrefernce.setSharePrefernceBoolean(LoginPageActivity.this, "login", true);
            startActivity(new Intent(LoginPageActivity.this, DashBoardPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();


        } catch (ApiException e) {
            Log.e("sam", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = mAuth.getCurrentUser();

    }

    private void facebookHandlerCode(AccessToken token) {

        AuthCredential authCredential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    firebaseUser = mAuth.getCurrentUser();
                    if (firebaseUser != null) {
                        String strName = firebaseUser.getDisplayName();
                        String stremail = firebaseUser.getEmail();
                        Uri strImage = firebaseUser.getPhotoUrl();
                        String strMobile = "1234567890";
                        String strPassword = "facebook";
                        String strRegisterType = "facebook";
                        String strLatitudes = strlatitude;
                        String strlongitudes = strlongitude;
                        String strIpadres = ipAddress;
                        String stdevice = android_id;
                        String strfcm = strFcmtoken;

                        SocialLogn(strName,stremail,strMobile,strPassword,strRegisterType,strLatitudes,strlongitudes,strIpadres,stdevice,strfcm);
                        SharePrefernce.setSharePrefernceBoolean(LoginPageActivity.this, "login", true);
                        startActivity(new Intent(LoginPageActivity.this, DashBoardPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == btnFacebook) {
            loginButton.performClick();
        }
    }

    public void getCurrentIP() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        strIp_Address = ipAddress;
    }

    public String getIPAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
                        strMobileIpAddresss = ip;
                        return ip;
                    }
                }
            }
        } catch (SocketException ex) {

        }
        return null;
    }

    public void getLocation() {
        gpsTracker = new GpsTracker(LoginPageActivity.this);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();

            strlatitude = String.valueOf(latitude);
            strlongitude = String.valueOf(longitude);

            adderssEditior.putString("lat_tude", String.valueOf(latitude));
            adderssEditior.putString("longi_tude", String.valueOf(longitude));
            adderssEditior.commit();


        } else {

            gpsTracker.showSettingsAlert();
        }
    }

    private void getAddress() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(LoginPageActivity.this, Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                        strcurrentlocation = addresses.get(0).getAddressLine(0);
                        adderssEditior.putString("MYCURRENT_LOCATION", strcurrentlocation);
                        adderssEditior.commit();

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void consumerLogin() {

        RequestBody consumerMobile = RequestBody.create(MediaType.parse("text/plain"), etEmail.getText().toString());
        RequestBody consumerPassword = RequestBody.create(MediaType.parse("text/plain"), etPassword.getText().toString());
        RequestBody consumerLoginType = RequestBody.create(MediaType.parse("text/plain"), "Credential");

        final Call<LoginModalClass> register = apiInterface.consumerLogin(consumerMobile, consumerPassword, consumerLoginType);

        register.enqueue(new Callback<LoginModalClass>() {
            @Override
            public void onResponse(Call<LoginModalClass> call, final Response<LoginModalClass> response) {
                pDialog.dismiss();

                try {

                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200 && response.body().getMessage().equalsIgnoreCase("Success!")) {

                            for (int i= 0; i<response.body().getResult().getUser().size();i++){
                                 consumerId= response.body().getResult().getUser().get(i).getId();
                                 strLoginType = response.body().getResult().getUser().get(i).getLoginType();
                            }

                            new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("Login Successfull")
                                    .setContentText("Tap on ok to go Home Page")
                                    .setConfirmText("Ok")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            SharePrefernce.setSharePrefernceBoolean(LoginPageActivity.this, "login", true);
                                            adderssEditior.putString("DRIVER_LOGIN_DATE", "2");
                                            adderssEditior.putString("CONSUMER_ID", String.valueOf(consumerId));
                                            adderssEditior.commit();
                                            startActivity(new Intent(LoginPageActivity.this, DashBoardPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                            finish();
                                            sDialog.dismissWithAnimation();
                                        }
                                    })

                                    .show();

                        }
                        else {
                            new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Please try again!")
                                    .setContentText(response.body().getMessage())
                                    .show();
                        }
                    }


                    if (response.code() == 400) {
                        new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Please try again!")
                                .setContentText(response.body().getMessage())
                                .show();
                        Toast.makeText(LoginPageActivity.this, "Please 1 your Internet Connection", Toast.LENGTH_LONG).show();
                    } else {
                        //  Toast.makeText(LoginPageActivity.this, "Please 2 your Internet Connection", Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<LoginModalClass> call, Throwable t) {
                pDialog.dismiss();
                new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Please try again!")
                        .setContentText(t.getMessage())
                        .show();

            }
        });
    }

    public void driverLogin(){
        RequestBody driverMobile = RequestBody.create(MediaType.parse("text/plain"), etEmail.getText().toString());
        RequestBody driverPassword = RequestBody.create(MediaType.parse("text/plain"), etPassword.getText().toString());
        RequestBody driverLoginType = RequestBody.create(MediaType.parse("text/plain"), "driver");

        final Call<DriverLoginModelClass> register = apiInterface.driverLogin(driverMobile, driverPassword, driverLoginType);

        register.enqueue(new Callback<DriverLoginModelClass>() {
            @Override
            public void onResponse(Call<DriverLoginModelClass> call, Response<DriverLoginModelClass> response) {
                pDialog.dismiss();

                try {

                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200 && response.body().getMessage().equalsIgnoreCase("Success!")) {

                            for (int i= 0; i<response.body().getResult().getUser().size();i++){
                                driverId= response.body().getResult().getUser().get(i).getId();
                                strLoginType = response.body().getResult().getUser().get(i).getLoginType();
                            }

                                new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Login Successfull")
                                        .setContentText("Tap on ok to go Home Page")
                                        .setConfirmText("Ok")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                adderssEditior.putString("DRIVER_VERIFY", "verify");
                                                adderssEditior.putString("LOGIN_DATE", currentDate);
                                                adderssEditior.putString("DRIVER_LOGIN_DATE", "1");
                                                adderssEditior.putString("DRIVER_ID", String.valueOf(driverId));
                                                startActivity(new Intent(LoginPageActivity.this, VerificationActivity.class));
                                                finish();
                                                adderssEditior.commit();
                                            }
                                        })

                                        .show();



                        }
                        else if (response.body().getMessage() != null) {
                            new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Please try again!")
                                    .setContentText(response.body().getMessage())
                                    .show();
                        } else {
                            new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Please try again!")
                                    .setContentText(response.body().getMessage())
                                    .show();
                        }
                    }


                    if (response.code() == 400) {
                        new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Please try again!")
                                .setContentText(response.body().getMessage())
                                .show();
                        Toast.makeText(LoginPageActivity.this, "Please 1 your Internet Connection", Toast.LENGTH_LONG).show();
                    } else {
                        //  Toast.makeText(LoginPageActivity.this, "Please 2 your Internet Connection", Toast.LENGTH_LONG).show();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DriverLoginModelClass> call, Throwable t) {
                pDialog.dismiss();
                new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Please try again!")
                        .setContentText(t.getMessage())
                        .show();

            }
        });
    }


    public void SocialLogn( String sname, String esmail, String smobile, String spassword, String sregistertype, String slatitiude, String slongitude, String sipaddress, String sandroidid, String sfcmtoken) {
       // filePart = MultipartBody.Part.createFormData("social_image", strimage.toString());

        RequestBody consumerEmail = RequestBody.create(MediaType.parse("text/plain"), esmail);
        RequestBody consumerMobile = RequestBody.create(MediaType.parse("text/plain"), smobile);
        RequestBody consumerName = RequestBody.create(MediaType.parse("text/plain"), sname);
        RequestBody consumerIpAddress = RequestBody.create(MediaType.parse("text/plain"), sipaddress);
        RequestBody consumerPassword = RequestBody.create(MediaType.parse("text/plain"), spassword);
        RequestBody consumerFCMtoken = RequestBody.create(MediaType.parse("text/plain"), sfcmtoken);
        RequestBody consumerAddress = RequestBody.create(MediaType.parse("text/plain"), strcurrentlocation);
        RequestBody userType = RequestBody.create(MediaType.parse("text/plain"), sregistertype);
        RequestBody longitude = RequestBody.create(MediaType.parse("text/plain"), slongitude);
        RequestBody latitude = RequestBody.create(MediaType.parse("text/plain"), slatitiude);
        RequestBody deviceId = RequestBody.create(MediaType.parse("text/plain"), sandroidid);
        RequestBody appVersion = RequestBody.create(MediaType.parse("text/plain"), strversion);
        Call<RegistrationModalClass> register = apiInterface.consumerRegistration(consumerEmail, consumerMobile, consumerName, consumerPassword,userType, consumerIpAddress, consumerFCMtoken, longitude,latitude,deviceId,appVersion,consumerAddress);
        register.enqueue(new Callback<RegistrationModalClass>() {
            @Override
            public void onResponse(Call<RegistrationModalClass> call, Response<RegistrationModalClass> response) {


                try {

                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200) {

                            SharePrefernce.setSharePrefernceBoolean(LoginPageActivity.this, "login", true);
                            Toast.makeText(LoginPageActivity.this,"check data",Toast.LENGTH_LONG).show();
                            adderssEditior.putString("DRIVER_LOGIN_DATE", "2");
                            adderssEditior.commit();
                         //   SharedPreferences.Editor editor = sharedpreferences.edit();
                           // editor.putString("user_id", String.valueOf(response.body().getData().getId()));
                          //  editor.apply();

                        }

                        else {

                            new SweetAlertDialog(LoginPageActivity.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText(response.body().getMessage())
                                    .show();
                        }
                    }
                    else {
                        pDialog.dismiss();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RegistrationModalClass> call, Throwable t) {
                // pDialog.dismiss();


            }
        });


    }

}