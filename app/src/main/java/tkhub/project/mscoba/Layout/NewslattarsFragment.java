package tkhub.project.mscoba.Layout;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;

import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/25/2016.
 */
public class NewslattarsFragment extends Activity {

    TextView text;
    boolean loadingFinished = true;
    boolean redirect = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_newslatters);

        final Intent intentNews = getIntent();



        MaterialMenuView materialMenuView=(MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.ARROW);
text =(TextView)findViewById(R.id.textView2) ;

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null) {
            showDialogNoInernet(this);

        } else {


            WebView pdfwebvie=(WebView)findViewById(R.id.webView_NewsLetter_PD);
            pdfwebvie.getSettings().setJavaScriptEnabled(true);
            pdfwebvie.setWebViewClient(new WebViewClient() {


                @Override
                public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                    loadingFinished = false;
                    //SHOW LOADING IF IT ISNT ALREADY VISIBLE
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    if(!redirect){
                        loadingFinished = true;
                    }

                    if(loadingFinished && !redirect){
                        text.setVisibility(View.INVISIBLE);
                    } else{
                        redirect = false;
                    }

                }
            });

            pdfwebvie.loadUrl("http://docs.google.com/gview?embedded=true&url=" + intentNews.getStringExtra("url"));






          /*
            WebView pdfwebvie=(WebView)findViewById(R.id.webView_NewsLetter_PD);
            pdfwebvie.getSettings().setJavaScriptEnabled(true);

            pdfwebvie.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {

                    text.setVisibility(View.INVISIBLE);

                }
            });
            pdfwebvie.loadUrl("http://docs.google.com/gview?embedded=true&url=" + intentNews.getStringExtra("url"));*/
        }


        materialMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewslattarsFragment.this, Newslatters.class);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                finish();
                startActivity(i, bndlanimation);
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
        Intent i = new Intent(NewslattarsFragment.this, Newslatters.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        finish();
        startActivity(i, bndlanimation);
    }
}
