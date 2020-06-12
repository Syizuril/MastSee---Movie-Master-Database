package id.syizuril.app.mastsee.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

import id.syizuril.app.mastsee.widgets.MovieStackRemoteViewsFactory;
/**
 * Created by Syekh Syihabuddin Azmil Umri on 11.06.2020.
 * NPM : 1710631170023
 */
public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MovieStackRemoteViewsFactory(this.getApplicationContext());
    }
}
