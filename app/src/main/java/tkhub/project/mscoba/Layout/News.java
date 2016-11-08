package tkhub.project.mscoba.Layout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tkhub.project.mscoba.MyClass.DB.NewsDB;
import tkhub.project.mscoba.MyClass.List.NavigationAdapter;
import tkhub.project.mscoba.MyClass.List.NavigationItem;
import tkhub.project.mscoba.MyClass.List.NewsAdapter;
import tkhub.project.mscoba.MyClass.List.Newsitem;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/1/2016.
 */
public class News extends Activity implements Animation.AnimationListener {

    private MaterialMenuView materialMenuView;
    RecyclerView newsList;
    DrawerLayout dLayout;

    private Realm mRealm;
    private RealmConfiguration realmConfig;

    ArrayList<Newsitem> newsItems = new ArrayList<Newsitem>();
    NewsAdapter newsAdapter;
    int a;

    RelativeLayout progress;
    int shareiconstarus = 0;


    WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_news);

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        newsList = (RecyclerView) findViewById(R.id.list_news);
        materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        newsList.setLayoutManager(mLayoutManager);
        newsList.setItemAnimator(new DefaultItemAnimator());


        progress = (RelativeLayout) findViewById(R.id.relativelayout_proress);
        progress.setVisibility(View.VISIBLE);

      //  realmConfig = new RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build();
      //  mRealm = Realm.getInstance(realmConfig);

        Realm.init(this);

        mRealm= Realm.getDefaultInstance();

        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
        nMgr.cancel(00125);

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
        newsAdapter = new NewsAdapter(this, newsItems);


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
                newsItems.clear();
                new getNews().execute();
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
                if (position == 1) {
                    Intent i = new Intent(News.this, Event.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 2) {
                    Intent i = new Intent(News.this, Newslatters.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 3) {
                    Intent i = new Intent(News.this, EventCalendar.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 4) {
                    Intent i = new Intent(News.this, Gallery.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 5) {
                    Intent i = new Intent(News.this, Committee.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 6) {
                    Intent i = new Intent(News.this, Membership.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 7) {
                    Intent i = new Intent(News.this, Share.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 8) {
                    Intent i = new Intent(News.this, Profile.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 9) {
                    Intent i = new Intent(News.this, Contact.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 10) {
                    Intent i = new Intent(News.this, About.class);
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
            offlinenews();
        } else {
            new getNews().execute();

        }


        //  AdView adView = (AdView) findViewById(R.id.adView);
        //  AdRequest adRequest = new AdRequest.Builder().build();
        //  adView.loadAd(adRequest);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private class getNews extends AsyncTask<Void, Void, Void> {
        JSONObject object;
        JSONObject Jobject;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                OkHttpClient client = new OkHttpClient();


                Request request = new Request.Builder().url("http://www.himanshufernando.com/App/OBA/php/allnews.php").build();
                Response responses = null;

                responses = client.newCall(request).execute();

                String jsonData = responses.body().string();
                Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("allNews");


                for (int i = 0; i < Jarray.length(); i++) {
                    object = Jarray.getJSONObject(i);
                    newsItems.add(new Newsitem(object.getString("newsid"), object.getString("newsimage"), object.getString("newstitle"),
                            object.getString("newscontent"), object.getString("newspublishDate"), object.getString("newsurl")));

                }
            } catch (SocketTimeoutException e) {

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

            newsList.setAdapter(newsAdapter);
            mWaveSwipeRefreshLayout.setRefreshing(false);
            progress.setVisibility(View.INVISIBLE);

        }

    }

    public void offlinenews() {

        for (NewsDB no : mRealm.where(NewsDB.class).findAll()) {
            newsItems.add(new Newsitem(no.getNewsid(), no.getNewsimage(), no.getNewstitle(), no.getNewscontent(), no.getNewspublishDate(), no.getNewsurl()));

        }
        newsList.setAdapter(newsAdapter);
        mWaveSwipeRefreshLayout.setRefreshing(false);
        progress.setVisibility(View.INVISIBLE);
    }


    public void lodeNews(String id, String imageurl, String title, String content, String date, String url, boolean status) {

        if (shareiconstarus == 0) {
            Intent i = new Intent(News.this, NewsFragment.class);
            Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
            i.putExtra("iamgeurl", imageurl);
            i.putExtra("title", title);
            i.putExtra("content", content);
            i.putExtra("date", date);
            i.putExtra("status", status);
            i.putExtra("url", url);
            finish();
            startActivity(i, bndlanimation);

        } else {

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


        new MaterialStyledDialog.Builder(this)
                .withDialogAnimation(true)
                .setHeaderColor(R.color.myBlue)
                .setTitle("Exit!")
                .setDescription("Do you really want to exit ?")
                .setPositiveText("Yes")
                .setNegativeText("No")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        finish();
                        System.exit(0);
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        return;
                    }
                })
                .show();


    }

}
