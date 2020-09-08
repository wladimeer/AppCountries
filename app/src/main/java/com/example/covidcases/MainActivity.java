package com.example.covidcases;

import com.loopj.android.http.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private RecyclerView vr_principal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        httpCountries();

        vr_principal = findViewById(R.id.vr_principal);
        vr_principal.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false
        ));
    }

    private void httpCountries() {
        AsyncHttpClient client = new AsyncHttpClient();
        String URL = "https://api.covid19api.com/countries";

        client.get(URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                processCountries(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("error", error.toString());
            }
        });
    }

    private void processCountries(String responseBody) {
        try {
            JSONArray countries = new JSONArray(responseBody);
            List<Country> countryList = new ArrayList<>();

            for(int i = 0; i < 10; i++) {
                JSONObject country = countries.getJSONObject(i);

                countryList.add(new Country(
                        country.getString("Country"),
                        country.getString("Slug"),
                        country.getString("ISO2")
                ));
            }

            Adapter adapter = new Adapter(countryList);
            vr_principal.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}