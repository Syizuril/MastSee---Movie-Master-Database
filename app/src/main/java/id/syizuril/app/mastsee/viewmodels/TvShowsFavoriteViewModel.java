package id.syizuril.app.mastsee.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import id.syizuril.app.mastsee.models.TvShowsResult;
import id.syizuril.app.mastsee.repositories.TvShowsFavoriteRepository;

public class TvShowsFavoriteViewModel extends AndroidViewModel {
    private TvShowsFavoriteRepository repository;
    private LiveData<List<TvShowsResult>> allTvShows;
    private LiveData<List<TvShowsResult>> tvShowsById;
    private Integer id;

    public TvShowsFavoriteViewModel(@NonNull Application application) {
        super(application);
        repository = new TvShowsFavoriteRepository(application);
        allTvShows = repository.getAllMovies();
    }

    public void insert(TvShowsResult tvShowsResult){
        repository.insert(tvShowsResult);
    }

    public void update(TvShowsResult tvShowsResult){
        repository.update(tvShowsResult);
    }

    public void delete(TvShowsResult tvShowsResult){
        repository.delete(tvShowsResult);
    }

    public void deleteAllMovies(){
        repository.deleteAllMovies();
    }

    public LiveData<List<TvShowsResult>> getAllMovies(){
        return allTvShows;
    }

    public LiveData<List<TvShowsResult>> getMoviesById(Integer id){
        tvShowsById = repository.getAllMoviesById(id);
        return tvShowsById;
    }
}
