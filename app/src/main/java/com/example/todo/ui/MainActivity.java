package com.example.todo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.os.Bundle;

import com.example.todo.fragments.CompletedTaskFragment;
import com.example.todo.R;
import com.example.todo.fragments.HomeFragment;
import com.example.todo.fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import hotchemi.android.rate.AppRate;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNav = findViewById (R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //show rate app prompt
        AppRate.with (this)
                .setInstallDays (1)
                .setLaunchTimes (3)
                .setRemindInterval (2)
                .monitor ();

        AppRate.showRateDialogIfMeetsConditions (this);

        //this checks to see if there's any savedInstance,if null, then it replaces the fragment container
        // with home fragment.
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,
                    new HomeFragment ()).commit();
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener=
            menuItem -> {
                Fragment selectedFragment=null;
                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        selectedFragment=new HomeFragment();
                        break;

                    case R.id.completed:
                        selectedFragment=new CompletedTaskFragment ();
                        break;
                    case R.id.profile:
                        selectedFragment=new ProfileFragment ();
                        break;
                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer,
                            selectedFragment).commit();
                }

                return true;
            };
    @Override
    public void onBackPressed() {
        FragmentManager fm=getFragmentManager ();
        if (fm.getBackStackEntryCount ()>0) {
            fm.popBackStack ();
        }
           else {
               super.onBackPressed ();
           }
        }
        // handles on hard ware backpressed..

}


