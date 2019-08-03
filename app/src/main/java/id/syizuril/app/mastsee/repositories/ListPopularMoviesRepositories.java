package id.syizuril.app.mastsee.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.requests.MovieApi;
import id.syizuril.app.mastsee.requests.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPopularMoviesRepositories {
    private static ListPopularMoviesRepositories instance;
    public static ListPopularMoviesRepositories getInstance(){
        if(instance == null){
            instance = new ListPopularMoviesRepositories();
        }
        return instance;
    }

    private MovieApi movieApi;

    public ListPopularMoviesRepositories(){
        movieApi = RetrofitService.createService(MovieApi.class);
    }

    public MutableLiveData<MovieResponse> getMovieResult(String type, String category, String apiKey, String language, int page){
        final MutableLiveData<MovieResponse> popularMovieData = new MutableLiveData<>();
        movieApi.getMovies(type, category, apiKey, language, page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful()){
                    popularMovieData.setValue(response.body());
                    Log.d("Success", response.message());
                }else{
                    System.out.println("GAGAL");
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                popularMovieData.setValue(null);
                Log.d("ERROR ", t.getMessage());
            }
        });
        return popularMovieData;
    }
}
