package id.syizuril.app.mastsee.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

import id.syizuril.app.mastsee.widgets.TvStackRemoteViewsFactory;

public class TvStackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TvStackRemoteViewsFactory(this.getApplicationContext());
    }
}
