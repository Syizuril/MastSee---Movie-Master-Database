package id.syizuril.app.mastsee;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import id.syizuril.app.mastsee.models.MovieResult;

@Dao
public interface MovieFavoriteDao {

    @Insert
    void insert(MovieResult movieResult);

    @Update
    void update(MovieResult movieResult);

    @Delete
    void delete(MovieResult movieResult);

    @Query("DELETE FROM movie_table")
    void deleteAllMoviesFavorite();

    @Query("SELECT * FROM movie_table")
    LiveData<List<MovieResult>> getAllMoviesFavorite();

    @Query("SELECT * FROM movie_table WHERE id LIKE :value")
    LiveData<List<MovieResult>> getAllMoviesFavoriteById(Integer value);
}
