package com.ucsdextandroid1.musicsearch.data;

import android.util.Log;
import androidx.annotation.NonNull;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rjaylward on 2019-05-10
 */
public class DataSources {

    private static final String TAG = DataSources.class.getSimpleName();

    private static DataSources instance;

    private ItunesApi dataApi;

    private DataSources() {

        //TODO create a custom gson
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ItunesSearchResults.class, new ItunesSearchResultsDeserialzer())
                .create();
        //TODO use retrofit to create an instance of the itunesApi
        this.dataApi = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create(gson)) //in class 5 we added our custom gson here
                .build()
                .create(ItunesApi.class);
    }

    public static DataSources getInstance() {
        if(instance == null)
            instance = new DataSources();

        return instance;
    }

    public void search(String searchTerm, Callback<List<? extends SongItem>> callback) {
        //TODO call the itunesApi and return an empty list on any failures
        dataApi.search(searchTerm).enqueue(new retrofit2.Callback<ItunesSearchResults>() {
            @Override
            public void onResponse(Call<ItunesSearchResults> call, Response<ItunesSearchResults> response) {
                if (response.isSuccessful()) {
                    callback.onDataFetched(response.body().getSongs());
                }
                else
                    callback.onDataFetched(Collections.emptyList());
            }

            @Override
            public void onFailure(Call<ItunesSearchResults> call, Throwable t) {
                callback.onDataFetched(Collections.emptyList());
            }
        });
    }

    public interface Callback<T> {
        void onDataFetched(T data);
    }

    public interface ItunesApi {
        //TODO add a method that corresponds to the search method on the iTunesApi
        @GET("search?media=music&limit=25")
        Call<ItunesSearchResults> search(@Query("term") String term);
    }
}
