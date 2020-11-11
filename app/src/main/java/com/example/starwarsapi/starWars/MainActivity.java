package com.example.starwarsapi.starWars;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.starwarsapi.R;
import com.example.starwarsapi.models.starWars;
import com.example.starwarsapi.models.starWarsResposta;
import com.example.starwarsapi.starWarsService.starWarsService;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "STARDEX";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.dev/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obterDados();
    }

    private void obterDados(){
        starWarsService service = retrofit.create(starWarsService.class);
        Call<starWarsResposta> starWarsRespostaCall = service.obterListaStarWars();
        starWarsRespostaCall.enqueue(new Callback<starWarsResposta>() {
            @Override
            public void onResponse(Call<starWarsResposta> call, Response<starWarsResposta> response) {
                if (response.isSuccessful()){
                    starWarsResposta starWarsResposta = response.body();
                    List<starWars> listaStarWars = starWarsResposta.getResults();
                    for(int i = 0; i < listaStarWars.size(); i++){
                        starWars people = listaStarWars.get(i);
                        Log.i(TAG, "StarWars: " + people.getName());
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