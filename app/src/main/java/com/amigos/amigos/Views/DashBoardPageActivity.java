package com.amigos.amigos.Views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.amigos.amigos.AdapterClass.CustomAdapter;
import com.amigos.amigos.AdapterClass.DashAdapter;
import com.amigos.amigos.AddServiceActivity;
import com.amigos.amigos.ModelClass.DashClass;
import com.amigos.amigos.R;
import com.amigos.amigos.Storage.SharePrefernce;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.amigos.amigos.Views.SplashActivity.MyPREFERENCES;


public class DashBoardPageActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, PopupMenu.OnMenuItemClickListener {
    ViewPager viewPagers;
    Integer[] imageId = {R.drawable.www, R.drawable.wwww, R.drawable.www, R.drawable.wwww, R.drawable.testdrive};
    String[] strTitle = {"Pick &amp; drop \n ( Service of Vehicle )", "Inventory Movement", "Digital Road Side Assistance", "New vehicle home delivery", "Test drive module"};
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 3000; // time in milliseconds between successive task executions.
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    DashAdapter dashAdapter;
    ImageView imgaddCar, carimage, imgallcar, imgPickandDrop;
    RecyclerView dashboardRecycle;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    List<DashClass> dashClasses;
    LinearLayout firstid, liInsurance, liOurServices, liRepairing;
    TextView tvbrandname;
    Bundle extras;
    FirebaseUser firebaseUser;
    int ass;
    LinearLayout linearLayout;
    ImageView drawerimage;
    DrawerLayout drawer;
    LinearLayout liPick, liDrop;
    CheckBox cbLIcence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board_page);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

        liPick = findViewById(R.id.liPick);
        liDrop = findViewById(R.id.liDrop);
        imgPickandDrop = findViewById(R.id.imgPickandDrop);
        liDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardPageActivity.this, CustomerDropActivity.class));
            }
        });

        liPick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(DashBoardPageActivity.this);
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
                                                         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                             if (cbLIcence.isChecked()) {
                                                                 startActivity(new Intent(DashBoardPageActivity.this, OurServices.class));
                                                                 dialog.dismiss();
                                                             }

                                                         }
                                                     }
                );

                dialog.show();
                dialog.setCancelable(true);
            }
        });
        imgPickandDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(DashBoardPageActivity.this);
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
                                                         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                             if (cbLIcence.isChecked()) {
                                                                 startActivity(new Intent(DashBoardPageActivity.this, OurServices.class));
                                                                 dialog.dismiss();
                                                             }

                                                         }
                                                     }
                );

                dialog.show();
                dialog.setCancelable(true);
            }
        });


        drawerimage = findViewById(R.id.hjgh);
        drawer = findViewById(R.id.drawer_layout);
        drawerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DrawerLayout drawer = findViewById(R.id.drawer_layout);
                if (!drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.openDrawer(GravityCompat.START);
                } else {
                    drawer.closeDrawer(GravityCompat.END);
                }
            }
        });
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        linearLayout = drawer.findViewById(R.id.navdrawer);
        drawer.setStatusBarBackgroundColor(getResources().getColor(R.color.black));
        navigationView.getHeaderView(0).findViewById(R.id.navdrawer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        navigationView.setNavigationItemSelectedListener(DashBoardPageActivity.this);
        Menu menu = navigationView.getMenu();

        dashboardRecycle = findViewById(R.id.dashRecycler);
        sliderDotspanel = findViewById(R.id.SliderDots);
        viewPagers = findViewById(R.id.viewpagers);
        tvbrandname = findViewById(R.id.tvbrandname);
        carimage = findViewById(R.id.carimage);
        imgallcar = findViewById(R.id.imgallcar);
        String str = getIntent().getStringExtra("brandname");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ass = bundle.getInt("picture");
            carimage.setImageResource(ass);
            tvbrandname.setText(str);
        } else {
            carimage.setImageResource(R.drawable.pickanddrop);
            tvbrandname.setText("Car");
        }


        dashClasses = new ArrayList<>();

        imgaddCar = findViewById(R.id.imgaddCar);

        imgaddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DashBoardPageActivity.this, AddServiceActivity.class));
            }
        });
        carimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(DashBoardPageActivity.this, view);
                popup.setOnMenuItemClickListener(DashBoardPageActivity.this);
                popup.inflate(R.menu.popup_menu);
                popup.show();
            }
        });

        imgallcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(DashBoardPageActivity.this, OurServicesDeatailActivity.class));

               /* final Dialog dialog = new Dialog(DashBoardPageActivity.this);
                dialog.setContentView(R.layout.allcardetails);

                firstid = dialog.findViewById(R.id.firstid);
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(dialog.getWindow().getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                dialog.getWindow().setAttributes(layoutParams);
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                firstid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.setCancelable(true);*/
            }
        });


        PagerAdapter adapter = new CustomAdapter(DashBoardPageActivity.this, imageId, strTitle);

        viewPagers.setAdapter(adapter);

        dotscount = adapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));



        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == 4) {
                    currentPage = 0;
                }
                viewPagers.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // as soon as the application opens the first
        // fragment should be shown to the user
        // in this case it is algorithm fragment
        /* getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();*/

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            // By using switch we can easily get
            // the selected fragment
            // by using there id.
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.Home:

                    break;
                case R.id.abc:
                    startActivity(new Intent(DashBoardPageActivity.this, StoreActivity.class));

                    break;
                case R.id.xyz:

                    break;
                case R.id.profile:
                    startActivity(new Intent(DashBoardPageActivity.this, ProfileapageActivity.class));
                    break;
            }
            // It will help to replace the
            // one fragment to other.

            return true;
        }
    };

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
        }
        if (id == R.id.nav_vehicle) {
            startActivity(new Intent(DashBoardPageActivity.this, OurServicesDeatailActivity.class));
        }
        if (id == R.id.nav_changePassword) {
            startActivity(new Intent(DashBoardPageActivity.this, ChangePasswordActivity.class));
        }

        if (id == R.id.uLogout) {
            mAuth.signOut();
            LoginManager.getInstance().logOut();
            SharePrefernce.setSharePrefernceBoolean(getApplicationContext(), "login", false);
            SharedPreferences preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            startActivity(new Intent(DashBoardPageActivity.this, LoginPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));


            mGoogleSignInClient.revokeAccess().addOnCompleteListener(DashBoardPageActivity.this,
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            SharePrefernce.setSharePrefernceBoolean(getApplicationContext(), "login", false);
                            //SharePrefernce.setSharePrefernceBoolean(getApplicationContext(), "subscription", false);

                            SharedPreferences preferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.clear();
                            editor.apply();
                            startActivity(new Intent(DashBoardPageActivity.this, LoginPageActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));


                        }
                    });
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item 1 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item 2 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Item 3 clicked", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                Toast.makeText(this, "Item 4 clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }

    }
}


