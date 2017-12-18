// By Fahd Humayun

package com.example.fahd.stegoshare;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class RecoverActivity extends AppCompatActivity {

    ImageView stepsImageView;
    ImageButton nextStepButton;
    ImageButton skipStepsButton;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover);

        initialization();

        stepsImageView = (ImageView) findViewById(R.id.id_recoveryStepsImageView);
        nextStepButton = (ImageButton) findViewById(R.id.id_nextStepButton);
        skipStepsButton = (ImageButton) findViewById(R.id.id_skipButton);

        counter = 1;

        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;

                if(counter > 5){
                    startSelectImagesActivity();
                    finish();
                }

                if(counter == 5){
                    nextStepButton.setImageResource(R.drawable.finish_button);
                    skipStepsButton.setBackground(null);
                }

                switch(counter){
                    case 1: stepsImageView.setImageResource(R.drawable.recovery_steps_1);
                        break;
                    case 2:
                        stepsImageView.setBackground(null);
                        stepsImageView.setImageResource(R.drawable.recovery_steps_2);
                        break;
                    case 3:
                        stepsImageView.setBackground(null);
                        stepsImageView.setImageResource(R.drawable.recovery_steps_3);
                        break;
                    case 4:
                        stepsImageView.setBackground(null);
                        stepsImageView.setImageResource(R.drawable.recovery_steps_4);
                        break;
                    case 5:
                        stepsImageView.setBackground(null);
                        stepsImageView.setImageResource(R.drawable.recovery_steps_5);
                        break;
                    default:
                        stepsImageView.setBackground(null);
                        stepsImageView.setImageResource(R.drawable.recovery_steps_1);
                }

            }
        });

        skipStepsButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startSelectImagesActivity();
            }
        });

    }

    private void initialization(){
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("  Stegoshare");
        getSupportActionBar().setSubtitle("    Recover Seed List");
    }

    private void startSelectImagesActivity(){
        Intent i = new Intent(this, SelectImagesActivity.class);
        i.putExtra("callingActivity", "RecoverActivity");
        startActivity(i);
        finish();
    }
}
