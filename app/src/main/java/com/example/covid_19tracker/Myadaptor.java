package com.example.covid_19tracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Myadaptor extends ArrayAdapter {
    private Context context;
    private List<CountryModel> countrylist;
    private List<CountryModel> countrylistfiltered;
    public Myadaptor(@NonNull Context context, List<CountryModel> countrylist) {
        super(context, R.layout.listitem,countrylist);
        this.context=context;
        this.countrylist=countrylist;
        this.countrylistfiltered=countrylist;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,null,true);
        TextView tvcountryname = view.findViewById(R.id.countryname);
        tvcountryname.setText(countrylist.get(position).getCountry());

        return view;
    }

    @Override
    public int getCount() {
        return countrylistfiltered.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {

        return countrylistfiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
