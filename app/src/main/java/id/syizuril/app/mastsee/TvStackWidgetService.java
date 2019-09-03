package id.syizuril.app.mastsee;

import android.content.Intent;
import android.widget.RemoteViewsService;

public class TvStackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new TvStackRemoteViewsFactory(this.getApplicationContext());
    }
}
