package id.syizuril.app.mastsee.repositories;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import id.syizuril.app.mastsee.models.TvShowResponse;
import id.syizuril.app.mastsee.requests.MovieApi;
import id.syizuril.app.mastsee.requests.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTvShowsRepositories {
    private static ListTvShowsRepositories instance;
    public static ListTvShowsRepositories getInstance(){
        if(instance == null){
            instance = new ListTvShowsRepositories();
        }
        return instance;
    }

    private MovieApi movieApi;

    public ListTvShowsRepositories(){
        movieApi = RetrofitService.createService(MovieApi.class);
    }

    public MutableLiveData<TvShowResponse> getTvShowsResult(String type, String category, String apiKey, String language, int page){
        final MutableLiveData<TvShowResponse> tvShowData = new MutableLiveData<>();
        movieApi.getTvShows(type, category, apiKey, language, page).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if(response.isSuccessful()){
                    tvShowData.setValue(response.body());
                    Log.d("Success", response.message());
                }else{
                    System.out.println("GAGAL");
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.d("ERROR ", t.getMessage());
            }
        });
        return tvShowData;
    }
}
