package tkhub.project.mscoba.Layout;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 2/13/2016.
 */
public class EventMap extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    double lan,lon;
    String vanue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_event_map);

        final Intent intent = getIntent();
        lan=intent.getDoubleExtra("lan",0.0);
        lon=intent.getDoubleExtra("lon", 0.0);
        vanue=intent.getStringExtra("vanue");

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null) {
            showDialogNoInernet(this);
        }else {

        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(false);
        LatLng sydney = new LatLng(lan,lon);

        mMap.addMarker(new MarkerOptions().position(sydney).title(vanue));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(sydney)
                .zoom(15)
                .bearing(90)
                .tilt(45)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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

}
