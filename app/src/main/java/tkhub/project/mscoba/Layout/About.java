package tkhub.project.mscoba.Layout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;

import java.util.ArrayList;

import tkhub.project.mscoba.MyClass.List.NavigationAdapter;
import tkhub.project.mscoba.MyClass.List.NavigationItem;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 2/2/2016.
 */
public class About extends Activity {

    TextView mscIcon,mcsText,historyIcon,historyText,obaIcon,obaText,devIcon,devText;
    RelativeLayout layoutMsc,layoutHistory,layoutOba,layoutDev,layoutMainMsc,layoutMainHisttory,layoutMainOba;

    private MaterialMenuView materialMenuView;
    DrawerLayout dLayout;
    int a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_about);

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);

        layoutMsc=(RelativeLayout)findViewById(R.id.relativeLayout_MSC);
        layoutHistory=(RelativeLayout)findViewById(R.id.relativeLayout_feedback);
        layoutOba=(RelativeLayout)findViewById(R.id.relativeLayout_like);
        layoutDev=(RelativeLayout)findViewById(R.id.relativeLayout_developer);

        layoutMainMsc=(RelativeLayout)findViewById(R.id.relativeLayout_about_main_msc);
        layoutMainHisttory=(RelativeLayout)findViewById(R.id.realtivelayout_about_main_history);
        layoutMainOba=(RelativeLayout)findViewById(R.id.realtivelayout_about_main_oba);

        mscIcon=(TextView)findViewById(R.id.textView28);
        mcsText=(TextView)findViewById(R.id.textView29);
        historyIcon=(TextView)findViewById(R.id.textView30);
        historyText=(TextView)findViewById(R.id.textView31);
        obaIcon=(TextView)findViewById(R.id.textView32);
        obaText=(TextView)findViewById(R.id.textView33);

        ImageView image = (ImageView)findViewById(R.id.imageView6);

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

        mscIcon.setTextColor(getResources().getColor(R.color.myDarkBlu));
        mcsText.setTextColor(getResources().getColor(R.color.myDarkBlu));

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
                    Intent i = new Intent(About.this, News.class);
                    Bundle bndlanimation = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    }
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 1) {
                    Intent i = new Intent(About.this, Event.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 2) {
                    Intent i = new Intent(About.this, Newslatters.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 3) {
                    Intent i = new Intent(About.this, EventCalendar.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 4) {
                    Intent i = new Intent(About.this, Gallery.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 5) {
                    Intent i = new Intent(About.this, Committee.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 6) {
                    Intent i = new Intent(About.this, Membership.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 7) {
                    Intent i = new Intent(About.this, Share.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 8) {
                    Intent i = new Intent(About.this, Profile.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 9) {
                    Intent i = new Intent(About.this, Contact.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }


            }
        });

        layoutMainMsc.setVisibility(View.VISIBLE);
        layoutMainHisttory.setVisibility(View.INVISIBLE);
        layoutMainOba.setVisibility(View.INVISIBLE);

        layoutHistory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                historyIcon.setTextColor(getResources().getColor(R.color.myDarkBlu));
                historyText.setTextColor(getResources().getColor(R.color.myDarkBlu));
                obaIcon.setTextColor(getResources().getColor(R.color.myWhite));
                obaText.setTextColor(getResources().getColor(R.color.myWhite));
                mscIcon.setTextColor(getResources().getColor(R.color.myWhite));
                mcsText.setTextColor(getResources().getColor(R.color.myWhite));


                layoutMainMsc.setVisibility(View.INVISIBLE);
                layoutMainHisttory.setVisibility(View.VISIBLE);
                layoutMainOba.setVisibility(View.INVISIBLE);

            }
        });

        layoutMsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                historyIcon.setTextColor(getResources().getColor(R.color.myWhite));
                historyText.setTextColor(getResources().getColor(R.color.myWhite));

                obaIcon.setTextColor(getResources().getColor(R.color.myWhite));
                obaText.setTextColor(getResources().getColor(R.color.myWhite));

                mscIcon.setTextColor(getResources().getColor(R.color.myDarkBlu));
                mcsText.setTextColor(getResources().getColor(R.color.myDarkBlu));


                layoutMainMsc.setVisibility(View.VISIBLE);
                layoutMainHisttory.setVisibility(View.INVISIBLE);
                layoutMainOba.setVisibility(View.INVISIBLE);
            }
        });

        layoutOba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                historyIcon.setTextColor(getResources().getColor(R.color.myWhite));
                historyText.setTextColor(getResources().getColor(R.color.myWhite));

                mscIcon.setTextColor(getResources().getColor(R.color.myWhite));
                mcsText.setTextColor(getResources().getColor(R.color.myWhite));

                obaIcon.setTextColor(getResources().getColor(R.color.myDarkBlu));
                obaText.setTextColor(getResources().getColor(R.color.myDarkBlu));

                layoutMainOba.setVisibility(View.VISIBLE);
                layoutMainMsc.setVisibility(View.INVISIBLE);
                layoutMainHisttory.setVisibility(View.INVISIBLE);

            }
        });

        layoutDev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(About.this, TKHub.class);
                Bundle bndlanimation = null;
                bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                startActivity(i, bndlanimation);

            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(About.this, News.class);
        Bundle bndlanimation = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        }
        finish();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(i, bndlanimation);
        }
    }


}
