package id.syizuril.app.mastsee.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import id.syizuril.app.mastsee.db.FavoriteDatabase;
import id.syizuril.app.mastsee.db.TvShowFavoriteDao;
import id.syizuril.app.mastsee.models.TvShowsResult;

public class TvShowsFavoriteRepository {
    private TvShowFavoriteDao tvShowFavoriteDao;
    private LiveData<List<TvShowsResult>> allTvShows;

    public TvShowsFavoriteRepository(Application application){
        FavoriteDatabase database = FavoriteDatabase.getInstance(application);
        tvShowFavoriteDao = database.tvShowFavoriteDao();
        allTvShows = tvShowFavoriteDao.getAllMoviesFavorite();
    }

    public void insert(TvShowsResult movieResult){
        new InsertNoteAsyncTask(tvShowFavoriteDao).execute(movieResult);
    }

    public void delete(TvShowsResult movieResult){
        new DeleteNoteAsyncTask(tvShowFavoriteDao).execute(movieResult);
    }

    public void deleteAllMovies(){
        new DeleteAllNoteAsyncTask(tvShowFavoriteDao).execute();
    }

    public LiveData<List<TvShowsResult>> getAllMovies(){
        return allTvShows;
    }

    public LiveData<List<TvShowsResult>> getAllMoviesById(Integer id){
        return tvShowFavoriteDao.getAllMoviesFavoriteById(id);
    }

    private static class InsertNoteAsyncTask extends AsyncTask<TvShowsResult, Void, Void>{
        private TvShowFavoriteDao tvShowFavoriteDao;

        private InsertNoteAsyncTask(TvShowFavoriteDao tvShowFavoriteDao){
            this.tvShowFavoriteDao = tvShowFavoriteDao;
        }

        @Override
        protected Void doInBackground(TvShowsResult... tvShowResults) {
            tvShowFavoriteDao.insert(tvShowResults[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<TvShowsResult, Void, Void>{
        private TvShowFavoriteDao tvShowFavoriteDao;

        private UpdateNoteAsyncTask(TvShowFavoriteDao tvShowFavoriteDao){
            this.tvShowFavoriteDao = tvShowFavoriteDao;
        }

        @Override
        protected Void doInBackground(TvShowsResult... tvShowResults) {
            tvShowFavoriteDao.update(tvShowResults[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<TvShowsResult, Void, Void>{
        private TvShowFavoriteDao tvShowFavoriteDao;

        private DeleteNoteAsyncTask(TvShowFavoriteDao tvShowFavoriteDao){
            this.tvShowFavoriteDao = tvShowFavoriteDao;
        }

        @Override
        protected Void doInBackground(TvShowsResult... tvShowResults) {
            tvShowFavoriteDao.delete(tvShowResults[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<TvShowsResult, Void, Void>{
        private TvShowFavoriteDao tvShowFavoriteDao;

        private DeleteAllNoteAsyncTask(TvShowFavoriteDao tvShowFavoriteDao){
            this.tvShowFavoriteDao = tvShowFavoriteDao;
        }

        @Override
        protected Void doInBackground(TvShowsResult... tvShowResults) {
            tvShowFavoriteDao.deleteAllMoviesFavorite();
            return null;
        }
    }
}
