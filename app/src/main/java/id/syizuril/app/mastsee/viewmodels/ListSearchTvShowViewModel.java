package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.BuildConfig;
import id.syizuril.app.mastsee.models.TvShowResponse;
import id.syizuril.app.mastsee.repositories.ListMoviesRepository;

public class ListSearchTvShowViewModel extends ViewModel {
    private MutableLiveData<TvShowResponse> mutableLiveData;
    private ListMoviesRepository listSearchRepositories;

    public void init(String query){
        if(mutableLiveData != null){
            return;
        }
        listSearchRepositories = ListMoviesRepository.getInstance();
        mutableLiveData = listSearchRepositories.getSearchTvShowsResult("search","tv", BuildConfig.TMDB_API_KEY,"en-US",query);
    }

    public LiveData<Boolean> getIsConnected(){
        return listSearchRepositories.getIsConnected();
    }

    public LiveData<TvShowResponse> getSearchResultList(){
        return mutableLiveData;
    }
}
