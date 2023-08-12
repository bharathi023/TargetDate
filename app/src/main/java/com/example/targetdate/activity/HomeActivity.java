package com.example.targetdate.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.targetdate.R;
import com.example.targetdate.constants.GlobalMethods;
import com.example.targetdate.custom.StaticData;
import com.example.targetdate.fragements.TabFragment;

public class HomeActivity extends AppCompatActivity {
    int backButtonClick;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        StaticData.saveAllCategories(getApplicationContext());
        initTabView();
        actionEvents();
    }



    private void initTabView() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        try {
            if (mFragmentTransaction != null) {
                TabFragment tab = new TabFragment();

                mFragmentTransaction.replace( R.id.containerViews, tab ).commit();
            }
        } catch (Exception e) {
            // Log.e("", "");
        }
    }

    private void actionEvents() {
        ((ImageView)findViewById(R.id.user)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ((ImageView)findViewById(R.id.logout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             showLogoutAlert();
            }
        });

    }

    private void showLogoutAlert() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Confirmation")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                        System.exit(0);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onBackPressed() {

        if (backButtonClick == 2) {
            finishAffinity();
            System.exit(0);
        } else {
            try {
                GlobalMethods.showNormalToast(HomeActivity.this, getString(R.string.please_click_back_againto_exist), 0);
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            synchronized (this) {
                                wait(1000 * 5);
                                backButtonClick = 1;
                            }
                        } catch (InterruptedException ex) {
                        }

                        // TODO
                    }
                };

                thread.start();

            } catch (Exception e) {
                // Log.e("", "");

            }
            backButtonClick++;
        }

    }
}