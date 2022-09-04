package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    private int  positionCountry;
    TextView ctvtotalcases,ctvtotaldeaths,ctvrecovered,ctextview,ctvtodaycases,ctvactivecases;
    TextView crestotalcases,crestotaldeaths,cresrecovered,crestodaycases,crestodaydeath,cresactivecases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent=getIntent();
        positionCountry = intent.getIntExtra("position",0);

        ctvtotalcases=findViewById(R.id.ctvTotalCases);
        ctvtotaldeaths=findViewById(R.id.ctvDeaths);
        ctvrecovered=findViewById(R.id.ctvRecovered);
        ctextview=findViewById(R.id.ctextView);
        ctvtodaycases=findViewById(R.id.ctvTodayCases);
        ctvactivecases=findViewById(R.id.ctvActiveCases);
        cresactivecases=findViewById(R.id.cresActiveCases);
        cresrecovered=findViewById(R.id.cresRecovered);
        crestotalcases=findViewById(R.id.cresTotalCases);
        crestotaldeaths=findViewById(R.id.cresDeaths);
        crestodaycases=findViewById(R.id.cresTodayCases);
        crestodaydeath=findViewById(R.id.cresCritical);
        getSupportActionBar().setTitle("Country Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ctextview.setText(MainActivity2.countryModelList.get(positionCountry).getCountry());
        crestodaydeath.setText(MainActivity2.countryModelList.get(positionCountry).getTodayDeaths());
        crestotalcases.setText(MainActivity2.countryModelList.get(positionCountry).getCases());
        cresactivecases.setText(MainActivity2.countryModelList.get(positionCountry).getActiveCases());
        crestotaldeaths.setText(MainActivity2.countryModelList.get(positionCountry).getDeaths());
        cresrecovered.setText(MainActivity2.countryModelList.get(positionCountry).getRecovered());
        crestodaycases.setText(MainActivity2.countryModelList.get(positionCountry).getTodayCases());



    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}