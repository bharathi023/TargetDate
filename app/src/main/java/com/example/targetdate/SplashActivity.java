package com.example.targetdate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       // animateAppName();
        callLoginScreen();
    }

    private void animateAppName() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 500f);
        //repeats the animation 2 times
        valueAnimator.setRepeatCount(1);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
        // animate over the course of 700 milliseconds
        valueAnimator.setDuration(300);
// define how to update the view at each "step" of the animation
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                ((AppCompatTextView)findViewById(R.id.app_name)).setRotationX(progress);

            }
        });
        valueAnimator.start();


    }


    private void callLoginScreen() {
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        }, 1900);
    }

}