package com.example.appkacovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textView,textViewGeneralDeath,textViewGeneralRecovery,textViewTodayInfections,textViewTodayDeath,todayDeathArray;
    private Spinner spinner;
    public String url = "https://koronawirus-api.herokuapp.com/api/covid19/daily";


    public String przedzial_0,przedzial_1,przedzial_2,przedzial_3,przedzial_4,przedzial_5,przedzial_6,przedzial_7,przedzial_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Animacje
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        todayDeathArray = findViewById(R.id.todayDeathArray);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        textViewGeneralDeath = findViewById(R.id.textViewGeneralDeath);
        textViewGeneralRecovery = findViewById(R.id.textViewGeneralRecovery);
        textViewTodayDeath = findViewById(R.id.textViewTodayDeath);
        textViewTodayInfections = findViewById(R.id.textViewTodayInfections);
        spinner = findViewById(R.id.spinner);

        setSpinner();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int x = spinner.getSelectedItemPosition();
                switch (x){
                    case 0:
                        todayDeathArray.setText("Zgony przedział: "+przedzial_0);
                        break;
                    case 1:
                        todayDeathArray.setText("Zgony przedział: "+przedzial_1);
                        break;
                    case 2:
                        todayDeathArray.setText("Zgony przedział: "+przedzial_2);
                        break;
                    case 3:
                        todayDeathArray.setText("Zgony przedział: "+przedzial_3);
                        break;
                    case 4:
                        todayDeathArray.setText("Zgony przedział: "+przedzial_4);
                        break;
                    case 5:
                        todayDeathArray.setText("Zgony przedział: "+przedzial_5);
                        break;
                    case 6:
                        todayDeathArray.setText("Zgony przedział: "+przedzial_6);
                        break;
                    case 7:
                        todayDeathArray.setText("Zgony przedział: "+przedzial_7);
                        break;
                    case 8:
                        todayDeathArray.setText("Zgony przedział: "+przedzial_8);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCovid(url);
            }
        });

    }

    public void getCovid(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                String output = "";
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    //Wpisywanie danych
                    String generalInfections = jsonResponse.getJSONObject("general").getString("infections");
                    String generalDeaths = jsonResponse.getJSONObject("general").getString("deaths");
                    String generalRecovered = jsonResponse.getJSONObject("general").getString("recovered");

                    String todayNewInfections = jsonResponse.getJSONObject("today").getString("newInfections");
                    String todayNewDeaths = jsonResponse.getJSONObject("today").getString("newDeaths");
                    przedzial_0 = jsonResponse.getJSONObject("today").getJSONArray("deathAgeRanges").getJSONObject(0).getString("deaths");
                    przedzial_1 = jsonResponse.getJSONObject("today").getJSONArray("deathAgeRanges").getJSONObject(1).getString("deaths");
                    przedzial_2 = jsonResponse.getJSONObject("today").getJSONArray("deathAgeRanges").getJSONObject(2).getString("deaths");
                    przedzial_3 = jsonResponse.getJSONObject("today").getJSONArray("deathAgeRanges").getJSONObject(3).getString("deaths");
                    przedzial_4 = jsonResponse.getJSONObject("today").getJSONArray("deathAgeRanges").getJSONObject(4).getString("deaths");
                    przedzial_5 = jsonResponse.getJSONObject("today").getJSONArray("deathAgeRanges").getJSONObject(5).getString("deaths");
                    przedzial_6 = jsonResponse.getJSONObject("today").getJSONArray("deathAgeRanges").getJSONObject(6).getString("deaths");
                    przedzial_7 = jsonResponse.getJSONObject("today").getJSONArray("deathAgeRanges").getJSONObject(7).getString("deaths");
                    przedzial_8 = jsonResponse.getJSONObject("today").getJSONArray("deathAgeRanges").getJSONObject(8).getString("deaths");

                    String deathGenderMale = jsonResponse.getJSONObject("today").getJSONObject("deathGender").getString("male");
                    String deathGenderFemale = jsonResponse.getJSONObject("today").getJSONObject("deathGender").getString("female");

                    textView.setText("Infekcje: "+generalInfections);
                    textViewGeneralDeath.setText("Zgony: "+generalDeaths);
                    textViewGeneralRecovery.setText("Wyleczone osoby: " + generalRecovered);

                    textViewTodayInfections.setText("Infekcje dzisiaj: " + todayNewInfections);
                    textViewTodayDeath.setText("Zgony dzisiaj: " + todayNewDeaths);
                }catch (JSONException e){
                    System.out.println(e.getStackTrace());

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


    public void setSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.przedial, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}