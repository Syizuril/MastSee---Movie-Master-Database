package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.BuildConfig;
import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.repositories.ListMoviesRepository;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class ListSearchMoviesViewModel extends ViewModel {
    private MutableLiveData<MovieResponse> mutableLiveData;
    private ListMoviesRepository listSearchRepositories;

    public void init(String query){
        if(mutableLiveData != null){
            return;
        }
        listSearchRepositories = ListMoviesRepository.getInstance();
        mutableLiveData = listSearchRepositories.getSearchMovieResult("search","movie", BuildConfig.TMDB_API_KEY,"en-US",query);
    }

    public LiveData<Boolean> getIsConnected(){
        return listSearchRepositories.getIsConnected();
    }

    public LiveData<MovieResponse> getSearchResultList(){
        return mutableLiveData;
    }
}
