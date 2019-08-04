package id.syizuril.app.mastsee.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.requests.MovieApi;
import id.syizuril.app.mastsee.requests.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListMoviesRepositories {
    private MutableLiveData<Boolean> isConnected = new MutableLiveData<>();
    public static String status ="success";
    private static ListMoviesRepositories instance;
    public static ListMoviesRepositories getInstance(){
        if(instance == null){
            instance = new ListMoviesRepositories();
        }
        return instance;
    }

    private MovieApi movieApi;

    public ListMoviesRepositories(){
        movieApi = RetrofitService.createService(MovieApi.class);
    }

    public MutableLiveData<MovieResponse> getMovieResult(String type, String category, String apiKey, String language, int page){
        final MutableLiveData<MovieResponse> movieData = new MutableLiveData<>();
        movieApi.getMovies(type, category, apiKey, language, page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    movieData.setValue(response.body());
                    Log.d("Success", response.message());
                    status = "success";
                    isConnected.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d("ERROR ", t.getMessage());
                status = "error";
                isConnected.setValue(false);
            }
        });
        return movieData;
    }

    public LiveData<Boolean> getIsConnected(){
        return isConnected;
    }
}
