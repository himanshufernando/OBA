package tkhub.project.mscoba.Layout;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.balysv.materialmenu.MaterialMenuDrawable;
import com.balysv.materialmenu.MaterialMenuView;

import com.sdsmdg.tastytoast.TastyToast;

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
import tkhub.project.mscoba.MyClass.List.AlbumAdapter;
import tkhub.project.mscoba.MyClass.List.Albumitem;
import tkhub.project.mscoba.MyClass.List.GalleryFullImageAdapter;
import tkhub.project.mscoba.MyClass.List.Galleryitem;
import tkhub.project.mscoba.MyClass.List.NavigationAdapter;
import tkhub.project.mscoba.MyClass.List.NavigationItem;
import tkhub.project.mscoba.MyClass.List.GalleryAdapter;
import tkhub.project.mscoba.R;

/**
 * Created by Himanshu on 1/28/2016.
 */
public class Gallery extends Activity {

    RelativeLayout progress, layoutmain, album;

    private MaterialMenuView materialMenuView;
    DrawerLayout dLayout;

    int a;


    AlbumAdapter albumAdapter;
    ArrayList<Albumitem> albumItem = new ArrayList<Albumitem>();


    GalleryAdapter galleryAdapter;
    ArrayList<Galleryitem> galleryItems = new ArrayList<Galleryitem>();

    GridView  grodViewAlbum;
    ArrayList<String> groupImages;
    GalleryFullImageAdapter galleryFullImageAdapter;

    RecyclerView recyclerView_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_gallery);


        recyclerView_main=(RecyclerView)findViewById(R.id.list_image);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,2);

        recyclerView_main.setLayoutManager(mLayoutManager);


        grodViewAlbum = (GridView) findViewById(R.id.gridView_album);

        galleryAdapter = new GalleryAdapter(this,galleryItems);


        albumAdapter = new AlbumAdapter(this, albumItem);

        groupImages = new ArrayList<String>();

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


        materialMenuView = (MaterialMenuView) findViewById(R.id.action_bar_menu);
        materialMenuView.animateIconState(MaterialMenuDrawable.IconState.BURGER);
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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
                    Intent i = new Intent(Gallery.this, News.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 1) {
                    Intent i = new Intent(Gallery.this, Event.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 2) {
                    Intent i = new Intent(Gallery.this, Newslatters.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 3) {
                    Intent i = new Intent(Gallery.this, EventCalendar.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 5) {
                    Intent i = new Intent(Gallery.this, Committee.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 6) {
                    Intent i = new Intent(Gallery.this, Membership.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 7) {
                    Intent i = new Intent(Gallery.this, Share.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 8) {
                    Intent i = new Intent(Gallery.this, Profile.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 9) {
                    Intent i = new Intent(Gallery.this, Contact.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }
                if (position == 10) {
                    Intent i = new Intent(Gallery.this, About.class);
                    Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
                    finish();
                    startActivity(i, bndlanimation);
                    
                }


            }
        });

        progress = (RelativeLayout) findViewById(R.id.relativelayout_proress);
        layoutmain = (RelativeLayout) findViewById(R.id.relativeLayoutmain);
        album = (RelativeLayout) findViewById(R.id.relativeLayoutalbum);

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null) {
            showDialogNoInernet(this);
            progress.setVisibility(View.INVISIBLE);
        } else {
            new getGalley().execute();
        }


    }

    public void lodeAlbum(String id, String coverurl) {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null) {
            showDialogNoInernet(this);
        } else {
            new getGalleyAlbum().execute(id,coverurl);
        }


    }

    public void lodeAlbumslider(String id, String imageUrl) {

        ConnectivityManager conMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null) {
            showDialogNoInernet(this);
        } else {

            groupImages.add(0, imageUrl);
            final Dialog dialogGallery = new Dialog(Gallery.this);
            dialogGallery.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialogGallery.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
            dialogGallery.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialogGallery.setContentView(R.layout.dialog_gallery);
            ViewPager viewPager = (ViewPager) dialogGallery.findViewById(R.id.imageSlideShow);
            galleryFullImageAdapter = new GalleryFullImageAdapter(Gallery.this, groupImages);
            viewPager.setAdapter(galleryFullImageAdapter);
            dialogGallery.setCancelable(true);
            dialogGallery.show();
        }


    }


    private class getGalleyAlbum extends AsyncTask<String, Void, Void> {
        JSONObject object;
        boolean res = false;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                RequestBody formBody = new FormBody.Builder().add("Id",params[0]).build();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.marisstellaoba.com/App/php/getalbum_by_id.php").post(formBody).build();
                Response responses = null;

                responses = client.newCall(request).execute();

                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("galleryalbum");
                groupImages.clear();
                groupImages.add(0, params[1]);
                albumItem.clear();

                for (int i = 0; i < Jarray.length(); i++) {
                    object = Jarray.getJSONObject(i);
                    albumItem.add(new Albumitem(object.getString("galleryid"), object.getString("galleryalbumimage")));
                    groupImages.add(i + 1, object.getString("galleryalbumimage"));
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

            if (res == true) {
                grodViewAlbum.setAdapter(albumAdapter);
                album.setVisibility(View.VISIBLE);
                progress.setVisibility(View.INVISIBLE);
                layoutmain.setVisibility(View.INVISIBLE);
                TastyToast.makeText(getApplicationContext(), "Images Loading !", TastyToast.LENGTH_LONG, TastyToast.INFO);
            } else {
                TastyToast.makeText(getApplicationContext(), "No image in the sever !", TastyToast.LENGTH_LONG, TastyToast.WARNING);
            }

        }

    }

    private class getGalley extends AsyncTask<Void, Void, Void> {
        JSONObject object;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.marisstellaoba.com/App/php/gallery.php").build();
                Response responses = null;

                responses = client.newCall(request).execute();

                String jsonData = responses.body().string();
                JSONObject Jobject = new JSONObject(jsonData);
                JSONArray Jarray = Jobject.getJSONArray("allgallery");

                for (int i = 0; i < Jarray.length(); i++) {
                    object = Jarray.getJSONObject(i);
                    galleryItems.add(new Galleryitem(object.getString("galleryid"), object.getString("gallerycoverimage"), object.getString("gallerytitle")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            recyclerView_main.setAdapter(galleryAdapter);
            progress.setVisibility(View.INVISIBLE);
            layoutmain.setVisibility(View.VISIBLE);
            album.setVisibility(View.INVISIBLE);
            TastyToast.makeText(getApplicationContext(), "Images Loading !", TastyToast.LENGTH_LONG, TastyToast.INFO);

        }

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
        if (layoutmain.getVisibility() == View.VISIBLE) {
            Intent i = new Intent(Gallery.this, News.class);
            Bundle bndlanimation = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.animation, R.anim.animation2).toBundle();
            finish();
            startActivity(i, bndlanimation);
        } else {
            album.setVisibility(View.INVISIBLE);
            progress.setVisibility(View.INVISIBLE);
            layoutmain.setVisibility(View.VISIBLE);
        }

    }
}