package tkhub.project.mscoba.Layout;

import android.annotation.TargetApi;
import android.app.Activity;
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
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tkhub.project.mscoba.MyClass.DB.EventDB;
import tkhub.project.mscoba.MyClass.List.EventAdapter;
import tkhub.project.mscoba.MyClass.List.Eventitem;
import tkhub.project.mscoba.MyClass.List.NavigationAdapter;
import tkhub.project.mscoba.MyClass.List.NavigationItem;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/22/2016.
 */
public class Event extends Activity {
    private MaterialMenuView materialMenuView;
    RecyclerView eventList;
    DrawerLayout dLayout;

    ArrayList<Eventitem> eventItems = new ArrayList<Eventitem>();
    EventAdapter eventAdapter;
    int a;
    RelativeLayout progress, layoutmain;
    WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;

    private Realm mRealm;
    private RealmConfiguration realmConfig;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_event);

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        eventList = (RecyclerView) findViewById(R.id.list_event);
        materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);

        progress = (RelativeLayout) findViewById(R.id.relativelayout_proress);
        layoutmain = (RelativeLayout) findViewById(R.id.relativelayout_main);
        progress.setVisibility(View.VISIBLE);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2);
        eventList.setLayoutManager(mLayoutManager);
        eventList.setItemAnimator(new DefaultItemAnimator());


        Realm.init(this);
        mRealm= Realm.getDefaultInstance();


        ArrayList<NavigationItem> mNavItems = new ArrayList<NavigationItem>();

        mNavItems.add(new NavigationItem("News", R.drawable.news));
        mNavItems.add(new NavigationItem("Event", R.drawable.event));
        mNavItems.add(new NavigationItem("Newsletter", R.drawable.newsletters));
        mNavItems.add(new NavigationItem("Calendar", R.drawable.calander));
        mNavItems.add(new NavigationItem("Gallery", R.drawable.gallery));
        mNavItems.add(new NavigationItem("Committee", R.drawable.member));
        mNavItems.add(new NavigationItem("Membership", R.drawable.membership));
        mNavItems.add(new NavigationItem("Share App", R.drawable.share));
        mNavItems.add(new NavigationItem("Profile", R.drawable.profile));
        mNavItems.add(new NavigationItem("Contact us", R.drawable.contact));
        mNavItems.add(new NavigationItem("About us", R.drawable.about));


        //-----------Set Navigation Item
        NavigationAdapter adapter = new NavigationAdapter(this, mNavItems);
        ListView navigationList = (ListView) findViewById(R.id.listView_navigation);

        navigationList.setAdapter(adapter);
        eventAdapter = new EventAdapter(this,eventItems);



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

        mWaveSwipeRefreshLayout = (WaveSwipeRefreshLayout) findViewById(R.id.main_swipe);
        mWaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                eventItems.clear();
                new getEvent().execute();
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
                    Intent i = new Intent(Event.this, News.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 2) {
                    Intent i = new Intent(Event.this, Newslatters.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 3) {
                    Intent i = new Intent(Event.this, EventCalendar.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 4) {
                    Intent i = new Intent(Event.this, Gallery.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 5) {
                    Intent i = new Intent(Event.this, Committee.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 6) {
                    Intent i = new Intent(Event.this, Membership.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 7) {
                    Intent i = new Intent(Event.this, Share.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 8) {
                    Intent i = new Intent(Event.this, Profile.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 9) {
                    Intent i = new Intent(Event.this, Contact.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 10) {
                    Intent i = new Intent(Event.this, About.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }


            }
        });


        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null) {
            showDialogNoInernet(this);
            getEventOffline();
        } else {
            new getEvent().execute();
        }

    }


    private class getEvent extends AsyncTask<Void, Void, Void> {
        JSONObject object;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.marisstellaoba.com/App/php/allevent.php").build();
                Response responses = null;

                responses = client.newCall(request).execute();

                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("allEvent");


                for (int i = 0; i < Jarray.length(); i++) {
                    object = Jarray.getJSONObject(i);
                    eventItems.add(new Eventitem(object.getString("eventid"), object.getString("eventname"), object.getString("eventcontent"),
                            object.getString("eventtumbimage"), object.getString("eventpublishdate"), object.getString("eventduedate"), object.getString("eventvenue"), object.getString("eventtime"), object.getDouble("eventlatitude"), object.getDouble("eventlongitude")));

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

            eventList.setAdapter(eventAdapter);
            mWaveSwipeRefreshLayout.setRefreshing(false);
            progress.setVisibility(View.INVISIBLE);
            layoutmain.setVisibility(View.VISIBLE);
        }

    }

    public void getEventOffline(){

        for (EventDB no : mRealm.where(EventDB.class).findAll()) {
            eventItems.add(new Eventitem(no.getEventid(), no.getEventname(),no.getEventcontent(),
                    no.getEventtumbimage(), no.getEventpublishdate(), no.getEventduedate(),no.getEventvenue(),
                    no.getEventtime(),no.getEventlatitude(),no.getEventlongitude()));

        }

        eventList.setAdapter(eventAdapter);
        mWaveSwipeRefreshLayout.setRefreshing(false);
        progress.setVisibility(View.INVISIBLE);
        layoutmain.setVisibility(View.VISIBLE);

    }




    public void lodeEvent(String id, String url, String title, String content, String date, String time, String vanue, double lan, double lon) {
        Intent i = new Intent(Event.this, EventFragment.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        i.putExtra("id", id);
        i.putExtra("url", url);
        i.putExtra("title", title);
        i.putExtra("content", content);
        i.putExtra("date", date);
        i.putExtra("time", time);
        i.putExtra("vanue", vanue);
        i.putExtra("lan", lan);
        i.putExtra("lon", lon);
        finish();
        startActivity(i, bndlanimation);
        
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
        Intent i = new Intent(Event.this, News.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        finish();
        startActivity(i, bndlanimation);
    }

}