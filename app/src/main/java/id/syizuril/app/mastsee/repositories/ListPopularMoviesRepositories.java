package id.syizuril.app.mastsee.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.models.MovieResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListPopularMoviesRepositories {
    private static ListPopularMoviesRepositories instance;
    private static String BASE_URL = "https://api.themoviedb.org";
    private static String API_KEY = "b2d6f482d73c8f231cd3af7c9085e7a5";

    private ArrayList<MovieResult> dataPopularMovies = new ArrayList<>();

    public static ListPopularMoviesRepositories getInstance(){
        if(instance == null){
            instance = new ListPopularMoviesRepositories();
        }
        return instance;
    }

    public MutableLiveData<List<MovieResult>> getMovieResult(){
        setMoviesTVShows();
        MutableLiveData<List<MovieResult>> data = new MutableLiveData<>();
        data.setValue(dataPopularMovies);
        return data;
    }

    private void setMoviesTVShows(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieDbService movieDbService = retrofit.create(MovieDbService.class);
        Call<MovieResponse> call = movieDbService.getMovies("movie","popular",API_KEY,"en_US",1);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<MovieResult> movieResults = response.body().getResults();
                dataPopularMovies.addAll(movieResults);
                Log.d("Berhasil", String.valueOf(dataPopularMovies));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }
}
