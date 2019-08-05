package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

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
        mutableLiveData = listMoviesRepositories.getMovieResult("movie","popular","b2d6f482d73c8f231cd3af7c9085e7a5","en_US",1);
    }

    public LiveData<Boolean> getIsConnected(){
        return listMoviesRepositories.getIsConnected();
    }

    public LiveData<MovieResponse> getMovieResultList(){
        return mutableLiveData;
    }
}
