// By Guy Rubinstein
// CustomPhotoGalleryActivity (Activity) - Used for importing the images from phone gallery to the application

package com.example.fahd.stegoshare;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

//https://stackoverflow.com/questions/23426113/how-to-select-multiple-images-from-gallery-in-android/23426985#23426985
public class CustomPhotoGalleryActivity extends AppCompatActivity {

    private GridView grdImages;
    private ImageButton btnSelect;

    private ImageAdapter imageAdapter;
    private String[] arrPath;
    private boolean[] thumbnailsselection;
    private int ids[];
    private int count;
    private int numberOfParts;

    private boolean recoverActivityFlag;

    /**
     * Overrides methods
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_gallery);
        grdImages= (GridView) findViewById(R.id.grdImages);
        btnSelect= (ImageButton) findViewById(R.id.btnSelect);

        recoverActivityFlag = getIntent().getBooleanExtra("recoverActivityFlag", false);

        if(recoverActivityFlag){
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setTitle("  Gallery");
            getSupportActionBar().setSubtitle("    Recover Seed List - Step 1: Select images");

            ((TextView) findViewById(R.id.selectText)).setText("Select the images");

        } else {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setTitle("  Gallery");
            getSupportActionBar().setSubtitle("    Hide Seed List - Step 2: Select images");

            numberOfParts = getIntent().getIntExtra("numberOfParts",1);
            ((TextView) findViewById(R.id.selectText)).setText("Select " + numberOfParts + " images");
        }



        final String[] columns = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };
        final String orderBy = MediaStore.Images.Media._ID;
        Cursor imagecursor = getContentResolver()
                .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);
        int image_column_index = imagecursor.getColumnIndex(MediaStore.Images.Media._ID);
        this.count = imagecursor.getCount();
        this.arrPath = new String[this.count];
        ids = new int[count];
        this.thumbnailsselection = new boolean[this.count];
        for (int i = 0; i < this.count; i++) {
            imagecursor.moveToPosition(i);
            ids[i] = imagecursor.getInt(image_column_index);
            int dataColumnIndex = imagecursor.getColumnIndex(MediaStore.Images.Media.DATA);
            arrPath[i] = imagecursor.getString(dataColumnIndex);
        }

        imageAdapter = new ImageAdapter(this, thumbnailsselection, ids, count);
        grdImages.setAdapter(imageAdapter);
        imagecursor.close();


        btnSelect.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                final int len = thumbnailsselection.length;
                int cnt = 0;
                String selectImages = "";
                for (int i = 0; i < len; i++) {
                    if (thumbnailsselection[i]) {
                        cnt++;
                        selectImages = selectImages + arrPath[i] + "|";
                    }
                }
                if (cnt != numberOfParts && !recoverActivityFlag) {
                    Toast.makeText(getApplicationContext(), "Select " + numberOfParts + " images.", Toast.LENGTH_LONG).show();
                } else {
                    Intent i = new Intent();
                    i.putExtra("data", selectImages);
                    setResult(Activity.RESULT_OK, i);
                    finish();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        super.onBackPressed();

    }

}
