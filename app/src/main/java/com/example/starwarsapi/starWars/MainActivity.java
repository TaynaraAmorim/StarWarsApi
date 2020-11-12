package com.example.starwarsapi.starWars;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.starwarsapi.R;
import com.example.starwarsapi.models.starWars;
import com.example.starwarsapi.models.starWarsResposta;
import com.example.starwarsapi.starWarsApi.starWarsService;
import com.example.starwarsapi.adapter.ListaStarWarsAdapter;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "STARDEX";
    Retrofit retrofit;
    ListView listViewPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //obterDados();
    }

    private void obterDados(View view){

        starWarsService service = retrofit.create(starWarsService.class);
        Call<starWarsResposta> starWarsRespostaCall = service.obterListaStarWars();
        starWarsRespostaCall.enqueue(new Callback<starWarsResposta>() {
            @Override
            public void onResponse(Call<starWarsResposta> call, Response<starWarsResposta> response) {
                if (response.isSuccessful()){
                    starWarsResposta starWarsResposta = response.body();
                    List<starWars> listaStarWars = starWarsResposta.getResults();

                    ListaStarWarsAdapter p = new ListaStarWarsAdapter(getApplicationContext(), listaStarWars);
                    listViewPeople = findViewById(R.id.peopleList);
                    listViewPeople.setAdapter(p);

                    Button b = findViewById(R.id.obterDados);

                    for(int i = 0; i < listaStarWars.size(); i++){
                        starWars people = listaStarWars.get(i);
                        Log.i(TAG, "Name: " + people.getName());
                        Log.i(TAG, "birth_year: " + people.getBirth_year());
                        Log.i(TAG, "eye_color: " + people.getEye_color());
                        Log.i(TAG, "height: " + people.getHeight());
                        Log.i(TAG, "homeworld: " + people.getHomeworld());
                    }

                } else {
                    Log.e(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<starWarsResposta> call, Throwable t) {
                Log.e(TAG,"onFailure: " + t.getMessage());

            }
        });
    }
}