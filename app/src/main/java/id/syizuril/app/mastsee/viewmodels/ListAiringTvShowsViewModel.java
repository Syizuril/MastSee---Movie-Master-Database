package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.BuildConfig;
import id.syizuril.app.mastsee.models.TvShowResponse;
import id.syizuril.app.mastsee.repositories.ListMoviesRepository;

public class ListAiringTvShowsViewModel extends ViewModel {
    private MutableLiveData<TvShowResponse> mutableLiveData;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        ListMoviesRepository listTvShowsRepositories = ListMoviesRepository.getInstance();
        mutableLiveData = listTvShowsRepositories.getTvShowsResult("tv","on_the_air", BuildConfig.TMDB_API_KEY,"en_US",1);
    }

    public LiveData<TvShowResponse> getTvShowsResultList(){
        return mutableLiveData;
    }
}
