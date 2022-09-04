package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainActivity2 extends AppCompatActivity {
    AutoCompleteTextView etsearch;
    ListView etlistview;

    public static List<CountryModel> countryModelList = new ArrayList<>();
    CountryModel countryModel;
    Myadaptor myadaptor;
    private Object JSONObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        etlistview=findViewById(R.id.etlistview);

        getSupportActionBar().setTitle("Countriwise Data");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        fetchCountryData();

        etlistview.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchCountryData() {
        String url = "https://disease.sh/v3/covid-19/countries";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String country= jsonObject.getString("country");
                        String cases= jsonObject.getString("cases");
                        String deaths= jsonObject.getString("deaths");
                        String recovered= jsonObject.getString("recovered");
                        String continent= jsonObject.getString("continent");
                        String todayCases= jsonObject.getString("todayCases");
                        String todayDeaths= jsonObject.getString("todayDeaths");
                        String activeCases= jsonObject.getString("active");

                        countryModel= new CountryModel(country,cases,todayCases,deaths,todayDeaths,recovered,activeCases);
                        countryModelList.add(countryModel);
                }
                    myadaptor = new Myadaptor(MainActivity2.this,countryModelList);
                    etlistview.setAdapter(myadaptor);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    };

