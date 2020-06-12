package id.syizuril.app.mastsee.widgets;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import id.syizuril.app.mastsee.R;
import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.provider.MovieContentProvider;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class MovieStackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private List<MovieResult> mWidgetItems = new ArrayList<>();
    private Cursor cursor;
    private final Context mContext;

    public MovieStackRemoteViewsFactory(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        if (cursor != null) {
            cursor.close();
        }

        final long identityToken = Binder.clearCallingIdentity();
        cursor = mContext.getContentResolver().query(MovieContentProvider.URI_MOVIE,null, null, null, null);
        assert cursor != null;
        while (cursor.moveToNext()) {
            Integer voteCount = cursor.getInt(cursor.getColumnIndexOrThrow(MovieResult.COLUMN_VOTE_COUNT));
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(MovieResult.COLUMN_ID));
            String title = cursor.getString(cursor.getColumnIndexOrThrow(MovieResult.COLUMN_TILE));
            String posterPath = cursor.getString(cursor.getColumnIndexOrThrow(MovieResult.COLUMN_POSTER));
            String backdropPath = cursor.getString(cursor.getColumnIndexOrThrow(MovieResult.COLUMN_BACKDROP));
            mWidgetItems.add(new MovieResult(voteCount, id, title, posterPath, backdropPath));
        }
        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public int getCount() {
        return mWidgetItems.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.movie_widget_item);
        try {
            Bitmap bitmap = Glide.with(mContext)
                    .asBitmap()
                    .load(mWidgetItems.get(position).backdropPath)
                    .apply(new RequestOptions().fitCenter())
                    .submit(512,512)
                    .get();
            rv.setImageViewBitmap(R.id.imageView, bitmap);
        } catch (Exception e){
            e.printStackTrace();
        }

        Bundle extras = new Bundle();
        extras.putInt(MovieFavoriteWidget.EXTRA_ITEM, position);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.imageView, fillInIntent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
