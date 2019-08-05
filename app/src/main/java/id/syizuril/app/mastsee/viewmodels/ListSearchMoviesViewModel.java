package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import id.syizuril.app.mastsee.models.SearchResponse;
import id.syizuril.app.mastsee.repositories.ListSearchMoviesRepositories;

public class ListSearchMoviesViewModel extends ViewModel {
    private MutableLiveData<SearchResponse> mutableLiveData;
    private MutableLiveData<Boolean> isConnected;
    private ListSearchMoviesRepositories listSearchRepositories;

    public void init(String query){
        if(mutableLiveData != null){
            return;
        }
        listSearchRepositories = ListSearchMoviesRepositories.getInstance();
        mutableLiveData = listSearchRepositories.getSearchMovieResult("search","multi","b2d6f482d73c8f231cd3af7c9085e7a5","en_US",query,1,false);
    }

    public LiveData<Boolean> getIsConnected(){
        return listSearchRepositories.getIsConnected();
    }

    public LiveData<SearchResponse> getSearchResultList(){
        return mutableLiveData;
    }
}
