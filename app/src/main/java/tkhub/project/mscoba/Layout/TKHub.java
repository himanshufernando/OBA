package tkhub.project.mscoba.Layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.mikhaellopez.circularimageview.CircularImageView;

import tkhub.project.mscoba.R;

/**
 * Created by Himanshu on 12/17/2016.
 */

public class TKHub extends Activity implements Animation.AnimationListener {
    CircularImageView imagLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tkhub);

        imagLogo = (CircularImageView)findViewById(R.id.imageView_logo);

        final Animation animMovelaypot = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animationtk);
        animMovelaypot.setAnimationListener(this);
        imagLogo.startAnimation(animMovelaypot);
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
}
