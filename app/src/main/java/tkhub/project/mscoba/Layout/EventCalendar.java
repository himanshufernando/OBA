package tkhub.project.mscoba.Layout;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.github.pierry.simpletoast.SimpleToast;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tkhub.project.mscoba.MyClass.DB.CalendarDB;
import tkhub.project.mscoba.MyClass.List.NavigationAdapter;
import tkhub.project.mscoba.MyClass.List.NavigationItem;
import tkhub.project.mscoba.R;

/**
 * Created by Himanshu on 1/25/2016.
 */
public class EventCalendar extends FragmentActivity {

    private MaterialMenuView materialMenuView;
    DrawerLayout dLayout;

    int a;
    ArrayList<String> planeDate = new ArrayList<String>();
    ArrayList<String[]> deventDetail;

    private Realm mRealm;
    private RealmConfiguration realmConfig;
    TextView textViewcurrentDate;


    CompactCalendarView compactCalendarView;

    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());

    RelativeLayout progress,layoutCalander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_calendar);

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);
        textViewcurrentDate =(TextView)findViewById(R.id.textViewcurrentDate);

        compactCalendarView = (CompactCalendarView)findViewById(R.id.compactcalendar_view);
        layoutCalander =(RelativeLayout) findViewById(R.id.relativeLayoutCalander);
        progress = (RelativeLayout) findViewById(R.id.relativelayout_proress);

      //  realmConfig = new RealmConfiguration.Builder(this) .deleteRealmIfMigrationNeeded().build();
       // mRealm = Realm.getInstance(realmConfig);

        Realm.init(this);

        mRealm= Realm.getDefaultInstance();
        ArrayList<NavigationItem> mNavItems = new ArrayList<NavigationItem>();

        mNavItems.add(new NavigationItem("News", R.drawable.news));
        mNavItems.add(new NavigationItem("Event", R.drawable.event));
        mNavItems.add(new NavigationItem("Newsletter", R.drawable.newsletters));
        mNavItems.add(new NavigationItem("Calendar", R.drawable.calander));
        mNavItems.add(new NavigationItem("Gallery", R.drawable.gallery));
        mNavItems.add(new NavigationItem("Members", R.drawable.member));
        mNavItems.add(new NavigationItem("Membership", R.drawable.membership));
        mNavItems.add(new NavigationItem("Share App", R.drawable.share));
        mNavItems.add(new NavigationItem("Profile", R.drawable.profile));
        mNavItems.add(new NavigationItem("Contact us", R.drawable.contact));
        mNavItems.add(new NavigationItem("About us", R.drawable.about));



        //-----------Set Navigation Item
        NavigationAdapter adapter = new NavigationAdapter(this, mNavItems);
        ListView navigationList = (ListView) findViewById(R.id.listView_navigation);

        navigationList.setAdapter(adapter);

        textViewcurrentDate.setText(dateFormatForMonth.format(new Date()));



        a = 0;
        materialMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a == 0) {
                    dLayout.openDrawer(Gravity.LEFT);
                    materialMenuView.animateIconState(MaterialMenuDrawable.IconState.ARROW);
                    a = 1;
                } else {
                    dLayout.closeDrawer(Gravity.LEFT);
                    materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);
                    a = 0;
                }
            }
        });

        dLayout.setDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View arg0, float arg1) {
            }

            @Override
            public void onDrawerStateChanged(int arg0) {

            }

            @Override
            public void onDrawerOpened(View arg0) {
                materialMenuView.animateIconState(MaterialMenuDrawable.IconState.ARROW);
            }

            @Override
            public void onDrawerClosed(View arg0) {
                materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);
            }

        });

        navigationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent i = new Intent(EventCalendar.this, News.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 1) {
                    Intent i = new Intent(EventCalendar.this, Event.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 2) {
                    Intent i = new Intent(EventCalendar.this, Newslatters.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 4) {
                    Intent i = new Intent(EventCalendar.this, Gallery.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 5) {
                    Intent i = new Intent(EventCalendar.this, Committee.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 6) {
                    Intent i = new Intent(EventCalendar.this, Membership.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 7) {
                    Intent i = new Intent(EventCalendar.this, Share.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 8) {
                    Intent i = new Intent(EventCalendar.this, Profile.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 9) {
                    Intent i = new Intent(EventCalendar.this, Contact.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 10) {
                    Intent i = new Intent(EventCalendar.this, About.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }


            }
        });


       /* final Calendar nextYear = Calendar.getInstance();
        final Calendar lastYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);
        lastYear.add(Calendar.YEAR, -1);


        mycalander.init(lastYear.getTime(), nextYear.getTime()) //
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE) //
                .withSelectedDate(new Date()).displayOnly();*/

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null) {
            showDialogNoInernet(this);
            getCalenderOffline();
        } else {
            new getYearCalender().execute();
        }


        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

                final Dialog dialogBox = new Dialog(EventCalendar.this);

                dialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialogBox.setContentView(R.layout.dialog_calender);
                dialogBox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialogBox.setCancelable(true);

                ImageView dialogClose = (ImageView) dialogBox.findViewById(R.id.imageView_dialog_internet_close);
                TextView eventdateMonth=(TextView)dialogBox.findViewById(R.id.txt_dialo_calender_month);
                TextView eventdateDate=(TextView)dialogBox.findViewById(R.id.txt_dialo_calender_date);
                TextView eventdateYear=(TextView)dialogBox.findViewById(R.id.txt_dialo_calender_year);
                TextView eventName=(TextView)dialogBox.findViewById(R.id.textView44);
                TextView eventVenu=(TextView)dialogBox.findViewById(R.id.textView45);


                SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("dd-yyyy-MMMM", Locale.getDefault());
                SimpleDateFormat dateFormatForMonthInNumbers = new SimpleDateFormat("dd-yyyy-MM", Locale.getDefault());
                String selectDate=dateFormatForMonth.format(dateClicked);

                boolean abalbleStatus=false;

                for (String[] chaqeData :deventDetail) {

                    if((chaqeData[1].substring(0,4).equals(selectDate.substring(3,7)))&&(chaqeData[1].substring(5,7).equals(dateFormatForMonthInNumbers.format(dateClicked).substring(8,10)))&&(chaqeData[1].substring(8,10).equals(selectDate.substring(0,2)))){
                        abalbleStatus=true;
                        eventName.setText(chaqeData[0]);
                        eventVenu.setText(chaqeData[2]);
                    }else {

                    }


                }

                eventdateMonth.setText(selectDate.substring(8,selectDate.length()));
                eventdateDate.setText(selectDate.substring(0,2));
                eventdateYear.setText(selectDate.substring(3,7));

                if(abalbleStatus==true){
                    dialogBox.show();
                }else {
                    SimpleToast.info(EventCalendar.this,"No any event for this date");

                }


                dialogClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBox.dismiss();
                    }
                });


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                textViewcurrentDate.setText(dateFormatForMonth.format(firstDayOfNewMonth));
               // System.out.println("maonth  :"+dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });


    }




    private class getYearCalender extends AsyncTask<Void, Void, Void> {
        JSONObject object;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
            layoutCalander.setVisibility(View.INVISIBLE);
            deventDetail = new ArrayList<>();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://himanshufernando.com/App/OBA/php/allcalender.php").build();
                Response responses = null;

                responses = client.newCall(request).execute();

                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("allCalendar");
                for (int i = 0; i < Jarray.length(); i++) {
                    object = Jarray.getJSONObject(i);

                    String[] detail = new String[5];
                    detail[0]=object.getString("calendarevent");
                    detail[1]=object.getString("calendardate");
                    detail[2]=object.getString("calendarvanue");

                    deventDetail.add(detail);


                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
             progress.setVisibility(View.INVISIBLE);
             layoutCalander.setVisibility(View.VISIBLE);

            for (String[] chaqeData :deventDetail) {
                addEvents(compactCalendarView,Integer.parseInt(chaqeData[1].substring(5,7)),Integer.parseInt(chaqeData[1].substring(8,10)));
            }
        }

    }

    public void getCalenderOffline(){

        deventDetail = new ArrayList<>();
        progress.setVisibility(View.INVISIBLE);
        layoutCalander.setVisibility(View.VISIBLE);
        for (CalendarDB no : mRealm.where(CalendarDB.class).findAll()) {

            String[] detail = new String[5];
            detail[0]=no.getCalendarevent();
            detail[1]=no.getCalendardate();
            detail[2]=no.getCalendarvanue();

            deventDetail.add(detail);

            addEvents(compactCalendarView,Integer.parseInt(no.getCalendardate().substring(5,7)),Integer.parseInt(no.getCalendardate().substring(8,10)));

        }



    }


    public void showDialogNoInernet(Context context) {

        final Dialog dialogBox = new Dialog(context);

        dialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogBox.setContentView(R.layout.dialog_nointernet);
        dialogBox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBox.setCancelable(false);

        ImageView dialogClose = (ImageView) dialogBox.findViewById(R.id.imageView_dialog_internet_close);
        dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogBox.dismiss();
                Toast.makeText(getApplicationContext(), "You will miss the latest update", Toast.LENGTH_LONG).show();
            }
        });

        dialogBox.show();
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(EventCalendar.this, News.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        finish();
        startActivity(i, bndlanimation);
    }

    private void addEvents(CompactCalendarView compactCalendarView, int month, int date) {

        currentCalender.set(Calendar.MONTH, month-1);
        currentCalender.set(Calendar.DATE,date);


        long timeInMillis = currentCalender.getTimeInMillis();
        compactCalendarView.addEvent(new Event(Color.RED,timeInMillis));
        compactCalendarView.addEvent(new Event(Color.RED,timeInMillis));
        compactCalendarView.addEvent(new Event(Color.RED,timeInMillis));
        compactCalendarView.addEvent(new Event(Color.GREEN,timeInMillis));
        compactCalendarView.addEvent(new Event(Color.BLUE,timeInMillis));
        compactCalendarView.addEvent(new Event(Color.GREEN,timeInMillis));


    }


}