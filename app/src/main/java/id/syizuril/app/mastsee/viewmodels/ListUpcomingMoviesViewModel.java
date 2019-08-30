package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.BuildConfig;
import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.repositories.ListMoviesRepositories;

public class ListUpcomingMoviesViewModel extends ViewModel {
    private MutableLiveData<MovieResponse> mutableLiveData;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        ListMoviesRepositories listUpcomingMoviesRepositories = ListMoviesRepositories.getInstance();
        mutableLiveData = listUpcomingMoviesRepositories.getMovieResult("movie","upcoming", BuildConfig.TMDB_API_KEY,"en_US",1);
    }
    public LiveData<MovieResponse> getMovieResultList(){
        return mutableLiveData;
    }
}
