package id.syizuril.app.mastsee;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.models.TvShowsResult;

@Database(entities = {MovieResult.class, TvShowsResult.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class FavoriteDatabase extends RoomDatabase {

    private static FavoriteDatabase instance;

    public abstract MovieFavoriteDao movieFavoriteDao();

    public abstract TvShowFavoriteDao tvShowFavoriteDao();

    public static synchronized FavoriteDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FavoriteDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };
}
