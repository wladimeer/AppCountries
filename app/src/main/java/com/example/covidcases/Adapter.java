package com.example.covidcases;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<Country> countryList;

    public Adapter(List<Country> countryList) {
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.items_country, parent, false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.assignData(countryList.get(position));
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView country, slug, iso2;

        public ViewHolder(@NonNull View view) {
            super(view);
            country = view.findViewById(R.id.country);
            slug = view.findViewById(R.id.slug);
            iso2 = view.findViewById(R.id.iso2);
        }

        public void assignData(Country country) {
            this.country.setText(country.getName());
            this.slug.setText(country.getSlug());
            this.iso2.setText(country.getIso2());
        }
    }

}
