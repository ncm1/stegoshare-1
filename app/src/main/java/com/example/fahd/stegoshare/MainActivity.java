// By Fahd Humayun

package com.example.fahd.stegoshare;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.R.attr.animation;

/*
MainActivity:
It is the main screen of the app when loaded.

Functionality:
1. Display app logo and brief description/introduction of the app.
2. Display two options:
    i. Hide: Beginning of the process for hiding the seed list.
    ii. Recover: Beginning of the process for recovering the seed list.

Variables:
1. Private:
    i. tapAnyTextView: TextView
    ii. hideImageButton: ImageButton
    iii. recoverImageButton: ImageButton

Methods:
1. Private:
    i. initialization(): void
    ii. showToast(): void
    iii. startHideActivity(): void
    iv. startRecoverActivity(): void

*/

public class MainActivity extends AppCompatActivity {

    // Variables
    private ImageView appIconImageView;
    private TextView tapAnyTextView;
    private ImageButton hideImageButton;
    private ImageButton recoverImageButton;

    /*
    onCreate() method
    called with the creation of the activity
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        hideImageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startHideActivity();
            }
        });

        recoverImageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startRecoverActivity();
            }
        });
    }

    /*
    initialization() method
    initialize GUI widgets or variables.

    @params
    none

    @return
    void
     */

    private void initialization(){
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("  Stegoshare");
        getSupportActionBar().setSubtitle("    Home");

        appIconImageView = (ImageView) findViewById(R.id.id_app_icon_ImageView);

        ObjectAnimator objectAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(this, R.animator.flipping);
        objectAnimator.setTarget(appIconImageView);
        objectAnimator.setDuration(20000);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.start();

        tapAnyTextView = (TextView) findViewById(R.id.id_tapAny_textView);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/CenturyGothic.ttf");
        tapAnyTextView.setTypeface(typeface);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(1000);
        animation.setStartOffset(20);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(Animation.INFINITE);
        tapAnyTextView.startAnimation(animation);

        hideImageButton = (ImageButton) findViewById(R.id.id_hide_imageButton);
        recoverImageButton = (ImageButton) findViewById(R.id.id_recover_imageButton);
    }

    /*
    showToast() method
    display message using Toast.
    called from onCreate().

    @params
    message: String
        Stores the string from the calling method.

    @return
    void
     */

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /*
    startHideActivity() method
    start the HideActivity using Intent to begin the process of hiding the seed list.
    called from the hideImageButton.onClickListener().

    @params
    none

    @return
    void
     */

    private void startHideActivity(){
        //Intent i = new Intent(this, HideActivity.class);
        Intent i = new Intent(this, SeedActivity.class);
        //Intent i = new Intent(this, SelectImagesActivity.class);
        startActivity(i);
    }

    /*
    startRecoverActivity() method
    start the RecoverActivity using Intent to begin the process of recovering the seed list.
    called from the recoverImageButton.onClickListener().

    @params
    none

    @return
    void
     */

    private void startRecoverActivity(){
        Intent i = new Intent(this, RecoverActivity.class);
        startActivity(i);
    }

}