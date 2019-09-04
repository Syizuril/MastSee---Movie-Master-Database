package id.syizuril.app.mastsee.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

import id.syizuril.app.mastsee.widgets.MovieStackRemoteViewsFactory;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MovieStackRemoteViewsFactory(this.getApplicationContext());
    }
}
