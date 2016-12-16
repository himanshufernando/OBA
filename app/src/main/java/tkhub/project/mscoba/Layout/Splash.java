package tkhub.project.mscoba.Layout;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import org.json.JSONObject;

import io.fabric.sdk.android.Fabric;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 3/22/2016.
 */
public class Splash extends Activity {

    private static final String TWITTER_KEY = "dxg2FlZCD4w4cshL2EH1ZYCdE";
    private static final String TWITTER_SECRET = "joDcU0j8DXjrDqzuX9SafT923dUNi2FWI7vYGyWPag0FLCREpV ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
            Fabric.with(this, new Twitter(authConfig));
        }catch (NoClassDefFoundError ex){

        }catch (Exception ex){

        }

        setContentView(R.layout.layout_splash);
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    Intent i = new Intent(Splash.this, News.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);

                }
            }, 4000);



    }

}

