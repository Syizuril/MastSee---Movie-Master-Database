package id.syizuril.app.mastsee.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.models.TvShowResponse;
import id.syizuril.app.mastsee.requests.MovieApi;
import id.syizuril.app.mastsee.requests.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMoviesRepository {
    private MutableLiveData<Boolean> isConnected = new MutableLiveData<>();
    private static ListMoviesRepository instance;
    public static ListMoviesRepository getInstance(){
        if(instance == null){
            instance = new ListMoviesRepository();
        }
        return instance;
    }

    private MovieApi movieApi;

    private ListMoviesRepository(){
        movieApi = RetrofitService.createService(MovieApi.class);
    }

    public MutableLiveData<MovieResponse> getMovieResult(String type, String category, String apiKey, String language, int page){
        final MutableLiveData<MovieResponse> movieData = new MutableLiveData<>();
        movieApi.getMovies(type, category, apiKey, language, page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    movieData.setValue(response.body());
                    Log.d("Success", response.message());
                    isConnected.setValue(true);
                }else{
                    isConnected.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Log.d("ERROR ", t.getMessage());
                isConnected.setValue(false);
            }
        });
        return movieData;
    }

    public MutableLiveData<TvShowResponse> getTvShowsResult(String type, String category, String apiKey, String language, int page){
        final MutableLiveData<TvShowResponse> tvShowData = new MutableLiveData<>();
        movieApi.getTvShows(type, category, apiKey, language, page).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                if(response.isSuccessful()){
                    tvShowData.setValue(response.body());
                    Log.d("Success", response.message());
                    isConnected.setValue(true);
                }else{
                    isConnected.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                Log.d("ERROR ", t.getMessage());
                isConnected.setValue(false);
            }
        });
        return tvShowData;
    }

    public MutableLiveData<MovieResponse> getSearchMovieResult(String type, String category, String apiKey, String language, String query){
        final MutableLiveData<MovieResponse> movieData = new MutableLiveData<>();
        movieApi.getSearchMovies(type, category, apiKey, language, query).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getResults().isEmpty()){
                        isConnected.setValue(false);
                    }else{
                    movieData.setValue(response.body());
                    Log.d("Success", response.message());
                    isConnected.setValue(true);
                    }
                }else{
                    isConnected.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Log.d("ERROR ", t.getMessage());
                isConnected.setValue(false);
            }
        });
        return movieData;
    }

    public MutableLiveData<TvShowResponse> getSearchTvShowsResult(String type, String category, String apiKey, String language, String query){
        final MutableLiveData<TvShowResponse> tvData = new MutableLiveData<>();
        movieApi.getSearchTvShows(type, category, apiKey, language, query).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvShowResponse> call, @NonNull Response<TvShowResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getResults().isEmpty()){
                        isConnected.setValue(false);
                    }else{
                        tvData.setValue(response.body());
                        Log.d("Success", response.message());
                        isConnected.setValue(true);
                    }
                }else{
                    isConnected.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<TvShowResponse> call, @NonNull Throwable t) {
                Log.d("ERROR ", t.getMessage());
                isConnected.setValue(false);
            }
        });
        return tvData;
    }

    public LiveData<Boolean> getIsConnected(){
        return isConnected;
    }
}
