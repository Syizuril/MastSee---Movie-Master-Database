package id.syizuril.app.mastsee.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.repositories.ListPopularMoviesRepositories;

public class ListPopularMoviesViewModel extends ViewModel {

    private MutableLiveData<List<MovieResult>> mMoviesTVShows;
    private ListPopularMoviesRepositories mRepo;
    public void init(){
        if(mMoviesTVShows != null){
            return;
        }
        mRepo = ListPopularMoviesRepositories.getInstance();
        mMoviesTVShows = mRepo.getMoviesTVShows();
    }

    public LiveData<List<MovieResult>> getMoviesTVShows(){
        return mMoviesTVShows;
    }
}
