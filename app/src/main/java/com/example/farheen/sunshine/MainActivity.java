package com.example.farheen.sunshine;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAdapter.Communicator{

    String days[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    String weatherDescription[];
    int images[] = {R.drawable.art_clear, R.drawable.art_clouds, R.drawable.art_fog, R.drawable.art_rain, R.drawable.art_snow, R.drawable.art_storm, R.drawable.art_light_rain, R.drawable.art_clear, R.drawable.art_clouds, R.drawable.art_fog, R.drawable.art_rain, R.drawable.art_snow, R.drawable.art_storm, R.drawable.art_light_rain};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WeatherToday frag = new WeatherToday();
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frag_layout, frag, "TodayWeather");
        transaction.commit();

        Resources res = getResources();
        weatherDescription = res.getStringArray(R.array.description);

        List<WeatherListModel> data = setData(images,days,weatherDescription);
        CustomAdapter adapter = new CustomAdapter(this,R.layout.list_items_3,data,this);

        ListView list = (ListView) findViewById(R.id.mylist);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView day = (TextView) view.findViewById(R.id.weather_title);
                TextView description = (TextView) view.findViewById(R.id.description);
                Toast.makeText(getApplicationContext(),day.getText().toString()+" is "+description.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public List<WeatherListModel> setData(int[] images, String[] days, String[] weatherDescription){
        List<WeatherListModel> data = new ArrayList<>();
        for(int i=0;i<days.length;i++){
            WeatherListModel current = new WeatherListModel();
            current.weatherImage = images[i];
            current.weatherTitle = days[i];
            current.description = weatherDescription[i];
            data.add(current);
        }
        return data;
    }

    @Override
    public void sendData(String data) {
        Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
    }
}
