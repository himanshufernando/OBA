package tkhub.project.mscoba.Layout;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;

import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.TimeZone;

import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/25/2016.
 */
public class EventFragment extends Activity {


    private static final int MY_PERMISSIONS_REQUEST_WRITE_CALENDAR = 123;
    Intent intentNews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event);

        ImageView coverImage = (ImageView) findViewById(R.id.imageView_fragment_event);
        final TextView title = (TextView) findViewById(R.id.textView_fragment_event_title);
        final TextView content = (TextView) findViewById(R.id.textView_fragment_event_content);
        final TextView date = (TextView) findViewById(R.id.textView_fragment_event_date);
        final TextView vanue = (TextView) findViewById(R.id.textView_fragment_event_vanue);


        final RelativeLayout iconLocation = (RelativeLayout) findViewById(R.id.relative_event_map);
        intentNews = getIntent();

        try {
            Picasso.with(this).load(intentNews.getStringExtra("url")).into(coverImage);
        }catch (Exception ex){

        }

        title.setText(intentNews.getStringExtra("title"));
        content.setText(intentNews.getStringExtra("content"));



        date.setText("Date : "+intentNews.getStringExtra("date").substring(0, 10) + " " + intentNews.getStringExtra("time").substring(0, 5));
        vanue.setText("Vanue : "+intentNews.getStringExtra("vanue"));

        MaterialMenuView materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.ARROW);



        materialMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventFragment.this, Event.class);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                finish();
                startActivity(i, bndlanimation);
            }
        });

        iconLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EventFragment.this, EventMap.class);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                i.putExtra("lan", intentNews.getDoubleExtra("lan", 0.0));
                i.putExtra("lon", intentNews.getDoubleExtra("lon", 0.0));
                i.putExtra("vanue", intentNews.getStringExtra("vanue"));
                startActivity(i, bndlanimation);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
       // fab.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checkPermission()) {
                    requestPermission();

                } else {

                    Calander(Integer.parseInt(intentNews.getStringExtra("id")), intentNews.getStringExtra("title"), intentNews.getStringExtra("date"), intentNews.getStringExtra("time"));


                }


            }
        });




    }




    public void Calander(int id, String title, String calSdate, String calStime) {

        boolean result= false;

        Cursor cursor = this.getContentResolver()
                .query(
                        Uri.parse("content://com.android.calendar/events"),
                        new String[]{"calendar_id", "title"}, null,
                        null, null);

        while (cursor.moveToNext()) {

            if(cursor.getString(1).equals(title)){
                result=true;
            }else {
                result=false;
            }

        }



        if (result==true) {
            TastyToast.makeText(getApplicationContext(), "Event already in your Calendar !", TastyToast.LENGTH_LONG, TastyToast.WARNING);
        } else {
            final ContentValues event = new ContentValues();
            event.put(CalendarContract.Events.CALENDAR_ID, 1);
            event.put(CalendarContract.Events.TITLE, title);
            event.put(CalendarContract.Events.DESCRIPTION, title);




            int StartTimeHou = 0;
            int StatTimMin = 0;

            try {
                StartTimeHou = Integer.parseInt(calStime.substring(1, 3));

            } catch (NumberFormatException n) {
                StartTimeHou = 00;
            }

            try {
                StatTimMin = Integer.parseInt(calStime.substring(4, 6));

            } catch (NumberFormatException n) {
                StatTimMin = 00;
            }


            Calendar beginTime = Calendar.getInstance();

            beginTime.set(Integer.parseInt(calSdate.substring(0, 4)),Integer.parseInt(calSdate.substring(5, 7))-1,Integer.parseInt(calSdate.substring(8, 10)), StartTimeHou, StatTimMin);


            event.put(CalendarContract.Events.DTSTART, beginTime.getTimeInMillis());
            event.put(CalendarContract.Events.DTEND, beginTime.getTimeInMillis());
            event.put(CalendarContract.Events.ALL_DAY, 0);

            String timeZone = TimeZone.getDefault().getID();
            event.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone);
            event.put(CalendarContract.Events.HAS_ALARM, true);


            Uri baseUri;
            if (Build.VERSION.SDK_INT >= 8) {
                baseUri = Uri.parse("content://com.android.calendar/events");
            } else {
                baseUri = Uri.parse("content://calendar/events");
            }


            Uri uri = this.getContentResolver().insert(baseUri, event);
            long eventID = Long.parseLong(uri.getLastPathSegment());


            ContentValues reminders = new ContentValues();
            reminders.put(CalendarContract.Reminders.EVENT_ID, eventID);
            reminders.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            reminders.put(CalendarContract.Reminders.MINUTES, 10);

            try {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
                   return;
                }
                this.getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, reminders);
            } catch (SQLiteException d) {

            }
            TastyToast.makeText(getApplicationContext(), "Event already in your Calendar !", TastyToast.LENGTH_LONG, TastyToast.SUCCESS);

        }
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(EventFragment.this, Event.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        finish();
        startActivity(i, bndlanimation);
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_CALENDAR);
        return result == PackageManager.PERMISSION_GRANTED ;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CALENDAR}, MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_CALENDAR:
                if (grantResults.length > 0) {
                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (locationAccepted )
                        Calander(Integer.parseInt(intentNews.getStringExtra("id")), intentNews.getStringExtra("title"), intentNews.getStringExtra("date"), intentNews.getStringExtra("time"));
                    else {
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CALENDAR)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{Manifest.permission.WRITE_CALENDAR},MY_PERMISSIONS_REQUEST_WRITE_CALENDAR);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(EventFragment.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

}

