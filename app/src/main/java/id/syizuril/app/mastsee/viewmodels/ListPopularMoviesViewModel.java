package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.repositories.ListPopularMoviesRepositories;

public class ListPopularMoviesViewModel extends ViewModel {

    private MutableLiveData<MovieResponse> mutableLiveData;
    private ListPopularMoviesRepositories listPopularMoviesRepositories;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        listPopularMoviesRepositories = ListPopularMoviesRepositories.getInstance();
        mutableLiveData = listPopularMoviesRepositories.getMovieResult("movie","popular","b2d6f482d73c8f231cd3af7c9085e7a5","en_US",1);
    }

    public LiveData<MovieResponse> getMovieResultList(){
        return mutableLiveData;
    }
}
