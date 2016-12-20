package tkhub.project.mscoba;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tkhub.project.mscoba.Layout.News;


/**
 * Created by Himanshu on 10/8/2016.
 */

public class FCMInsnaceIdSeervies extends FirebaseInstanceIdService {
    String resToken;
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        resToken = FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new saveToken().execute();
    }

    private class saveToken extends AsyncTask<String, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                RequestBody formBody = new FormBody.Builder()
                        .add("RegID", resToken)
                        .build();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.marisstellaoba.com/App/php/register.php").post(formBody).build();
                Response responses = null;
                responses = client.newCall(request).execute();


            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            try {
                Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(FCMInsnaceIdSeervies.this)
                                .setSmallIcon(R.drawable.notificationlogo)
                                .setContentTitle("Welcome")
                                .setAutoCancel(true)
                                .setSound(defaultSoundUri)
                                .setContentText("Welcome to Maris Stella OBA");

                Intent resultIntent = new Intent(FCMInsnaceIdSeervies.this, News.class);


                TaskStackBuilder stackBuilder = null;
                PendingIntent resultPendingIntent = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    stackBuilder = TaskStackBuilder.create(FCMInsnaceIdSeervies.this);
                    stackBuilder.addParentStack(News.class);

                    stackBuilder.addNextIntent(resultIntent);
                    resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                }else {
                    resultPendingIntent= PendingIntent.getActivity(FCMInsnaceIdSeervies.this, 0, resultIntent, PendingIntent.FLAG_ONE_SHOT);
                }
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(0, mBuilder.build());

            }catch (Exception ex){

            }

        }

    }


}
