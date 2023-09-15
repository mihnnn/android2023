package vn.edu.usth.usthweather;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class WeatherAndForecastFragment extends Fragment {

//    public WeatherAndForecastFragment() {
//        // Required empty public constructor
//    }
    public static WeatherAndForecastFragment newInstance(String city) {
        WeatherAndForecastFragment fragment = new WeatherAndForecastFragment();
        Bundle args = new Bundle();
        args.putString("city",city);
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            String myString = getArguments().getString("myString");
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ScrollView scrollView = new ScrollView(getActivity());

        LinearLayout linearLayout = new LinearLayout(getActivity());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT ));

        linearLayout.setId(View.generateViewId());

        String city = getArguments().getString("city");

        WeatherFragment weatherFragment = WeatherFragment.newInstance(city);
        ForecastFragment forecastFragment = ForecastFragment.newInstance(city);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(linearLayout.getId(), weatherFragment);
        fragmentTransaction.add(linearLayout.getId(), forecastFragment);
        fragmentTransaction.commit();

        scrollView.addView(linearLayout);
        return scrollView;
    }

//        return inflater.inflate(R.layout.fragment_weather_and_forecast,container,false);
//    }
}