package id.syizuril.app.mastsee.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import id.syizuril.app.mastsee.models.SearchResponse;
import id.syizuril.app.mastsee.requests.MovieApi;
import id.syizuril.app.mastsee.requests.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListSearchMoviesRepositories {
    private MutableLiveData<Boolean> isConnected = new MutableLiveData<>();
    public static String status ="success";
    private static ListSearchMoviesRepositories instance;
    public static ListSearchMoviesRepositories getInstance(){
        if(instance == null){
            instance = new ListSearchMoviesRepositories();
        }
        return instance;
    }

    private MovieApi movieApi;

    public ListSearchMoviesRepositories(){
        movieApi = RetrofitService.createService(MovieApi.class);
    }

    public MutableLiveData<SearchResponse> getSearchMovieResult(String type, String category, String apiKey, String language, String query, int page, boolean includeAdult){
        final MutableLiveData<SearchResponse> movieData = new MutableLiveData<>();
        movieApi.getSearchMovies(type, category, apiKey, language, query, page, includeAdult).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.isSuccessful()){
                    movieData.setValue(response.body());
                    Log.d("Success", response.message());
                    status = "success";
                    isConnected.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
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
