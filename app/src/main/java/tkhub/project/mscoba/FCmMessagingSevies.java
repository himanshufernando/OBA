package tkhub.project.mscoba;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.sql.SQLOutput;
import java.util.Map;

import tkhub.project.mscoba.Layout.News;
import tkhub.project.mscoba.Layout.Splash;


/**
 * Created by Himanshu on 10/8/2016.
 */

public class FCmMessagingSevies extends FirebaseMessagingService {


    private static final String TAG = "kkkkkkkkkk";
    String key;
    String value;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String titel =remoteMessage.getNotification().getTitle();
        String message =remoteMessage.getNotification().getBody();

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
             key = entry.getKey();
             value = entry.getValue();

        }




        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle(titel)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentText(message);
        Intent resultIntent ;




         resultIntent = new Intent(this, News.class);


        TaskStackBuilder stackBuilder = null;
        PendingIntent resultPendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(News.class);

            stackBuilder.addNextIntent(resultIntent);
            resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        }else {

            resultPendingIntent= PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);

        }

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
        super.onMessageReceived(remoteMessage);
    }
}
