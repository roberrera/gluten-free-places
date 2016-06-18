package com.roberterrera.glutenfreeplaces.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.roberterrera.glutenfreeplaces.model.FoursquareResults;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rob on 6/17/16.
 */
public interface FoursquareAPIService {
    @GET("/v2/venues/search")
    Call<FoursquareResults> loadVenues(
            @Query("ll") String latlon,
            @Query("query") String glutenfree,
            @Query("client_id") String CLIENT_ID,
            @Query("client_secret") String CLIENT_SECRET,
            @Query("v") int versionParam,
            @Query("m") String foursquare
    );

    class Factory{
        // If the service hasn't started, create it. Otherwise, call on the existing service.
        private static FoursquareAPIService service;
        public static FoursquareAPIService getInstance(){
            if (service == null){

                Gson gson = new GsonBuilder()
                        .create();

                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.foursquare.com")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                service = retrofit.create(FoursquareAPIService.class);
                return service;
            } else {
                return service;
            }
        }
    }


}
