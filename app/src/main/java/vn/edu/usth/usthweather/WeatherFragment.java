package vn.edu.usth.usthweather;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class WeatherFragment extends Fragment {

    public String city;

    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance(String city) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString("city", city);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int margin = getResources().getDimensionPixelSize(R.dimen.dp_20);
        FrameLayout frameLayout = new FrameLayout(requireContext());
        frameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT));

        RelativeLayout relativeLayout = new RelativeLayout(requireContext());
        relativeLayout.setBackground(getResources().getDrawable(R.drawable.frame));
        relativeLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
//        relativeLayout.setBackgroundColor(getResources().getColor(R.color.teal_200));
        relativeLayout.setPadding(100, 100, 100, 100);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
        );


        layoutParams.setMargins(margin, margin, margin, margin);
        relativeLayout.setLayoutParams(layoutParams);


        ImageView imageView = new ImageView(requireContext());
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(300,300));
        imageView.setBackgroundResource(R.drawable.cloudy_1);
        RelativeLayout.LayoutParams imageParams = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
        imageParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        TextView textView1 = new TextView(requireContext());
        textView1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));
        textView1.setText("12°C\nCloudy");

        TextView textView2 = new TextView(requireContext());
        textView2.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT));

        textView2.setText(R.string.city);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );

        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        textView2.setLayoutParams(params);

        relativeLayout.addView(imageView);
        relativeLayout.addView(textView1);
        relativeLayout.addView(textView2);

        frameLayout.addView(relativeLayout);

        return frameLayout;
//        return inflater.inflate(R.layout.fragment_weather,container,false);
    }
}