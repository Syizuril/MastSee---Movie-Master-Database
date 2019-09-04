package id.syizuril.app.mastsee.provider;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;

import id.syizuril.app.mastsee.db.FavoriteDatabase;
import id.syizuril.app.mastsee.db.TvShowFavoriteDao;
import id.syizuril.app.mastsee.models.TvShowsResult;

public class TvContentProvider extends ContentProvider {
    public static final String AUTHORITY = "id.syizuril.app.mastsee.provider.TvContentProvider";
    public static final Uri URI_TV = Uri.parse("content://"+AUTHORITY+"/"+ TvShowsResult.TABLE_NAME);

    private static final int CODE_MOVIE_DIR = 3;
    private static final int CODE_MOVIE_ITEM = 4;
    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        MATCHER.addURI(AUTHORITY, TvShowsResult.TABLE_NAME, CODE_MOVIE_DIR);
        MATCHER.addURI(AUTHORITY, TvShowsResult.TABLE_NAME +"/*",CODE_MOVIE_ITEM);
    }

    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = MATCHER.match(uri);
        if(code == CODE_MOVIE_DIR || code == CODE_MOVIE_ITEM){
            final Context context = getContext();
            if(context == null){
                return null;
            }
            TvShowFavoriteDao tvShowFavoriteDao = FavoriteDatabase.getInstance(context).tvShowFavoriteDao();
            final Cursor cursor;
            if(code == CODE_MOVIE_DIR){
                cursor = tvShowFavoriteDao.selectAll();
            }else{
                cursor = tvShowFavoriteDao.selectById(ContentUris.parseId(uri));
            }
            cursor.setNotificationUri(context.getContentResolver(), uri);
            return cursor;
        }else{
            throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (MATCHER.match(uri)){
            case CODE_MOVIE_DIR:
                return "vnd.android.cursor.dir/"+AUTHORITY+"."+TvShowsResult.TABLE_NAME;
            case CODE_MOVIE_ITEM:
                return "vnd.android.cursor.item/"+AUTHORITY+"."+TvShowsResult.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown URI: "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (MATCHER.match(uri)){
            case CODE_MOVIE_DIR:
                final Context context = getContext();
                if(context == null){
                    return null;
                }
                assert values != null;
                final long id = FavoriteDatabase.getInstance(context).tvShowFavoriteDao().insertMovie(TvShowsResult.fromContentValues(values));
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            case CODE_MOVIE_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: "+uri);
            default:
                throw new IllegalArgumentException("Uknown URI: "+uri);
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        switch (MATCHER.match(uri)){
            case CODE_MOVIE_DIR:
                throw new IllegalArgumentException("Invalid URI, cannot update without ID"+uri);
            case CODE_MOVIE_ITEM:
                final Context context = getContext();
                if(context == null){
                    return 0;
                }
                final int count = FavoriteDatabase.getInstance(context).tvShowFavoriteDao().deleteById(ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return count;
            default:
                throw new IllegalArgumentException("Uknown URI: "+uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @SuppressWarnings("RedundantThrows") /* This gets propagated up from the Callable */
    @NonNull
    @Override
    public ContentProviderResult[] applyBatch(
            @NonNull final ArrayList<ContentProviderOperation> operations)
            throws OperationApplicationException {
        final Context context = getContext();
        if (context == null) {
            return new ContentProviderResult[0];
        }
        final FavoriteDatabase database = FavoriteDatabase.getInstance(context);
        return database.runInTransaction(() -> TvContentProvider.super.applyBatch(operations));
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] valuesArray) {
        switch (MATCHER.match(uri)){
            case CODE_MOVIE_DIR:
                final Context context = getContext();
                if(context == null){
                    return 0;
                }
                final FavoriteDatabase database = FavoriteDatabase.getInstance(context);
                final TvShowsResult[] tvShowsResults = new TvShowsResult[valuesArray.length];
                for(int i=0; i<valuesArray.length; i++){
                    tvShowsResults[i] = TvShowsResult.fromContentValues(valuesArray[i]);
                }
                return database.tvShowFavoriteDao().insertAll(tvShowsResults).length;
            case CODE_MOVIE_ITEM:
                throw new IllegalArgumentException("Invalid URI, cannot insert with ID: " + uri);
            default:
                throw new IllegalArgumentException("Unknown URI: "+uri);
        }
    }
}
