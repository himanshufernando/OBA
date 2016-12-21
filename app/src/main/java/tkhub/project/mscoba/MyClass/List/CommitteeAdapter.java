package tkhub.project.mscoba.MyClass.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/30/2016.
 */
public class CommitteeAdapter extends PagerAdapter {

    private static final String SHOWCASE_ID = "custom example";
    private Activity _activity;
    private ArrayList<String> names;
    private ArrayList<String> comiitePost;
    private ArrayList<String> comiiteImage;
    private ArrayList<String> comiitequter;
    private LayoutInflater inflater;

    public CommitteeAdapter(Activity _activity, ArrayList<String> names, ArrayList<String> comiitePost, ArrayList<String> comiiteImage, ArrayList<String> comiitequter) {
        this._activity = _activity;
        this.names = names;
        this.comiitePost = comiitePost;
        this.comiiteImage = comiiteImage;
        this.comiitequter = comiitequter;
    }



    @Override
    public int getCount() {
        return this.names.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.list_committee, container, false);


        TextView name =(TextView)viewLayout.findViewById(R.id.textView_comme_name);
        TextView post =(TextView)viewLayout.findViewById(R.id.textView_comme_post);
        TextView quter =(TextView)viewLayout.findViewById(R.id.textView_comm_quit);
        CircularImageView image=(CircularImageView) viewLayout.findViewById(R.id.imageView_comm_profile);




        name.setText(names.get(position));
        post.setText(comiitePost.get(position));
        String ImageUrl = comiiteImage.get(position);

        quter.setText("\""+""+comiitequter.get(position)+""+"\"");

        try {
            Picasso.with(_activity).load(ImageUrl).into(image);
        }catch (Exception ex){

        }


        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);

    }
}