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
        CustomAdapter adapter = new CustomAdapter(this,days,images,weatherDescription);
        list.setAdapter(adapter);
        list.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        RelativeLayout row = (RelativeLayout) view;
        TextView day = (TextView) row.getChildAt(1);
        TextView desc = (TextView) row.getChildAt(2);
        Toast.makeText(getApplicationContext(),day.getText().toString()+" is "+desc.getText().toString(),Toast.LENGTH_SHORT).show();
    }
}

class MyViewHolder{
    ImageView myImage;
    TextView myDay;
    TextView myDescription;
    MyViewHolder(View row){
        myImage = (ImageView) row.findViewById(R.id.weather_image);
        myDay = (TextView) row.findViewById(R.id.weather_title);
        myDescription = (TextView) row.findViewById(R.id.description);
    }
}

class CustomAdapter extends ArrayAdapter<String>{

    Context context;
    int[] images;
    String[] dayArray;
    String[] descriptionArray;
    CustomAdapter(Context c, String[] day, int[] imgs, String[] desc){

        super(c, R.layout.list_items_3, R.id.weather_title, day);
        this.context = c;
        this.images = imgs;
        this.dayArray = day;
        this.descriptionArray = desc;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        MyViewHolder holder;
        if (row==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_items_3, parent, false);
            holder = new MyViewHolder(row);
            row.setTag(holder);
            Log.d("Tag", "Creating new row");
        }

       else {
            holder = (MyViewHolder) row.getTag();
            Log.d("Tag", "Recycling");
        }

        holder.myImage.setImageResource(images[position]);
        holder.myDay.setText(dayArray[position]);
        holder.myDescription.setText(descriptionArray[position]);

        return row;
    }
}
