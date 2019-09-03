package id.syizuril.app.mastsee.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import id.syizuril.app.mastsee.models.TvShowsResult;

@Dao
public interface TvShowFavoriteDao {

    @Insert
    void insert(TvShowsResult tvShowsResult);

    @Insert
    long insertMovie(TvShowsResult tvShowsResult);

    @Insert
    long[] insertAll(TvShowsResult[] tvShowsResults);

    @Delete
    void delete(TvShowsResult tvShowsResult);

    @Query("DELETE FROM tvshows_table")
    void deleteAllMoviesFavorite();

    @Query("SELECT * FROM " + TvShowsResult.TABLE_NAME)
    LiveData<List<TvShowsResult>> getAllMoviesFavorite();

    @Query("SELECT * FROM " + TvShowsResult.TABLE_NAME)
    Cursor selectAll();

    @Query("SELECT * FROM " + TvShowsResult.TABLE_NAME + " WHERE "+ TvShowsResult.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    @Query("SELECT * FROM tvshows_table WHERE _id LIKE :value")
    LiveData<List<TvShowsResult>> getAllMoviesFavoriteById(Long value);

    @Query("DELETE FROM " + TvShowsResult.TABLE_NAME + " WHERE " + TvShowsResult.COLUMN_ID +" = :id")
    int deleteById(long id);
}
