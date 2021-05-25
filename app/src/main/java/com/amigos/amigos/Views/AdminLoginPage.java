package com.amigos.amigos.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.amigos.amigos.AdapterClass.DashAdapter;
import com.amigos.amigos.ModelClass.DashClass;
import com.amigos.amigos.ModelClass.DriverDetailsMOdelClass;
import com.amigos.amigos.ModelClass.DriverLoginModelClass;
import com.amigos.amigos.ModelClass.DriverOnDutyDetails;
import com.amigos.amigos.ModelClass.DriverOnDutyModelClass;
import com.amigos.amigos.ModelClass.RegistrationModalClass;
import com.amigos.amigos.R;
import com.amigos.amigos.Retrofit.ApiClient;
import com.amigos.amigos.Retrofit.ApiInterface;
import com.amigos.amigos.Storage.SharePrefernce;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminLoginPage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    Integer[] imageId = {R.drawable.picdrop, R.drawable.inventry, R.drawable.roadside, R.drawable.delivery, R.drawable.testdrive};
    String[] strTitle = {"Pick &amp; drop \n ( Service of Vehicle )", "Inventory Movement", "Digital Road Side Assistance", "New vehicle home delivery", "Test drive module"};
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    DashAdapter dashAdapter;
    ImageView imgaddCar, imgEdit;
    RecyclerView dashboardRecycle;
    List<DashClass> dashClasses;
    LinearLayout subliParents, liParents;
    LinearLayout liOurTask, liOnTimePerformance, liCashInHand, liRemainingFile, liOnTimeTask, liCanceltask, liTodayEarning, liTodayMissedTask, liMonthEarning, liMonthMissedTask;
    ImageView drawerimage;
    Switch backgroundSwitch;

    String strDriverId, strDriverOf, strDriverName, strDriverOfTime, strDriverOnDetails,strAppVersin;
    SweetAlertDialog pDialog;
    TextView tvOff, tvDriverName, tvAppVersion, tvOffDuty;
    public static final String MyPREFERENCES = "MyAmigoPrefs";
    SharedPreferences sharedpreferences;
    ApiInterface apiInterface;
    SharedPreferences.Editor adderssEditior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        adderssEditior = sharedpreferences.edit();
        apiInterface = ApiClient.getPostService().create(ApiInterface.class);
        strDriverId = sharedpreferences.getString("DRIVER_ID", "");
        strAppVersin = sharedpreferences.getString("APP_VERSION", "");

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a");
        strDriverOfTime = sdf.format(new Date());


        getDriverData();
        getDriverDOnDutyDetails();


        tvOff = findViewById(R.id.tvOff);
        imgaddCar = findViewById(R.id.imgaddCar);
        liParents = findViewById(R.id.liParents);
        liOnTimePerformance = findViewById(R.id.liOnTimePerformance);
        liCashInHand = findViewById(R.id.liCashInHand);
        liRemainingFile = findViewById(R.id.liRemainingFile);
        liOnTimeTask = findViewById(R.id.liOnTimeTask);
        liCanceltask = findViewById(R.id.liCanceltask);
        liTodayEarning = findViewById(R.id.liTodayEarning);
        liTodayMissedTask = findViewById(R.id.liTodayMissedTask);
        liMonthEarning = findViewById(R.id.liMonthEarning);
        liMonthMissedTask = findViewById(R.id.liMonthMissedTask);
        liOurTask = findViewById(R.id.liOurTask);
        backgroundSwitch = findViewById(R.id.backgroundSwitch);


        subliParents = findViewById(R.id.subliParents);
        liParents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, GoalsActivity.class));
            }
        });
        liOurTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, ServicePickUpActivity.class));
            }
        });
        subliParents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, MonthEarningActivity.class));
            }
        });
        liOnTimePerformance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, PerformanceActivity.class));
            }
        });

        liCashInHand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, CustomerCashActivity.class));
            }
        });

        liRemainingFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, RemainingFileActivity.class));
            }
        });

        liOnTimeTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, TaskHistoryActivity.class));
            }
        });

        liCanceltask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, CancelTaskActivity.class));
            }
        });

        liTodayEarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, TodayTaskActivity.class));
            }
        });
        liTodayMissedTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, TodayMissedTaskActivity.class));
            }
        });
        liMonthEarning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, DriverMonthEarningActivity.class));
            }
        });
        liMonthMissedTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, MonthMissedTaskActivity.class));
            }
        });


        backgroundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    tvOff.setText("ON DUTY");
                    strDriverOf = tvOff.getText().toString().trim();
                    driverOf();

                } else
                    {
                    tvOff.setText("OFF DUTY");
                    strDriverOf = tvOff.getText().toString().trim();
                    driverOf();
                }
            }
        });

        drawerimage = findViewById(R.id.drawerimage);
      final   DrawerLayout  drawer = findViewById(R.id.drawer_layout);
        drawerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                } else {
                    drawer.closeDrawer(GravityCompat.END);
                }
            }
        });


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);

        drawer.setStatusBarBackgroundColor(getResources().getColor(R.color.black));
        navigationView.getHeaderView(0).findViewById(R.id.navdrawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        navigationView.setNavigationItemSelectedListener(AdminLoginPage.this);

        imgEdit = navigationView.getHeaderView(0).findViewById(R.id.imgProfileEdit);
        tvDriverName = navigationView.getHeaderView(0).findViewById(R.id.tvDriverName);
        tvAppVersion = navigationView.getHeaderView(0).findViewById(R.id.tvAppVersion);
        tvOffDuty = navigationView.getHeaderView(0).findViewById(R.id.tvOffDuty);

        tvAppVersion.setText(strAppVersin);
         //navigationView.getHeaderView(0).findViewById(R.id.imgProfileEdit);

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLoginPage.this, PofileDriverActivity.class));
            }
        });

        imgaddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, CustomerIntroActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            startActivity(new Intent(AdminLoginPage.this, PofileDriverActivity.class));
        }

        if (id == R.id.nav_goals) {
            startActivity(new Intent(AdminLoginPage.this, GoalsActivity.class));
        }
        if (id == R.id.nav_payout) {
            startActivity(new Intent(AdminLoginPage.this, MonthEarningActivity.class));
        }
        if (id == R.id.nav_performance) {
            startActivity(new Intent(AdminLoginPage.this, PerformanceActivity.class));
        }
        if (id == R.id.nav_inhandcash) {
            startActivity(new Intent(AdminLoginPage.this, CustomerCashActivity.class));
        }
        if (id == R.id.nav_taskHistory) {
            startActivity(new Intent(AdminLoginPage.this, TaskHistoryActivity.class));
        }
        if (id == R.id.nav_attendence) {
            startActivity(new Intent(AdminLoginPage.this, AttendanceActivity.class));
        }
        if (id == R.id.nav_acDeatails) {
            startActivity(new Intent(AdminLoginPage.this, BankDetailsActivity.class));
        }
        if (id == R.id.nav_verificationDeatails) {
            startActivity(new Intent(AdminLoginPage.this, VerificationActivity.class).putExtra("isback", "true"));
        }

        if (id == R.id.nav_logout) {
            SharePrefernce.setSharePrefernceBoolean(getApplicationContext(), "login", false);
            startActivity(new Intent(AdminLoginPage.this, LoginPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));


        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void getDriverData() {
        Map<String, String> prams = new HashMap<String, String>();
        prams.put("driver_id", String.valueOf(strDriverId));
        Call<DriverDetailsMOdelClass> profile;
        profile = apiInterface.getDriverDetails(prams);
        pDialog = new SweetAlertDialog(AdminLoginPage.this, SweetAlertDialog.PROGRESS_TYPE);
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

                            strDriverName = response.body().getResult().getDriverName();
                            tvDriverName.setText(response.body().getResult().getDriverName());


                        } else {
                            new SweetAlertDialog(AdminLoginPage.this, SweetAlertDialog.ERROR_TYPE)
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


    public void driverOf() {

        RequestBody driverId = RequestBody.create(MediaType.parse("text/plain"), strDriverId);
        RequestBody driverName = RequestBody.create(MediaType.parse("text/plain"), strDriverName);
        RequestBody driverOff = RequestBody.create(MediaType.parse("text/plain"), strDriverOf);
        RequestBody driverofTtime = RequestBody.create(MediaType.parse("text/plain"), strDriverOfTime);


        Call<DriverOnDutyModelClass> register = apiInterface.driverOf(driverId, driverName, driverOff, driverofTtime);

        register.enqueue(new Callback<DriverOnDutyModelClass>() {
            @Override
            public void onResponse(Call<DriverOnDutyModelClass> call, Response<DriverOnDutyModelClass> response) {
                pDialog.dismiss();

                try {

                    if (response.body().getStatus() == 200 && response.body().getMessage().equalsIgnoreCase("Success!")) {
                        Toast.makeText(AdminLoginPage.this, response.body().getMessage(), Toast.LENGTH_LONG).show();

                    } else {
                        new SweetAlertDialog(AdminLoginPage.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Please try again!")
                                .setContentText(response.body().getMessage())
                                .show();
                    }


                    if (response.code() == 400) {
                        new SweetAlertDialog(AdminLoginPage.this, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Please try again!")
                                .setContentText(response.body().getMessage())
                                .show();

                    } else {

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    pDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<DriverOnDutyModelClass> call, Throwable t) {
                pDialog.dismiss();
                new SweetAlertDialog(AdminLoginPage.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Please try again!")
                        .setContentText(t.getMessage())
                        .show();

            }
        });
    }

    public void getDriverDOnDutyDetails() {
        Map<String, String> prams = new HashMap<String, String>();
        prams.put("driver_id", String.valueOf(strDriverId));
        Call<DriverOnDutyDetails> profile;
        profile = apiInterface.driverOnDutyDetails(prams);

        profile.enqueue(new Callback<DriverOnDutyDetails>() {
            @Override
            public void onResponse(Call<DriverOnDutyDetails> call, Response<DriverOnDutyDetails> response) {

                try {
                    if (response.body() != null) {

                        if (response.body().getStatus() == 200) {

                            strDriverOnDetails = response.body().getResult().getDriverOff();
                            tvOffDuty.setText(response.body().getResult().getDriverOff());
                            Toast.makeText(AdminLoginPage.this, strDriverOnDetails, Toast.LENGTH_LONG).show();
                            if (strDriverOnDetails.equals("OFF DUTY")) {
                                backgroundSwitch.setChecked(false);
                            } else {
                                backgroundSwitch.setChecked(true);
                            }


                        } else {
                            new SweetAlertDialog(AdminLoginPage.this, SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText(response.body().getMessage())
                                    .show();
                        }
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<DriverOnDutyDetails> call, Throwable t) {


            }
        });
    }
}