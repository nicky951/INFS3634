package com.example.infs3634country;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;


public class CountryDetail extends AppCompatActivity {

    private TextView name;
    private TextView region;
    private TextView capital;
    private TextView currency;
    private TextView population;
    private ImageView flag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_detail);

        name = findViewById(R.id.countryName1);
        region = findViewById(R.id.region);
        capital = findViewById(R.id.capital);
        currency = findViewById(R.id.currencies);
        population = findViewById(R.id.population);
        flag = findViewById(R.id.countryImage);

        final Intent intent = getIntent();
        final Country country = (Country) intent.getSerializableExtra("countrySelected");

        name.setText(country.getName());
        region.setText(country.getRegion());
        capital.setText(country.getCapital());
        population.setText(country.getPopulation());

        String url = country.getFlag();

        GlideToVectorYou.justLoadImage(this, Uri.parse(url), flag);

        final String currencyRequestUrl = "https://restcountries.eu/rest/v2/name/" + country.getName();

        final RequestQueue requestQueue =  Volley.newRequestQueue(this);

        Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                Gson gson = new Gson();
                currencyResponse[] currencyResponse = gson.fromJson(response, currencyResponse[].class);
                List<currencies> currencies = currencyResponse[0].getCurrencies();

                currency.setText(currencies.get(0).getCode());
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"The request failed: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                requestQueue.stop();
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.GET, currencyRequestUrl, responseListener,
                errorListener);

        requestQueue.add(stringRequest);

    }
}
