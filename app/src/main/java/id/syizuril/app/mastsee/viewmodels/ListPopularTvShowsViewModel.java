package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.models.TvShowResponse;
import id.syizuril.app.mastsee.repositories.ListTvShowsRepositories;

public class ListPopularTvShowsViewModel extends ViewModel {
    private MutableLiveData<TvShowResponse> mutableLiveData;
    private ListTvShowsRepositories listTvShowsRepositories;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        listTvShowsRepositories = ListTvShowsRepositories.getInstance();
        mutableLiveData = listTvShowsRepositories.getTvShowsResult("tv","popular","b2d6f482d73c8f231cd3af7c9085e7a5","en_US",1);
    }
    public LiveData<Boolean> getIsConnected(){
        return listTvShowsRepositories.getIsConnected();
    }
    public LiveData<TvShowResponse> getTvShowsResultList(){
        return mutableLiveData;
    }
}
