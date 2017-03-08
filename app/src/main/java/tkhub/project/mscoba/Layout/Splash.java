package tkhub.project.mscoba.Layout;


import android.app.Activity;
import android.app.ActivityOptions;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;


import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;



import io.fabric.sdk.android.Fabric;

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
                    startActivity(i);

                }
            }, 3500);



    }

}

