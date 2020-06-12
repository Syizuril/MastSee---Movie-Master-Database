package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.BuildConfig;
import id.syizuril.app.mastsee.models.TvShowResponse;
import id.syizuril.app.mastsee.repositories.ListMoviesRepository;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class ListPopularTvShowsViewModel extends ViewModel {
    private MutableLiveData<TvShowResponse> mutableLiveData;
    private ListMoviesRepository listTvShowsRepositories;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        listTvShowsRepositories = ListMoviesRepository.getInstance();
        mutableLiveData = listTvShowsRepositories.getTvShowsResult("tv","popular", BuildConfig.TMDB_API_KEY,"en_US",1);
    }
    public LiveData<Boolean> getIsConnected(){
        return listTvShowsRepositories.getIsConnected();
    }
    public LiveData<TvShowResponse> getTvShowsResultList(){
        return mutableLiveData;
    }
}
