package tkhub.project.mscoba.Layout;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/25/2016.
 */
public class NewsFragment extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);

        ImageView coverImage=(ImageView)findViewById(R.id.imageView_fragment_news);
        TextView title =(TextView)findViewById(R.id.textView_fragment_news_title);
        TextView content =(TextView)findViewById(R.id.textView_fragment_news_content);
        TextView date =(TextView)findViewById(R.id.textView_fragment_news_publish);

        Intent intentNews = getIntent();


        if(intentNews.getBooleanExtra("status", false) == true) {

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            share.putExtra(Intent.EXTRA_SUBJECT, intentNews.getStringExtra("title"));
            share.putExtra(Intent.EXTRA_TEXT, intentNews.getStringExtra("url"));
            share.putExtra(Intent.EXTRA_TITLE, intentNews.getStringExtra("title"));
            startActivity(Intent.createChooser(share, "Share"));

        }

        UrlImageViewHelper.setUrlDrawable(coverImage, intentNews.getStringExtra("iamgeurl"), R.drawable.imagebackground);
        title.setText(intentNews.getStringExtra("title"));
        content.setText(intentNews.getStringExtra("content"));

        String monthdate,month = null,year;
        monthdate =intentNews.getStringExtra("date").substring(8,10);
        year=intentNews.getStringExtra("date").substring(0, 4);


        switch (intentNews.getStringExtra("date").substring(5,7)) {
            case "01":
                month="January";
                break;
            case "02":
                month="February";
                break;
            case "03":
                month="March";
                break;
            case "04":
                month="April";
                break;
            case "05":
                month="May";
                break;
            case "06":
                month="June";
                break;
            case "07":
                month="July";
                break;
            case "08":
                month="August";
                break;
            case "09":
                month="September";
                break;
            case "10":
                month="October";
                break;
            case "11":
                month="November";
                break;
            case "12":
                month="December";
                break;
            default:

                break;
        }


        date.setText(monthdate+" of "+month+" "+year);




        MaterialMenuView materialMenuView=(MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.ARROW);


        materialMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewsFragment.this, News.class);
                Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                finish();
                startActivity(i, bndlanimation);

            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(NewsFragment.this, News.class);
        Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
        finish();
        startActivity(i, bndlanimation);
    }
    private static String getMonth(String date) throws ParseException {
        Date d = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH).parse(date);
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        String monthName = new SimpleDateFormat("MMMM").format(cal.getTime());
        return monthName;
    }
}

