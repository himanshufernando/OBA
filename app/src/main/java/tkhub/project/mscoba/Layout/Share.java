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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.plus.PlusShare.Builder;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import tkhub.project.mscoba.MyClass.List.NavigationAdapter;
import tkhub.project.mscoba.MyClass.List.NavigationItem;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 2/1/2016.
 */
public class Share extends Activity {

    private MaterialMenuView materialMenuView;
    DrawerLayout dLayout;

    int a;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    ConnectivityManager conMgr;
    NetworkInfo netInfo;
    Button share;
    private static final String TWITTER_KEY = "dxg2FlZCD4w4cshL2EH1ZYCdE";
    private static final String TWITTER_SECRET = "joDcU0j8DXjrDqzuX9SafT923dUNi2FWI7vYGyWPag0FLCREpV ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_share);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

share=(Button)findViewById(R.id.buttonshare);
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);

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

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

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
                    Intent i = new Intent(Share.this, News.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(i, bndlanimation);
                    finish();
                }
                if (position == 1) {
                    Intent i = new Intent(Share.this, Event.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(i, bndlanimation);
                    finish();
                }
                if (position == 2) {
                    Intent i = new Intent(Share.this, Newslatters.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(i, bndlanimation);
                    finish();
                }
                if (position == 3) {
                    Intent i = new Intent(Share.this, EventCalendar.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(i, bndlanimation);
                    finish();
                }
                if (position == 4) {
                    Intent i = new Intent(Share.this, Gallery.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(i, bndlanimation);
                    finish();
                }
                if (position == 5) {
                    Intent i = new Intent(Share.this, Committee.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(i, bndlanimation);
                    finish();
                }
                if (position == 6) {
                    Intent i = new Intent(Share.this, Membership.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(i, bndlanimation);
                    finish();
                }
                if (position == 8) {
                    Intent i = new Intent(Share.this, Profile.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(i, bndlanimation);
                    finish();
                }
                if (position == 9) {
                    Intent i = new Intent(Share.this, Contact.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(i, bndlanimation);
                    finish();
                }
                if (position == 10) {
                    Intent i = new Intent(Share.this, About.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    startActivity(i, bndlanimation);
                    finish();
                }


            }
        });

        conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = conMgr.getActiveNetworkInfo();
        final Uri uri = Uri.parse("android.resource://oba.msc.himanshu.mscoba/drawable/logo");
        try {
            InputStream stream = getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

                if (netInfo == null) {
                    showDialogNoInernet(Share.this);

                } else {
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_SUBJECT, "MSC OBA Official App");
                    share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=tkhub.project.mscoba");
                    share.putExtra(Intent.EXTRA_TITLE, "Find MSC OBA Official app via Play store and Apple store. Our ultimate goal is create a bridge between you and us");
                    startActivity(Intent.createChooser(share, "Share"));
                }*/

                Toast.makeText(Share.this,"This features is block",Toast.LENGTH_SHORT).show();



            }
        });




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
            }
        });

        dialogBox.show();
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Share.this, News.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        finish();
        startActivity(i, bndlanimation);
    }

}

