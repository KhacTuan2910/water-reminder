package com.example.waterreminder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.waterreminder.fragment.HomeFragment;
import com.example.waterreminder.fragment.MyProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MyProfileFragment.IsenData {

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_HISTORY = 1;
    private static final int FRAGMENT_PROFILE = 2;


    private int mCurrentFragment = FRAGMENT_HOME;

    private DrawerLayout mDrawerLayout;
    private BottomNavigationView mBottomNavigationView;
    private ViewPager2 mViewPager2;
    private MyViewPager2Adapter myViewPager2Adapter;

    private TextView tv_name_nav;

    private String weight = "";
    private String name = "";
    private String heigh = "";
    private String age = "";

    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setupViewPager2();

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar
                ,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        setupBottomNav();
        reveived_data();
        tv_name_nav.setText(name);

        // Notification
        createNotificationChannel();

        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE,48);
        calendar.set(Calendar.SECOND,40);
        calendar.set(Calendar.MILLISECOND,0);
        setAlarm();
        // End notification

    }

    // Methods Notification
    private void createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "nhom1beminderChannel";
            String description = "Channel For ... Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("nhom1bid",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }


    }

    private void setAlarm() {

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this,AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,pendingIntent);

        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT).show();



    }

    // End Methods Notification

    @Override
    public void senDataf(String fweight, String fname, String fage, String fheigh) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        intent.putExtra("fname", fname);
        intent.putExtra("fage", fage);
        intent.putExtra("fheigh", fheigh);
        intent.putExtra("fweight", fweight);
        startActivity(intent);
    }


    private void reveived_data() {
        Intent intent = getIntent();
        String mWeight = intent.getStringExtra("weight_data");
        String mName = intent.getStringExtra("name_data");
        String mAge = intent.getStringExtra("age_data");
        String mHeigh = intent.getStringExtra("heigh_data");

        weight = mWeight;
        name =mName;
        age = mAge;
        heigh = mHeigh;


    }
    public String getWeight() {
        return weight;
    }

    public String getHeigh() {
        return heigh;
    }

    public String getName() {
        return name;
    }
    public String getAge() {
        return age;
    }

    public void setupBottomNav() {
        mBottomNavigationView = findViewById(R.id.bottom_nav);
        mBottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
        mBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.bottom_home:
                        openHomeFragment();
//                        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        break;

                    case R.id.bottom_history:
                        openHistoryFraghment();
//                        navigationView.getMenu().findItem(R.id.nav_reminder).setChecked(true);

                        break;

                    case R.id.bottom_profile:
                        openProfileFragment();
//                        navigationView.getMenu().findItem(R.id.nav_history).setChecked(true);

                        break;
                }
                return true;
            }
        });

    }

    public void setupViewPager2() {
        mViewPager2 = findViewById(R.id.view_pager2);
        myViewPager2Adapter = new MyViewPager2Adapter(this);
        mViewPager2.setAdapter(myViewPager2Adapter);
        mViewPager2.setPageTransformer(new ZoomOutPageTransformer());

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header= navigationView.getHeaderView(0);
        tv_name_nav= header.findViewById(R.id.tv_name_nav);

//        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        mCurrentFragment = FRAGMENT_HOME;
                        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1:
                        mCurrentFragment = FRAGMENT_HISTORY;
                        navigationView.getMenu().findItem(R.id.nav_history).setChecked(true);
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_history).setChecked(true);
                        break;
                    case 2:
                        mCurrentFragment = FRAGMENT_PROFILE;
                        navigationView.getMenu().findItem(R.id.nav_profile).setChecked(true);
                        mBottomNavigationView.getMenu().findItem(R.id.bottom_profile).setChecked(true);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            openHomeFragment();
//            mBottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);

        }else if ( id ==R.id.nav_history) {
            openHistoryFraghment();
//            mBottomNavigationView.getMenu().findItem(R.id.bottom_Reminder).setChecked(true);


        }else if ( id ==R.id.nav_profile) {
            openProfileFragment();
//            mBottomNavigationView.getMenu().findItem(R.id.bottom_history).setChecked(true);

        } else if (id == R.id.nav_contact){

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

//    private void replaceFragment (Fragment fragment) {
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
//    }

    private void openHomeFragment() {
        if(mCurrentFragment != FRAGMENT_HOME) {
//            replaceFragment(new HomeFragment());
            mViewPager2.setCurrentItem(0);
            mCurrentFragment = FRAGMENT_HOME;
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }
    private void openHistoryFraghment() {
        if (mCurrentFragment != FRAGMENT_HISTORY) {
//            replaceFragment(new HistoryFragment());
            mViewPager2.setCurrentItem(1);
            mCurrentFragment = FRAGMENT_HISTORY;
            getSupportActionBar().setTitle("History");
        }
    }

    private void openProfileFragment() {
        if(mCurrentFragment != FRAGMENT_PROFILE) {
//            replaceFragment(new ReminderFragment());
            mViewPager2.setCurrentItem(2);
            mCurrentFragment = FRAGMENT_PROFILE;
            getSupportActionBar().setTitle("My Profile");
        }
    }


}