package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.models.TvShowResponse;
import id.syizuril.app.mastsee.repositories.ListTvShowsRepositories;

public class ListAiringTvShowsViewModel extends ViewModel {
    private MutableLiveData<TvShowResponse> mutableLiveData;

    public void init(){
        if(mutableLiveData != null){
            return;
        }
        ListTvShowsRepositories listTvShowsRepositories = ListTvShowsRepositories.getInstance();
        mutableLiveData = listTvShowsRepositories.getTvShowsResult("tv","on_the_air","b2d6f482d73c8f231cd3af7c9085e7a5","en_US",1);
    }

    public LiveData<TvShowResponse> getTvShowsResultList(){
        return mutableLiveData;
    }
}
