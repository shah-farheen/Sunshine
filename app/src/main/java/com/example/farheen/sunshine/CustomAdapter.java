package com.example.farheen.sunshine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by farheen on 13/5/16
 */
public class CustomAdapter extends ArrayAdapter<WeatherListModel>{

    private LayoutInflater inflater;
    List<WeatherListModel> data;

    public CustomAdapter(Context context, int resource, List<WeatherListModel> data) {
        super(context, resource, data);
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    static class MyHolder{
        ImageView weatherImage;
        TextView weatherTitle;
        TextView description;
        public MyHolder(View view){
            weatherImage = (ImageView) view.findViewById(R.id.weather_image);
            weatherTitle = (TextView) view.findViewById(R.id.weather_title);
            description = (TextView) view.findViewById(R.id.description);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyHolder holder;
        if(convertView==null){
            View view = inflater.inflate(R.layout.list_items_3,parent,false);
            convertView = view;
            holder = new MyHolder(view);
            view.setTag(holder);
        }
        else {
            holder = (MyHolder) convertView.getTag();
        }

        WeatherListModel current = data.get(position);
        holder.weatherImage.setImageResource(current.weatherImage);
        holder.weatherTitle.setText(current.weatherTitle);
        holder.description.setText(current.description);

        return convertView;
    }

}
