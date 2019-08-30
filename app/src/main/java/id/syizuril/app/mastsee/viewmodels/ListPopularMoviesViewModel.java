package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.BuildConfig;
import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.repositories.ListMoviesRepositories;

public class ListPopularMoviesViewModel extends ViewModel {
    private MutableLiveData<MovieResponse> mutableLiveData;
    private ListMoviesRepositories listMoviesRepositories;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        listMoviesRepositories = ListMoviesRepositories.getInstance();
        mutableLiveData = listMoviesRepositories.getMovieResult("movie","popular", BuildConfig.TMDB_API_KEY,"en_US",1);
    }

    public LiveData<Boolean> getIsConnected(){
        return listMoviesRepositories.getIsConnected();
    }

    public LiveData<MovieResponse> getMovieResultList(){
        return mutableLiveData;
    }
}
