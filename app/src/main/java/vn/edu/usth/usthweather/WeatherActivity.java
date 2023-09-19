package vn.edu.usth.usthweather;

import static android.content.ContentValues.TAG;

import android.Manifest;
//import static androidx.core.content.ContextCompat.checkSelfPermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class HomeFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGE_COUNT = 3;
    private Context context;
    private String titles[];

    public HomeFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        titles = new String[]{
                context.getString(R.string.hanoi),
                context.getString(R.string.paris),
                context.getString(R.string.toulouse)
        };
    }

    @Override
    public int getCount() {
        return PAGE_COUNT; //number of pages for a ViewPager
    }

    @Override
    public Fragment getItem(int page) {
        //returns an instance of Fragment corresponding to the specified page and city name
        switch (page) {
            case 0:
                return WeatherAndForecastFragment.newInstance(context.getString(R.string.hanoi));
            case 1:
                return WeatherAndForecastFragment.newInstance(context.getString(R.string.paris));
            case 2:
                return WeatherAndForecastFragment.newInstance(context.getString(R.string.toulouse));
        }
        return new EmptyFragment(); //failsafe
    }
    @Override
    public CharSequence getPageTitle(int page) {
        //returns a tab title corresponding to the specified page and city name
        return titles[page];
    }
}

public class WeatherActivity extends AppCompatActivity {

//    private static final int REQUEST_CODE_MANAGE_EXTERNAL_STORAGE = 1;
//    private static final String PERMISSION_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private int STORAGE_PERMISSION_CODE = 1;
    private MediaPlayer mediaPlayer;
    private static final String MP3_FILE_PATH = Environment.getExternalStorageDirectory() + "/Music/hahaha.mp3";

    public void extractMP3File() {
        try {
            File directory = new File(Environment.getExternalStorageDirectory() + "/Music");
            if (!directory.exists()) {
                directory.mkdirs();
            } else {

            }

            InputStream inputStream = getAssets().open("hahaha.mp3");
            OutputStream outputStream = new FileOutputStream(MP3_FILE_PATH);

            byte[] buffer = new byte[8192];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

        inputStream.close();
        outputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void playMP3File() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(MP3_FILE_PATH);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stopMP3() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(MP3_FILE_PATH);
            mediaPlayer.pause();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        PagerAdapter adapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), this);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);

        if (ActivityCompat.checkSelfPermission(WeatherActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(WeatherActivity.this,R.string.granted_notice,Toast.LENGTH_SHORT).show();
        } else {
            requestRuntimePermission();
        }

        extractMP3File();
        playMP3File();

    }

    private void requestRuntimePermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(WeatherActivity.this, R.string.granted_notice, Toast.LENGTH_SHORT).show();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(WeatherActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            AlertDialog.Builder reqbuild = new AlertDialog.Builder(WeatherActivity.this);
            reqbuild.setMessage(R.string.ask_permission_text)
                    .setTitle(R.string.ask_permission_title)
                    .setCancelable(false)
                    .setPositiveButton(R.string.OK_text, (dialog, which) ->{
                        ActivityCompat.requestPermissions(WeatherActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
                    })
                    .setNegativeButton(R.string.cancel_text, ((dialog, which) -> dialog.dismiss()));
            reqbuild.show();
        } else {
            ActivityCompat.requestPermissions(WeatherActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"Start");
    }

    @Override
    protected  void onResume() {
        super.onResume();
        Log.i(TAG,"Resume");
    }
    @Override
    protected  void onPause() {
        super.onPause();
        Log.i(TAG,"Pause");


    }
    @Override
    protected  void onStop() {
        super.onStop();
        Log.i(TAG,"Stop");

    }
    @Override
    protected  void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"Destroy");

    }
}