package id.syizuril.app.mastsee.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import id.syizuril.app.mastsee.FavoriteDatabase;
import id.syizuril.app.mastsee.MovieFavoriteDao;
import id.syizuril.app.mastsee.models.MovieResult;

public class MoviesFavoriteRepository {
    private MovieFavoriteDao movieFavoriteDao;
    private LiveData<List<MovieResult>> allMovies;
    private LiveData<List<MovieResult>> allMoviesById;

    public MoviesFavoriteRepository(Application application){
        FavoriteDatabase database = FavoriteDatabase.getInstance(application);
        movieFavoriteDao = database.movieFavoriteDao();
        allMovies = movieFavoriteDao.getAllMoviesFavorite();
    }

    public void insert(MovieResult movieResult){
        new InsertNoteAsyncTask(movieFavoriteDao).execute(movieResult);
    }

    public void update(MovieResult movieResult){
        new UpdateNoteAsyncTask(movieFavoriteDao).execute(movieResult);
    }

    public void delete(MovieResult movieResult){
        new DeleteNoteAsyncTask(movieFavoriteDao).execute(movieResult);
    }

    public void deleteAllMovies(){
        new DeleteAllNoteAsyncTask(movieFavoriteDao).execute();
    }

    public LiveData<List<MovieResult>> getAllMovies(){
        return allMovies;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<MovieResult, Void, Void>{
        private MovieFavoriteDao movieFavoriteDao;

        private InsertNoteAsyncTask(MovieFavoriteDao movieFavoriteDao){
            this.movieFavoriteDao = movieFavoriteDao;
        }

        @Override
        protected Void doInBackground(MovieResult... movieResults) {
            movieFavoriteDao.insert(movieResults[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<MovieResult, Void, Void>{
        private MovieFavoriteDao movieFavoriteDao;

        private UpdateNoteAsyncTask(MovieFavoriteDao movieFavoriteDao){
            this.movieFavoriteDao = movieFavoriteDao;
        }

        @Override
        protected Void doInBackground(MovieResult... movieResults) {
            movieFavoriteDao.update(movieResults[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<MovieResult, Void, Void>{
        private MovieFavoriteDao movieFavoriteDao;

        private DeleteNoteAsyncTask(MovieFavoriteDao movieFavoriteDao){
            this.movieFavoriteDao = movieFavoriteDao;
        }

        @Override
        protected Void doInBackground(MovieResult... movieResults) {
            movieFavoriteDao.delete(movieResults[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<MovieResult, Void, Void>{
        private MovieFavoriteDao movieFavoriteDao;

        private DeleteAllNoteAsyncTask(MovieFavoriteDao movieFavoriteDao){
            this.movieFavoriteDao = movieFavoriteDao;
        }

        @Override
        protected Void doInBackground(MovieResult... movieResults) {
            movieFavoriteDao.deleteAllMoviesFavorite();
            return null;
        }
    }
}
