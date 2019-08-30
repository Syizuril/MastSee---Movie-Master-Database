package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.BuildConfig;
import id.syizuril.app.mastsee.models.TvShowResponse;
import id.syizuril.app.mastsee.repositories.ListMoviesRepository;

public class ListTopTvShowsViewModel extends ViewModel {
    private MutableLiveData<TvShowResponse> mutableLiveData;
    private ListMoviesRepository listTvShowsRepositories;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        listTvShowsRepositories = ListMoviesRepository.getInstance();
        mutableLiveData = listTvShowsRepositories.getTvShowsResult("tv","top_rated", BuildConfig.TMDB_API_KEY,"en_US",1);
    }
    public LiveData<Boolean> getIsConnected(){
        return listTvShowsRepositories.getIsConnected();
    }
    public LiveData<TvShowResponse> getTvShowsResultList(){
        return mutableLiveData;
    }
}
