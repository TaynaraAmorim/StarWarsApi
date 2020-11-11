package com.example.starwarsapi.models;

import java.util.ArrayList;

public class starWarsResposta {

    private ArrayList<starWars> results;

    public ArrayList<starWars> getResults(){
        return results;
    }
    public void setResults(ArrayList<starWars> results){
        this.results = results;
    }
}
