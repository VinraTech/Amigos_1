package com.amigos.amigos.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager.widget.ViewPager;

import com.amigos.amigos.AdapterClass.IntroViewPagerAdapter;
import com.amigos.amigos.ModelClass.ScreenItem;
import com.amigos.amigos.R;
import com.amigos.amigos.Storage.SharePrefernce;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;

public class IntroPageActivity extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    AppCompatButton btnNext;
    int position = 0;
    Button btnGetStarted;
    Animation btnAnim;
    TextView tvSkip;
    List<ScreenItem> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_intro_page);
        screenPager = findViewById(R.id.screen_viewpager);
        btnNext = findViewById(R.id.btn_next);
        btnGetStarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_animation);
        tvSkip = findViewById(R.id.tv_skip);

        mList = new ArrayList<>();


        // hide the action bar

        mList.add(new ScreenItem("Service Center", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s", R.drawable.slidersec));
        mList.add(new ScreenItem("Get Ready", "when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries", R.drawable.sliderthird));
        mList.add(new ScreenItem("Repairing Service Center", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", R.drawable.sliderfour));

        introViewPagerAdapter = new IntroViewPagerAdapter(IntroPageActivity.this, mList);
        screenPager.setAdapter(introViewPagerAdapter);
        tabIndicator.setupWithViewPager(screenPager);


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if (position < mList.size()) {
                    position++;
                    screenPager.setCurrentItem(position);
                }

                if (position == mList.size() - 1) { // when we rech to the last screen

                    // TODO : show the GETSTARTED Button and hide the indicator and the next button

                    loaddLastScreen();


                }


            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1 || tab.getPosition() == 0) {
                    btnNext.setVisibility(View.VISIBLE);
                    tvSkip.setVisibility(View.VISIBLE);
                    tabIndicator.setVisibility(View.VISIBLE);
                    btnGetStarted.setVisibility(View.GONE);
                } else {
                    btnNext.setVisibility(View.INVISIBLE);
                    btnGetStarted.setVisibility(View.VISIBLE);
                    tvSkip.setVisibility(View.INVISIBLE);
                    tabIndicator.setVisibility(View.INVISIBLE);
                    btnGetStarted.setAnimation(btnAnim);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePrefernce.setSharePrefernceBoolean(IntroPageActivity.this, "first", true);
                Intent mainActivity = new Intent(getApplicationContext(), LoginPageActivity.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
            }
        });

        // skip button click listener

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharePrefernce.setSharePrefernceBoolean(IntroPageActivity.this, "first", true);

                Intent mainActivity = new Intent(getApplicationContext(), LoginPageActivity.class);
                startActivity(mainActivity);
                savePrefsData();
                finish();
//                screenPager.setCurrentItem(mList.size());
            }
        });


    }

    private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpnendBefore = pref.getBoolean("isIntroOpnend", false);
        return isIntroActivityOpnendBefore;


    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpnend", true);
        editor.commit();


    }

    private void loaddLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetStarted.setVisibility(View.VISIBLE);
        tvSkip.setVisibility(View.INVISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetStarted.setAnimation(btnAnim);

    }


}
