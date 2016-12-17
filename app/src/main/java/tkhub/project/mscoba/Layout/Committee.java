package tkhub.project.mscoba.Layout;

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
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tkhub.project.mscoba.MyClass.DB.CommitteeDB;
import tkhub.project.mscoba.MyClass.List.CommitteeAdapter;
import tkhub.project.mscoba.MyClass.List.NavigationAdapter;
import tkhub.project.mscoba.MyClass.List.NavigationItem;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/30/2016.
 */
public class Committee extends Activity {

    private static final String SHOWCASE_ID = "custom example";
    private MaterialMenuView materialMenuView;
    DrawerLayout dLayout;

    int a;
    ViewPager viewPager;
    ArrayList<String> comiiteName=new ArrayList<String>();
    ArrayList<String> comiitePost=new ArrayList<String>();
    ArrayList<String> comiiteImage=new ArrayList<String>();
    ArrayList<String> comiitequter=new ArrayList<String>();

    CommitteeAdapter commiteeAdapter;
    RelativeLayout progress,layoutcommittee;

    private Realm mRealm;
    private RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_committee);

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);

        progress=(RelativeLayout)findViewById(R.id.relativelayout_proress);
        layoutcommittee=(RelativeLayout)findViewById(R.id.relativeLayout26);
        progress.setVisibility(View.VISIBLE);

        viewPager = (ViewPager)findViewById(R.id.imageSlideShow);



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


        Realm.init(this);

        mRealm= Realm.getDefaultInstance();

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
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent i = new Intent(Committee.this, News.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 1) {
                    Intent i = new Intent(Committee.this, Event.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 2) {
                    Intent i = new Intent(Committee.this, Newslatters.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                   
                }
                if (position == 3) {
                    Intent i = new Intent(Committee.this, EventCalendar.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                   
                }
                if (position == 4) {
                    Intent i = new Intent(Committee.this, Gallery.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                   
                }
                if (position == 6) {
                    Intent i = new Intent(Committee.this, Membership.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                   
                }
                if (position == 7) {
                    Intent i = new Intent(Committee.this, Share.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                   
                }
                if (position == 8) {
                    Intent i = new Intent(Committee.this, Profile.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                   
                }
                if (position == 9) {
                    Intent i = new Intent(Committee.this, Contact.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                   
                }
                if (position == 10) {
                    Intent i = new Intent(Committee.this, About.class);
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
            getCommiteOffline();
        } else {
            new getCommite().execute();
        }
    }


    private class getCommite extends AsyncTask<Void, Void, Void> {
        JSONObject object;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.marisstellaoba.com/App/php/committee.php").build();
                Response responses = null;

                responses = client.newCall(request).execute();

                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("committee");
                for (int i = 0; i < Jarray.length(); i++) {
                    object = Jarray.getJSONObject(i);
                    comiiteName.add(object.getString("commiteename"));
                    comiiteImage.add(object.getString("commiteeimage"));
                    comiitePost.add(object.getString("commiteeposition"));
                    comiitequter.add(object.getString("commiteequote"));

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
            layoutcommittee.setVisibility(View.VISIBLE);
            progress.setVisibility(View.INVISIBLE);
            commiteeAdapter = new CommitteeAdapter(Committee.this,comiiteName,comiitePost,comiiteImage,comiitequter);
            viewPager.setAdapter(commiteeAdapter);

        }

    }

public void getCommiteOffline(){
    for (CommitteeDB no : mRealm.where(CommitteeDB.class).findAll()) {
        comiiteName.add(no.getCommiteename());
        comiiteImage.add(no.getCommiteeimage());
        comiitePost.add(no.getCommiteeposition());
        comiitequter.add(no.getCommiteequote());
    }
    layoutcommittee.setVisibility(View.VISIBLE);
    progress.setVisibility(View.INVISIBLE);
    commiteeAdapter = new CommitteeAdapter(Committee.this,comiiteName,comiitePost,comiiteImage,comiitequter);
    viewPager.setAdapter(commiteeAdapter);

}


    public void showDialogNoInernet(Context context){

        final Dialog dialogBox = new Dialog(context);

        dialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogBox.setContentView(R.layout.dialog_nointernet);
        dialogBox.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogBox.setCancelable(false);

        ImageView dialogClose=(ImageView)dialogBox.findViewById(R.id.imageView_dialog_internet_close);
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
        Intent i = new Intent(Committee.this, News.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        finish();
        startActivity(i, bndlanimation);
    }

}

