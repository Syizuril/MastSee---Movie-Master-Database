package id.syizuril.app.mastsee;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import id.syizuril.app.mastsee.models.MovieResponse;
import id.syizuril.app.mastsee.models.MovieResult;
import id.syizuril.app.mastsee.requests.MovieApi;
import id.syizuril.app.mastsee.requests.RetrofitService;
import id.syizuril.app.mastsee.view.MainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DailyReleaseReceiver extends BroadcastReceiver {

    private final int DAILY_RELEASE_ID = 102;
    private List<MovieResult> movieResults = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        getMovieReleaseToday(context);
    }

    private void getMovieReleaseToday(final Context context){
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String today = dateFormat.format(date);
        MovieApi movieApi = RetrofitService.getMovieApi();
        Call<MovieResponse> call = movieApi.getMovieRelease("discover","movie",BuildConfig.TMDB_API_KEY, today, today);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if(response.isSuccessful() && response.body()!=null){
                    movieResults = response.body().getResults();
                    showAlarmNotification(context);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    public Bitmap getBitmapFromURL(String strURL) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void showAlarmNotification(Context context){
        String CHANNEL_ID = "Channel_2";
        String CHANNEL_NAME = "MastseeReleaseReminder channel";

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        int movies = 0;
        try{
            movies = ((movieResults.size()>0) ? movieResults.size():0);
        }catch (Exception e){
            Log.d("ERROR",e.getMessage());
        }

        String msg = "";
        if(movies == 0) {
            msg = context.getResources().getString(R.string.no_movies_today);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                    .setContentTitle(context.getResources().getString(R.string.content_title_release_reminder))
                    .setContentText(msg)
                    .setSubText(context.getResources().getString(R.string.release_reminder))
                    .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                    .setSound(alarmSound);

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(pendingIntent);
            builder.setAutoCancel(true);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

                builder.setChannelId(CHANNEL_ID);

                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(channel);
                }
            }

            Notification notification = builder.build();

            if (notificationManager != null) {
                notificationManager.notify(DAILY_RELEASE_ID, notification);
            }
        }else{
            for(int i = 0; i<movies; i++){
                msg = movieResults.get(i).getTitle() + " " + context.getResources().getString(R.string.has_been_release_today);
                Bitmap bitmap = getBitmapFromURL("https://image.tmdb.org/t/p/w533_and_h300_bestv2/"+movieResults.get(i).getBackdropPathAlt());
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setContentTitle(context.getResources().getString(R.string.content_title_release_reminder))
                        .setContentText(msg)
                        .setSubText(context.getResources().getString(R.string.release_reminder))
                        .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                        .setLargeIcon(bitmap)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                        .setSound(alarmSound);

                Intent intent = new Intent(context, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

                    channel.enableVibration(true);
                    channel.setVibrationPattern(new long[]{1000, 1000, 1000, 1000, 1000});

                    builder.setChannelId(CHANNEL_ID);

                    if (notificationManager != null) {
                        notificationManager.createNotificationChannel(channel);
                    }
                }

                Notification notification = builder.build();

                if (notificationManager != null) {
                    notificationManager.notify(DAILY_RELEASE_ID, notification);
                }
            }
        }
    }

    public void setDailyReminder(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReleaseReceiver.class);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_RELEASE_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if(alarmManager != null){
            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        }
    }

    public void cancelDailyReminder(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, DailyReleaseReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, DAILY_RELEASE_ID, intent, 0);
        pendingIntent.cancel();

        if(alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }
    }
}
