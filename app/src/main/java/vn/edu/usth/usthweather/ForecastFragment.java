package vn.edu.usth.usthweather;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ScrollView;

public class ForecastFragment extends Fragment {
    public String city;

    public ForecastFragment() {
        // Required empty public constructor
    }
    public static ForecastFragment newInstance(String city) {
        ForecastFragment fragment = new ForecastFragment();
        Bundle args = new Bundle();
        args.putString("city", city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int dp = getResources().getDimensionPixelSize(R.dimen.dp_20);
        // Create a ScrollView
//       ScrollView scrollView = new ScrollView(getActivity());
//
//        // Create a vertical LinearLayout
//        LinearLayout linearLayout = new LinearLayout(getActivity());
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//        linearLayout.setBackgroundColor(Color.parseColor("#A7B5B5FF"));
//
//        // Create a FrameLayout.LayoutParams object with margins
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
//        params.setMargins(dp, dp, dp, dp);
//
//        // Set the layout parameters for the LinearLayout
//        linearLayout.setLayoutParams(params);
//        linearLayout.setPadding(15,5,15,5);
//
//        // Create an array of weather data for 7 days
//        String[] days = getResources().getStringArray(R.array.days_of_the_week);
//        String[] conditions = getResources().getStringArray(R.array.condition);
//        int[] degrees = getResources().getIntArray(R.array.degrees);
//        int[] degrees2 = getResources().getIntArray(R.array.degrees2);
//        int[] icons = {R.drawable.cloudy_1, R.drawable.sunny, R.drawable.rainy_1, R.drawable.cloudy_2, R.drawable.rainy_2, R.drawable.snowy, R.drawable.stormy};
//

//        int dp = getResources().getDimensionPixelSize(R.dimen.dp_20);

//        RelativeLayout relativeLayout = new RelativeLayout(getActivity());
//        relativeLayout.setBackgroundColor(Color.parseColor("#2000FF00"));
//
//        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(dp, dp, dp, dp);
//
//
//        TextView text = new TextView(getActivity());
//        text.setText("BALLS");
//
//        ImageView icon = new ImageView(getActivity());
//        icon.setId(R.id.weather_icon);
//        icon.setImageResource(R.drawable.rainy_1);
//        RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(300,300);
//        iconParams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        icon.setLayoutParams(iconParams);
//
//        relativeLayout.addView(text);
//        relativeLayout.addView(icon);
//        relativeLayout.setLayoutParams(params);

    // Create new frameLayout
    FrameLayout frameLayout =  new FrameLayout(requireContext());

    //Create new ScrollView
    ScrollView scrollView = new ScrollView(requireContext());

    //New vertical LinearLayout that contains the whole forecast fragment
    LinearLayout linearLayout = new LinearLayout(requireContext());
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    linearLayout.setBackgroundColor(Color.parseColor("#2000FF00"));

    // Create an array of weather data for 7 days
    String[] days = getResources().getStringArray(R.array.days_of_the_week);
    String[] conditions = getResources().getStringArray(R.array.condition);
    int[] degrees = getResources().getIntArray(R.array.degrees);
    int[] degrees2 = getResources().getIntArray(R.array.degrees2);
    int[] icons = {R.drawable.cloudy_1, R.drawable.sunny, R.drawable.rainy_1, R.drawable.cloudy_2, R.drawable.rainy_2, R.drawable.snowy, R.drawable.stormy};

    for (int i = 0; i < 7; i++) {
        //new LinearLayout that acts as a row per day
        LinearLayout horizontalLayout = new LinearLayout(requireContext());
        horizontalLayout.setGravity(Gravity.CENTER);
        horizontalLayout.setPadding(0,0,0,30);

        //Add the weekday "text"
        TextView textView = new TextView(requireContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1));
        textView.setLayoutParams(new LinearLayout.LayoutParams(220, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setText(days[i]);
        textView.setTextSize(20);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(70, 10, 10, 10);
//        textView.setPadding(0, 0, 80, 0);
//        textView.setPadding(30, 10, 10, 10);
//        textView.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1));

        //Add the ImageView
        ImageView imageView = new ImageView(requireContext());
        imageView.setLayoutParams(new LinearLayout.LayoutParams(110, 110));
        imageView.setBackgroundResource(icons[i]);
        imageView.setPadding(10, 10, 10, 10);
//        imageView.setLayoutParams(new LinearLayout.LayoutParams(110, 110));
//        imageView.setPadding(0, 0, 80, 0);

        //Add LinearLayout for condition / temp
        LinearLayout verticalLayout = new LinearLayout(requireContext());
        verticalLayout.setOrientation(LinearLayout.VERTICAL);

        verticalLayout.setPadding(100, 0, 0, 0);
        TextView textView1 = new TextView(requireContext());
        textView1.setText(conditions[i]);
        textView1.setTextSize(16);

        //Add TextView of temperature values in arrays.xml
        TextView textView2 = new TextView(requireContext());
        int tempAfter = degrees[i] + degrees2[i];
        textView2.setText(degrees[i] +"°C - " + tempAfter + "°C");
        textView2.setTextSize(16);
        textView2.setTextColor(Color.BLACK);

        //Add textView (weekdays) and imageView(weather icon) to horizontal layout
        horizontalLayout.addView(textView);
        horizontalLayout.addView(imageView);
        //Add TextView in vertical linear layout
        verticalLayout.addView(textView1);
        verticalLayout.addView(textView2);

        //Add horizontal vertical LinearLayout into LinearLayout
        horizontalLayout.addView(verticalLayout);

        //Add row - horizontal linearlayout in to forecast vertical layout
        linearLayout.addView(horizontalLayout);
    }

    LinearLayout.LayoutParams verticalLayoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
    );
    verticalLayoutParams.setMargins(dp,dp,dp,dp);
    linearLayout.setLayoutParams(verticalLayoutParams);


    scrollView.addView(linearLayout);

    frameLayout.addView((scrollView));
    return frameLayout;

    }
}