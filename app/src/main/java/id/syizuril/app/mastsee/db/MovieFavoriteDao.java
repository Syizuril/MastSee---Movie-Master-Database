package id.syizuril.app.mastsee.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.database.Cursor;

import java.util.List;

import id.syizuril.app.mastsee.models.MovieResult;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
@Dao
public interface MovieFavoriteDao {

    @Insert
    void insert(MovieResult movieResult);

    @Insert
    long insertMovie(MovieResult movieResult);

    @Insert
    long[] insertAll(MovieResult[] movieResult);

    @Delete
    void delete(MovieResult movieResult);

    @Query("DELETE FROM movie_table")
    void deleteAllMoviesFavorite();

    @Query("SELECT * FROM " + MovieResult.TABLE_NAME)
    LiveData<List<MovieResult>> getAllMoviesFavorite();

    @Query("SELECT * FROM " + MovieResult.TABLE_NAME)
    Cursor selectAll();

    @Query("SELECT * FROM " + MovieResult.TABLE_NAME + " WHERE "+ MovieResult.COLUMN_ID + " = :id")
    Cursor selectById(long id);

    @Query("SELECT * FROM movie_table WHERE _id LIKE :value")
    LiveData<List<MovieResult>> getAllMoviesFavoriteById(Long value);

    @Query("DELETE FROM " + MovieResult.TABLE_NAME + " WHERE " + MovieResult.COLUMN_ID +" = :id")
    int deleteById(long id);
}
