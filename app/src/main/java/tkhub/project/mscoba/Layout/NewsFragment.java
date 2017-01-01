package tkhub.project.mscoba.Layout;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;

import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tkhub.project.mscoba.MyClass.List.Albumitem;
import tkhub.project.mscoba.MyClass.List.NewsfragmentAdapter;
import tkhub.project.mscoba.MyClass.List.Newsitem;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/25/2016.
 */
public class NewsFragment extends Activity {


    RecyclerView newsImageList;
    ArrayList<Newsitem> newsItems = new ArrayList<Newsitem>();
    NewsfragmentAdapter newsfragmentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_news);

      //  ImageView coverImage=(ImageView)findViewById(R.id.imageView_fragment_news);
        newsImageList = (RecyclerView) findViewById(R.id.list_news_fragment);
        TextView title =(TextView)findViewById(R.id.textView_fragment_news_title);
        TextView content =(TextView)findViewById(R.id.textView_fragment_news_content);
        TextView date =(TextView)findViewById(R.id.textView_fragment_news_publish);

        LinearLayoutManager verticalLayoutmanager = new LinearLayoutManager(NewsFragment.this, LinearLayoutManager.HORIZONTAL, false);
        newsImageList.setLayoutManager(verticalLayoutmanager);



        newsfragmentAdapter = new NewsfragmentAdapter(this, newsItems);
        Intent intentNews = getIntent();




        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null) {
        } else {
           new getNewsImages().execute(intentNews.getStringExtra("id"));
        }

        if(intentNews.getBooleanExtra("status", false) == true) {

            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            share.putExtra(Intent.EXTRA_SUBJECT, intentNews.getStringExtra("title"));
            share.putExtra(Intent.EXTRA_TEXT, intentNews.getStringExtra("url"));
            share.putExtra(Intent.EXTRA_TITLE, intentNews.getStringExtra("title"));
            startActivity(Intent.createChooser(share, "Share"));

        }



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

    private class getNewsImages extends AsyncTask<String, Void, Void> {
        JSONObject object;
        boolean res = false;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                RequestBody formBody = new FormBody.Builder().add("Id",params[0]).build();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.marisstellaoba.com/App/php/getnewsalbum_by_id.php").post(formBody).build();
                Response responses = null;
                responses = client.newCall(request).execute();

                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("newsimages");

                for (int i = 0; i < Jarray.length(); i++) {
                    object = Jarray.getJSONObject(i);
                   newsItems.add(new Newsitem(object.getString("newsimageid"), object.getString("newsimageurl")));
                }

                res = true;
            } catch (SocketTimeoutException sec){
                res = false;
                TastyToast.makeText(getApplicationContext(), "Server busy,please try again !", TastyToast.LENGTH_LONG, TastyToast.WARNING);
            } catch (IOException e) {
                e.printStackTrace();
                res = false;
            } catch (JSONException e) {
                e.printStackTrace();
                res = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            newsImageList.setAdapter(newsfragmentAdapter);
        }

    }

}

