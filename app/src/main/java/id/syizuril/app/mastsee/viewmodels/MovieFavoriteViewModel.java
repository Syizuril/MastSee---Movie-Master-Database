package id.syizuril.app.mastsee.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.repositories.MoviesFavoriteRepository;

public class MovieFavoriteViewModel extends AndroidViewModel {
    private MoviesFavoriteRepository repository;
    private LiveData<List<MovieResult>> allMovies;
    private LiveData<List<MovieResult>> moviesById;
    private Integer id;

    public MovieFavoriteViewModel(@NonNull Application application) {
        super(application);
        repository = new MoviesFavoriteRepository(application);
        allMovies = repository.getAllMovies();
    }

    public void insert(MovieResult movieResult){
        repository.insert(movieResult);
    }

    public void update(MovieResult movieResult){
        repository.update(movieResult);
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

    public LiveData<List<MovieResult>> getMoviesById(Integer id){
        moviesById = repository.getAllMoviesById(id);
        return moviesById;
    }
}
