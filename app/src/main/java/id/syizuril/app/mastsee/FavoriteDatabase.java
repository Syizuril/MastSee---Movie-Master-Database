package id.syizuril.app.mastsee;

import android.annotation.SuppressLint;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.syizuril.app.mastsee.models.MovieResult;

@Database(entities = {MovieResult.class}, version = 1, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class FavoriteDatabase extends RoomDatabase {

    private static FavoriteDatabase instance;

    public abstract MovieFavoriteDao movieFavoriteDao();

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
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private MovieFavoriteDao movieFavoriteDao;

        private PopulateDbAsyncTask(FavoriteDatabase db){
            movieFavoriteDao = db.movieFavoriteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String releaseDate = "Agt 01, 2019";
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
            try{
                Date date = formatter.parse(releaseDate);
                movieFavoriteDao.insert(new MovieResult(610,384018,6.5,"Fast & Furious Presents: Hobbs & Shaw",388.398,"/keym7MPn1icW1wWfzMnW3HeuzWU.jpg","en","Fast & Furious Presents: Hobbs & Shaw","/hpgda6P9GutvdkDX5MUJ92QG9aj.jpg","A spinoff of The Fate of the Furious, focusing on Johnson's US Diplomatic Security Agent Luke Hobbs forming an unlikely alliance with Statham's Deckard Shaw.",date));
            }catch (ParseException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
