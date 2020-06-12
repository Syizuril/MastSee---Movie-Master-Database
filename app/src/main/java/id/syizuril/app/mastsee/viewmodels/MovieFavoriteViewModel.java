package id.syizuril.app.mastsee.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.repositories.MoviesFavoriteRepository;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class MovieFavoriteViewModel extends AndroidViewModel {
    private MoviesFavoriteRepository repository;
    private LiveData<List<MovieResult>> allMovies;

    public MovieFavoriteViewModel(@NonNull Application application) {
        super(application);
        repository = new MoviesFavoriteRepository(application);
        allMovies = repository.getAllMovies();
    }

    public void insert(MovieResult movieResult){
        repository.insert(movieResult);
    }

    public void delete(MovieResult movieResult){
        repository.delete(movieResult);
    }

    public void deleteAllMovies(){
        repository.deleteAllMovies();
    }

    public LiveData<List<MovieResult>> getAllMovies(){
        return allMovies;
    }

    public LiveData<List<MovieResult>> getMoviesById(Long id){
        return repository.getAllMoviesById(id);
    }
}
