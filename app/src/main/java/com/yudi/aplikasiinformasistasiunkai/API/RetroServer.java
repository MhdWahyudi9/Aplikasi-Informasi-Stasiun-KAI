package com.yudi.aplikasiinformasistasiunkai.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String url = "https://aplikasiinformasistasiunkai.000webhostapp.com/";
    private static Retrofit retro;

    public static Retrofit connectRetrofit() {
        if (retro == null) {
            retro = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }
}
