package tkhub.project.mscoba;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.sql.SQLOutput;
import java.util.Map;

import tkhub.project.mscoba.Layout.Event;
import tkhub.project.mscoba.Layout.EventFragment;
import tkhub.project.mscoba.Layout.Gallery;
import tkhub.project.mscoba.Layout.News;
import tkhub.project.mscoba.Layout.Newslatters;
import tkhub.project.mscoba.Layout.Splash;


/**
 * Created by Himanshu on 10/8/2016.
 */

public class FCmMessagingSevies extends FirebaseMessagingService {



    String key;
    String value;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String titel = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

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
        Intent resultIntent = null;



       switch (value) {
            case "1":
               resultIntent = new Intent(this, News.class);
                break;
            case "2":
               resultIntent = new Intent(this, Event.class);
                break;
            case "3":
               resultIntent = new Intent(this, Newslatters.class);
                break;
            case "4":
                resultIntent = new Intent(this, Gallery.class);
                break;
            case "5":
                resultIntent = new Intent(Intent.ACTION_VIEW);
                resultIntent.setData(Uri.parse("market://details?id=tkhub.project.mscoba"));

        }




        TaskStackBuilder stackBuilder = null;
        PendingIntent resultPendingIntent = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            stackBuilder = TaskStackBuilder.create(this);
          //  stackBuilder.addParentStack(News.class);

            stackBuilder.addNextIntent(resultIntent);
            resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        } else {

            resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);

        }

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
        super.onMessageReceived(remoteMessage);
    }

}
