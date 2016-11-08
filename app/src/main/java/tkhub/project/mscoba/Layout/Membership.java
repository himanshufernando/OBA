package tkhub.project.mscoba.Layout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;

import net.steamcrafted.loadtoast.LoadToast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import tkhub.project.mscoba.MyClass.List.NavigationAdapter;
import tkhub.project.mscoba.MyClass.List.NavigationItem;
import tkhub.project.mscoba.MyClass.Servies.Downloader;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 2/1/2016.
 */
public class Membership extends Activity implements Animation.AnimationListener {
    private static final int MEGABYTE = 1024 * 1024;
    private MaterialMenuView materialMenuView;
    DrawerLayout dLayout;
    int a;
    private static final String SHOWCASE_ID = "Membership";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_membership);

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);
        RelativeLayout download = (RelativeLayout) findViewById(R.id.layout_download);
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
                    Intent i = new Intent(Membership.this, News.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 1) {
                    Intent i = new Intent(Membership.this, Event.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 2) {
                    Intent i = new Intent(Membership.this, Newslatters.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 3) {
                    Intent i = new Intent(Membership.this, EventCalendar.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 4) {
                    Intent i = new Intent(Membership.this, Gallery.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 5) {
                    Intent i = new Intent(Membership.this, Committee.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 7) {
                    Intent i = new Intent(Membership.this, Share.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 8) {
                    Intent i = new Intent(Membership.this, Profile.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 9) {
                    Intent i = new Intent(Membership.this, Contact.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
                if (position == 10) {
                    Intent i = new Intent(Membership.this, About.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }


            }
        });


        final RelativeLayout layoutone = (RelativeLayout) findViewById(R.id.relativeLayout_one);
        TextView texticonone = (TextView) findViewById(R.id.textView_one_icon);
        TextView textone = (TextView) findViewById(R.id.textView_one);

        final RelativeLayout layouttwo = (RelativeLayout) findViewById(R.id.relativeLayout_two);
        TextView texticontwo = (TextView) findViewById(R.id.textView_two_icon);
        TextView texttwo = (TextView) findViewById(R.id.textView_two);

        final RelativeLayout layoutthree = (RelativeLayout) findViewById(R.id.relativeLayout_three);
        TextView texticonthree = (TextView) findViewById(R.id.textView_three_icon);
        TextView textthree = (TextView) findViewById(R.id.textView_three);

        final RelativeLayout layoutfour = (RelativeLayout) findViewById(R.id.relativeLayout_four);
        TextView texticonfour = (TextView) findViewById(R.id.textView_four_icon);
        TextView textfour = (TextView) findViewById(R.id.textView_four);

        final RelativeLayout layoutfive = (RelativeLayout) findViewById(R.id.relativeLayout_five);
        TextView texticonfive = (TextView) findViewById(R.id.textView_five_icon);
        TextView textfive = (TextView) findViewById(R.id.textView_five);

        final RelativeLayout layoutsix = (RelativeLayout) findViewById(R.id.relativeLayout_six);
        TextView texticonsix = (TextView) findViewById(R.id.textView_six_icon);
        TextView textsix = (TextView) findViewById(R.id.textView_six);


        final Animation animMovelaypot = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move);
        final Animation animMoveicon = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_icon);
        final Animation animMovetext = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_text);

        final Animation animMovelaypot_2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_2);
        final Animation animMoveicon_2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_icon_2);
        final Animation animMovetext_2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_text_2);

        final Animation animMovelaypot_3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_3);
        final Animation animMoveicon_3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_icon_3);
        final Animation animMovetext_3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_text_3);

        final Animation animMovelaypot_4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_4);
        final Animation animMoveicon_4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_icon_4);
        final Animation animMovetext_4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_text_4);

        final Animation animMovelaypot_5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_5);
        final Animation animMoveicon_5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_icon_5);
        final Animation animMovetext_5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_text_5);

        final Animation animMovelaypot_6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_6);
        final Animation animMoveicon_6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_icon_6);
        final Animation animMovetext_6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_text_6);


        animMovelaypot.setAnimationListener(this);
        animMoveicon.setAnimationListener(this);
        animMovetext.setAnimationListener(this);

        animMovelaypot_2.setAnimationListener(this);
        animMoveicon_2.setAnimationListener(this);
        animMovetext_2.setAnimationListener(this);

        animMovelaypot_3.setAnimationListener(this);
        animMoveicon_3.setAnimationListener(this);
        animMovetext_3.setAnimationListener(this);

        animMovelaypot_4.setAnimationListener(this);
        animMoveicon_4.setAnimationListener(this);
        animMovetext_4.setAnimationListener(this);

        animMovelaypot_5.setAnimationListener(this);
        animMoveicon_5.setAnimationListener(this);
        animMovetext_5.setAnimationListener(this);

        animMovelaypot_6.setAnimationListener(this);
        animMoveicon_6.setAnimationListener(this);
        animMovetext_6.setAnimationListener(this);


        layoutone.setVisibility(View.VISIBLE);
        layouttwo.setVisibility(View.VISIBLE);
        layoutthree.setVisibility(View.VISIBLE);
        layoutfour.setVisibility(View.VISIBLE);
        layoutfive.setVisibility(View.VISIBLE);
        layoutsix.setVisibility(View.VISIBLE);


        layoutone.startAnimation(animMovelaypot);
        texticonone.startAnimation(animMoveicon);
        textone.startAnimation(animMovetext);

        layouttwo.startAnimation(animMovelaypot_2);
        texticontwo.startAnimation(animMoveicon_2);
        texttwo.startAnimation(animMovetext_2);

        layoutthree.startAnimation(animMovelaypot_3);
        texticonthree.startAnimation(animMoveicon_3);
        textthree.startAnimation(animMovetext_3);

        layoutfour.startAnimation(animMovelaypot_4);
        texticonfour.startAnimation(animMoveicon_4);
        textfour.startAnimation(animMovetext_4);

        layoutfive.startAnimation(animMovelaypot_5);
        texticonfive.startAnimation(animMoveicon_5);
        textfive.startAnimation(animMovetext_5);

        layoutsix.startAnimation(animMovelaypot_6);
        texticonsix.startAnimation(animMoveicon_6);
        textsix.startAnimation(animMovetext_6);


        new Handler().postDelayed(new Runnable() {
            public void run() {

                new MaterialStyledDialog.Builder(Membership.this)
                        .withDialogAnimation(true)
                        .setHeaderColor(R.color.colorPrimary)
                        .setTitle("How to Apply!")
                        .setDescription("Download and fill the application form with above conditions and handover to OBA Center or any OBA Committee Member.")
                        .setPositiveText("OK")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

            }
        }, 5400);


        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new getGalleyOffline().execute();
            }
        });


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

    private class getGalleyOffline extends AsyncTask<Void, Void, Void> {

        LoadToast lt = new LoadToast(Membership.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            lt.setText("Downloading ");
            lt.setTranslationY(400);
            lt.setTextColor(getResources().getColor(R.color.myWhite)).setBackgroundColor(getResources().getColor(R.color.myBlue)).setProgressColor(getResources().getColor(R.color.myWhite));
            lt.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "OBA_Aplication_Form.pdf");
            try {
                file.createNewFile();

            } catch (IOException e1) {
                e1.printStackTrace();
                lt.error();
            }
            Downloader.DownloadFile("http://www.himanshufernando.com/App/OBA/pdf/Christmas%20Carnival%20Letter.pdf", file);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            lt.success();
            new Handler().postDelayed(new Runnable() {
                public void run() {

                    new MaterialStyledDialog.Builder(Membership.this)
                            .withDialogAnimation(true)
                            .setHeaderColor(R.color.myBlue)
                            .setTitle("Download Complete!")
                            .setDescription("Find a file you have downloaded on Download Folder")
                            .setPositiveText("OK")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();








                    /*
                    MaterialStyledDialog dialog = new MaterialStyledDialog(Membership.this)
                            .setHeaderColor(R.color.myBlue)
                            .setTitle("Download Complete!")
                            .setDescription("Find a file you have downloaded on Download Folder")
                            .setPositive("Ok", new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(MaterialDialog dialog, DialogAction which) {
                                    dialog.dismiss();
                                }
                            })
                            .build();

                    dialog.show();*/
                }
            }, 1000);
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Membership.this, News.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        finish();
        startActivity(i, bndlanimation);
    }

}
