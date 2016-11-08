package tkhub.project.mscoba.MyClass.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/30/2016.
 */
public class AboutAdapter extends PagerAdapter {


    private Activity _activity;
    private ArrayList<String> description;
    private ArrayList<String> title;
    private ArrayList<Integer> image;

    private LayoutInflater inflater;

    public AboutAdapter(Activity _activity, ArrayList<String> description, ArrayList<Integer> image,ArrayList<String> tit) {
        this._activity = _activity;
        this.description = description;
        this.image = image;
        this.title = tit;
    }



    @Override
    public int getCount() {
        return this.description.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.list_about, container, false);


        TextView textdescription =(TextView)viewLayout.findViewById(R.id.textView_about);
        TextView textteitle =(TextView)viewLayout.findViewById(R.id.textView_about_title);
        ImageView imagecover=(ImageView) viewLayout.findViewById(R.id.imageView_aboutcover);

        textdescription.setText(description.get(position));
        textteitle.setText(title.get(position));
        imagecover.setImageResource(image.get(position));


        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);

    }
}