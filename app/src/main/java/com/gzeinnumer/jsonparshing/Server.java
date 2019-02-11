package com.gzeinnumer.jsonparshing;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {
    private static String BASE_URL = "https://simplifiedcoding.net/demos/";
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
