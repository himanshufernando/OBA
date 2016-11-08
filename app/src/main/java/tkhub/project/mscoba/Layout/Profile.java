package tkhub.project.mscoba.Layout;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
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
 * Created by Himanshu on 2/1/2016.
 */
public class Profile extends Activity implements Animation.AnimationListener  {

    private MaterialMenuView materialMenuView;
    DrawerLayout dLayout;
    int a;

TextView name,membershino,nic,address,landnumber,moblie,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_profile);

        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);

        name=(TextView)findViewById(R.id.textView_pro_name);
        membershino=(TextView)findViewById(R.id.textView_pro_id);
        nic=(TextView)findViewById(R.id.textView_pro_nic);
        address=(TextView)findViewById(R.id.textView_pro_address);
        landnumber=(TextView)findViewById(R.id.textView_pro_landphone);
        moblie=(TextView)findViewById(R.id.textView_pro_phone);
        email=(TextView)findViewById(R.id.textView_pro_email);


        name.setText("Name : "+"W.H.FERANANDO");
        membershino.setText("Mem No : "+"LM/F/628 - 1925");
        nic.setText("NIC No : "+"892631093V");
        address.setText("Address : "+"167/5 St.Niclas Rd,Negombo.");
        landnumber.setText("Phone No : "+"+94 312 233 748");
        moblie.setText("Mobile No : "+"+94 716 607 483");
        email.setText("Email : "+"himanshu.fernando@gmail.com");


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

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent i = new Intent(Profile.this, News.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 1) {
                    Intent i = new Intent(Profile.this, Event.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 2) {
                    Intent i = new Intent(Profile.this, Newslatters.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 3) {
                    Intent i = new Intent(Profile.this, EventCalendar.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 4) {
                    Intent i = new Intent(Profile.this, Gallery.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 5) {
                    Intent i = new Intent(Profile.this, Committee.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 6) {
                    Intent i = new Intent(Profile.this, Membership.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 7) {
                    Intent i = new Intent(Profile.this, Share.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }if (position == 9) {
                    Intent i = new Intent(Profile.this, Contact.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }if (position == 10) {
                    Intent i = new Intent(Profile.this, About.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }


            }
        });


        final RelativeLayout layoutlogin=(RelativeLayout)findViewById(R.id.layout_login);
        final RelativeLayout layoutdetails=(RelativeLayout)findViewById(R.id.layout_details);
        final TextView textlogout=(TextView)findViewById(R.id.textView_logout);
        Button login =(Button)findViewById(R.id.btnLogin);

        layoutlogin.setVisibility(View.VISIBLE);
        layoutdetails.setVisibility(View.GONE);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                layoutdetails.setVisibility(View.VISIBLE);
                layoutlogin.setVisibility(View.INVISIBLE);
                textlogout.setVisibility(View.VISIBLE);


            }
        });

        textlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                layoutdetails.setVisibility(View.INVISIBLE);
                layoutlogin.setVisibility(View.VISIBLE);
                textlogout.setVisibility(View.INVISIBLE);


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
    @Override
    public void onBackPressed() {
        Intent i = new Intent(Profile.this, News.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        finish();
        startActivity(i, bndlanimation);
    }
}
