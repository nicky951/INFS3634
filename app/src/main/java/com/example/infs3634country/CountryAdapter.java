package com.example.infs3634country;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Country> countryToAdapt;

    public void setData(List<Country> countryToAdapt) {
        this.countryToAdapt = countryToAdapt;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.country_summary, parent, false);

        CountryViewHolder countryViewHolder = new CountryViewHolder(view);
        return countryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        final Country countryAtPosition = countryToAdapt.get(position);

        holder.bind(countryAtPosition);

        String url = countryAtPosition.getFlag();

        Utils.fetchSvg(holder.itemView.getContext(), url, holder.flag);

    }

    @Override
    public int getItemCount() {
        return countryToAdapt.size();
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public TextView name;
        /*
        public TextView region;
        public TextView capital;
        public TextView timezone;
        public TextView currency;
        public TextView population;
        */

        public ImageView flag;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.countryname);
            /*
            region = view.findViewById(R.id.regionLabel);
            capital = view.findViewById(R.id.capital);
            timezone = view.findViewById(R.id.timezone);
            currency = view.findViewById(R.id.currencies);
            population = view.findViewById(R.id.population);
            */

            flag = view.findViewById(R.id.countryflag);
        }

        public void bind(final Country country) {
            name.setText(country.getName());
        }

    }
}
