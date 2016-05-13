package com.example.farheen.sunshine;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    String days[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String weatherDescription[];
    int images[] = {R.drawable.art_clear, R.drawable.art_clouds, R.drawable.art_fog, R.drawable.art_rain, R.drawable.art_snow, R.drawable.art_storm, R.drawable.art_light_rain, R.drawable.art_clear, R.drawable.art_clouds, R.drawable.art_fog, R.drawable.art_rain, R.drawable.art_snow, R.drawable.art_storm, R.drawable.art_light_rain};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WeatherToday frag = new WeatherToday();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frag_layout, frag, "TodayWeather");
        transaction.commit();

        Resources res = getResources();
        weatherDescription = res.getStringArray(R.array.description);

        ListView list = (ListView) findViewById(R.id.mylist);
        list.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RelativeLayout row = (RelativeLayout) view;
        TextView day = (TextView) row.getChildAt(1);
        TextView desc = (TextView) row.getChildAt(2);
        Toast.makeText(getApplicationContext(),day.getText().toString()+" is "+desc.getText().toString(),Toast.LENGTH_SHORT).show();
    }
}
