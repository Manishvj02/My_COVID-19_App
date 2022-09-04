package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    CardView cardView;
    PieChart pie_chart;
    TextView resTotalCases,resActiveCases,resDeaths,resRecovered,resTodayCases,resCritical,resTodayRecovered,resTests;
    Button trackButton;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resTests=findViewById(R.id.resTests);
        resTodayRecovered=findViewById(R.id.resTodayRecovered);
        resCritical=findViewById(R.id.resCritical);
        resActiveCases=findViewById(R.id.resActiveCases);
        resTotalCases=findViewById(R.id.resTotalCases);
        resDeaths=findViewById(R.id.resDeaths);
        resRecovered=findViewById(R.id.resRecovered);
        resTodayCases=findViewById(R.id.resTodayCases);
        trackButton=findViewById(R.id.trackButton);
        trackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });

        fetchData();




    }

    public void fetchData() {
    String url = "https://disease.sh/v3/covid-19/all";
    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                JSONObject jsonObject=new JSONObject(response.toString());
                resActiveCases.setText(jsonObject.getString("active"));
                resRecovered.setText(jsonObject.getString("recovered"));
                resDeaths.setText(jsonObject.getString("deaths"));
                resTotalCases.setText(jsonObject.getString("cases"));
                resTodayCases.setText(jsonObject.getString("todayCases"));
                resCritical.setText(jsonObject.getString("critical"));
                resTests.setText(jsonObject.getString("tests"));
                resTodayRecovered.setText(jsonObject.getString("todayRecovered"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    RequestQueue requestQueue = Volley.newRequestQueue(this);
    requestQueue.add(request);
    }
}
