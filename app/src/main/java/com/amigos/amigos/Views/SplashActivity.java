package com.amigos.amigos.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amigos.amigos.R;
import com.amigos.amigos.Storage.SharePrefernce;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SplashActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyAmigoPrefs";
    SharedPreferences sharedpreferences;
    String strVerify, strDriverLogin,strLoginDate,strcurrentDate,strDriverLoginDate;
    Handler handler;
    Button btnRetry;
    SharedPreferences.Editor adderssEditior;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        adderssEditior = sharedpreferences.edit();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d");
        strcurrentDate = sdf.format(new Date());
        strVerify = sharedpreferences.getString("DRIVER_VERIFY", "");
        strDriverLogin =sharedpreferences.getString("DRIVER_LOGIN", "");
        strLoginDate =sharedpreferences.getString("LOGIN_DATE", "");
        strDriverLoginDate =sharedpreferences.getString("DRIVER_LOGIN_DATE", "");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                moveAhead();
            }
        }, 1000);
    }

    private void moveAhead() {

        if (strDriverLoginDate.equalsIgnoreCase("1")) {

            if (!strLoginDate.equalsIgnoreCase(strcurrentDate) ) {
                Toast.makeText(SplashActivity.this, "wewerwe", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
         else if (!SharePrefernce.getSharePrefernceBoolean(this, "login")) {
                Toast.makeText(SplashActivity.this, "No", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }

            else if (strVerify.equalsIgnoreCase("verify")) {
                startActivity(new Intent(this, VerificationActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            } else if ((strDriverLogin.equalsIgnoreCase("driverlogin"))) {
                startActivity(new Intent(this, AdminLoginPage.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        }
            else if (strDriverLoginDate.equalsIgnoreCase("2")){

                if (!SharePrefernce.getSharePrefernceBoolean(this, "first")) {
                    startActivity(new Intent(this, IntroPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                } else if (!SharePrefernce.getSharePrefernceBoolean(this, "login")) {
                    Toast.makeText(SplashActivity.this, "sdfsdfsd", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, LoginPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                } else {
                    startActivity(new Intent(this, DashBoardPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

                }
        }
            else {
            startActivity(new Intent(this, IntroPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        }
    }
}