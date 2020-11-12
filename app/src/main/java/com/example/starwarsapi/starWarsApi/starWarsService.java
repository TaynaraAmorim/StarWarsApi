package com.example.starwarsapi.starWarsApi;

import com.example.starwarsapi.models.starWarsResposta;

import retrofit2.Call;
import retrofit2.http.GET;

public interface starWarsService {

    @GET("people")
    Call<starWarsResposta> obterListaStarWars();
}
