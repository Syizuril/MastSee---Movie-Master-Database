package id.syizuril.app.mastsee.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import id.syizuril.app.mastsee.models.TvShowsResult;

@Dao
public interface TvShowFavoriteDao {

    @Insert
    void insert(TvShowsResult tvShowsResult);

    @Update
    void update(TvShowsResult tvShowsResult);

    @Delete
    void delete(TvShowsResult tvShowsResult);

    @Query("DELETE FROM tvshows_table")
    void deleteAllMoviesFavorite();

    @Query("SELECT * FROM tvshows_table")
    LiveData<List<TvShowsResult>> getAllMoviesFavorite();

    @Query("SELECT * FROM tvshows_table WHERE id LIKE :value")
    LiveData<List<TvShowsResult>> getAllMoviesFavoriteById(Integer value);
}
