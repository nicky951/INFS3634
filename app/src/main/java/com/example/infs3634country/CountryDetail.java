package com.example.infs3634country;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.pixplicity.sharp.Sharp;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CountryDetail extends AppCompatActivity {

    private TextView name;
    private TextView region;
    private TextView capital;
    private TextView timezone;
    private TextView currencies;
    private TextView population;
    private ImageView flag;
    private static OkHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_detail);

        name = findViewById(R.id.countryName1);
        region = findViewById(R.id.region);
        capital = findViewById(R.id.capital);
        timezone = findViewById(R.id.timezone);
        currencies = findViewById(R.id.currencies);
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
        System.out.println(url);

    }
}
