package vn.edu.usth.usthweather;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

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

//        // Create a new Fragment to be placed in the activity layout
//        FragmentManager managerFragment = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = managerFragment.beginTransaction();
//
//        WeatherAndForecastFragment bundleFragment = new WeatherAndForecastFragment();
//
//        // Add the fragment to the 'container' FrameLayout
//        fragmentTransaction.add(R.id.bundle,bundleFragment);
//        fragmentTransaction.commit();
//
//
//        PagerAdapter adapter = new HomeFragmentPagerAdapter(
//                getSupportFragmentManager(),
//                this
//        );
//        ViewPager pager = (ViewPager) findViewById(R.id.pager);
//        pager.setOffscreenPageLimit(3);
//        pager.setAdapter(adapter);
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
//        tabLayout.setupWithViewPager(pager);
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