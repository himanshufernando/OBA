package tkhub.project.mscoba.Layout;

import android.annotation.TargetApi;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import tkhub.project.mscoba.MyClass.List.NavigationAdapter;
import tkhub.project.mscoba.MyClass.List.NavigationItem;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 2/2/2016.
 */
public class Contact extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private MaterialMenuView materialMenuView;
    RelativeLayout layoutMap,layoutBackground,layoutPhone,layoutAddress,layoutWeb;
    TextView textPhone,textMobile,textAddresOne,textAddresTwo,textAddresThree,textAddresFour,textWeb,textEmail,iconPhone,iconAddres,iconWeb;

    DrawerLayout dLayout;
    int a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contact);


        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);
       // materialMenuView.animatePressedState(MaterialMenuDrawable.IconState.BURGER);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);

        layoutMap = (RelativeLayout) findViewById(R.id.relativeLayout_map);

        layoutPhone = (RelativeLayout) findViewById(R.id.relativeLayoutPhone);
        layoutAddress = (RelativeLayout) findViewById(R.id.relativeLayoutAddress);
        layoutWeb = (RelativeLayout) findViewById(R.id.relativeLayoutWeb);

        iconPhone =(TextView)findViewById(R.id.textView41);
        iconAddres =(TextView)findViewById(R.id.textView42);
        iconWeb =(TextView)findViewById(R.id.textView43);

        textPhone =(TextView)findViewById(R.id.textView_phone);
        textMobile =(TextView)findViewById(R.id.textView_phone_two);
        textAddresOne =(TextView)findViewById(R.id.textView_address_one);
        textAddresTwo =(TextView)findViewById(R.id.textView_address_two);
        textAddresThree =(TextView)findViewById(R.id.textView_address_three);
        textAddresFour =(TextView)findViewById(R.id.textView_address_four);
        textWeb =(TextView)findViewById(R.id.textView_web);
        textEmail =(TextView)findViewById(R.id.textView_email);

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
                 //   materialMenuView.animatePressedState(MaterialMenuDrawable.IconState.ARROW);
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
                    Intent i = new Intent(Contact.this, News.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 1) {
                    Intent i = new Intent(Contact.this, Event.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                
                
                }
                if (position == 2) {
                    Intent i = new Intent(Contact.this, Newslatters.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                
                }
                if (position == 3) {
                    Intent i = new Intent(Contact.this, EventCalendar.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                
                }
                if (position == 4) {
                    Intent i = new Intent(Contact.this, Gallery.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                
                }
                if (position == 5) {
                    Intent i = new Intent(Contact.this, Committee.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                
                }
                if (position == 6) {
                    Intent i = new Intent(Contact.this, Membership.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                
                }
                if (position == 7) {
                    Intent i = new Intent(Contact.this, Share.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                
                }
                if (position == 8) {
                    Intent i = new Intent(Contact.this, Profile.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                
                }
                if (position == 10) {
                    Intent i = new Intent(Contact.this, About.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                
                }


            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null) {
            layoutMap.setVisibility(View.INVISIBLE);
        }else {
            layoutMap.setVisibility(View.VISIBLE);
        }



        //On
        layoutPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iconPhone.setTextColor(getResources().getColor(R.color.colorPrimary));
                iconAddres.setTextColor(getResources().getColor(R.color.myBlack));
                iconWeb.setTextColor(getResources().getColor(R.color.myBlack));

                textPhone.setVisibility(View.VISIBLE);
                textMobile.setVisibility(View.VISIBLE);
                textAddresOne.setVisibility(View.INVISIBLE);
                textAddresTwo.setVisibility(View.INVISIBLE);
                textAddresThree.setVisibility(View.INVISIBLE);
                textAddresFour.setVisibility(View.INVISIBLE);
                textWeb.setVisibility(View.INVISIBLE);
                textEmail.setVisibility(View.INVISIBLE);
            }
        });

        layoutAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iconPhone.setTextColor(getResources().getColor(R.color.myBlack));
                iconAddres.setTextColor(getResources().getColor(R.color.colorPrimary));
                iconWeb.setTextColor(getResources().getColor(R.color.myBlack));

                textPhone.setVisibility(View.INVISIBLE);
                textMobile.setVisibility(View.INVISIBLE);
                textAddresOne.setVisibility(View.VISIBLE);
                textAddresTwo.setVisibility(View.VISIBLE);
                textAddresThree.setVisibility(View.VISIBLE);
                textAddresFour.setVisibility(View.VISIBLE);
                textWeb.setVisibility(View.INVISIBLE);
                textEmail.setVisibility(View.INVISIBLE);
            }
        });

        layoutWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                iconPhone.setTextColor(getResources().getColor(R.color.myBlack));
                iconAddres.setTextColor(getResources().getColor(R.color.myBlack));
                iconWeb.setTextColor(getResources().getColor(R.color.colorPrimary));

                textPhone.setVisibility(View.INVISIBLE);
                textMobile.setVisibility(View.INVISIBLE);
                textAddresOne.setVisibility(View.INVISIBLE);
                textAddresTwo.setVisibility(View.INVISIBLE);
                textAddresThree.setVisibility(View.INVISIBLE);
                textAddresFour.setVisibility(View.INVISIBLE);
                textWeb.setVisibility(View.VISIBLE);
                textEmail.setVisibility(View.VISIBLE);
            }
        });

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(false);
        LatLng sydney = new LatLng(7.213564, 79.849012);//la.lo

        mMap.addMarker(new MarkerOptions().position(sydney).title("Maris Stella College"));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(sydney)
                .zoom(15)
                .bearing(90)
                .tilt(45)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Contact.this, News.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        finish();
        startActivity(i, bndlanimation);
    }
}

