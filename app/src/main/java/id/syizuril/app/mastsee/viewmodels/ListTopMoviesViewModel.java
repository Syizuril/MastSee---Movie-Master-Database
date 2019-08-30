package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.BuildConfig;
import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.repositories.ListMoviesRepository;

public class ListTopMoviesViewModel extends ViewModel {
    private MutableLiveData<MovieResponse> mutableLiveData;
    private ListMoviesRepository listMoviesRepository;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        listMoviesRepository = ListMoviesRepository.getInstance();
        mutableLiveData = listMoviesRepository.getMovieResult("movie","top_rated", BuildConfig.TMDB_API_KEY,"en_US",1);
    }
    public LiveData<Boolean> getIsConnected(){
        return listMoviesRepository.getIsConnected();
    }
    public LiveData<MovieResponse> getMovieResultList(){
        return mutableLiveData;
    }
}
