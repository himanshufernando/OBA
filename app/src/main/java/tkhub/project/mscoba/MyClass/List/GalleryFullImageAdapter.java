package tkhub.project.mscoba.MyClass.List;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;

import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 4/20/2015.
 */
public class GalleryFullImageAdapter extends PagerAdapter {


    private Activity activity;
    private ArrayList<String> imagePaths;
    private LayoutInflater inflater;

    private ImageView imageViewGalleryFullImage;

    // constructor
    public GalleryFullImageAdapter(Activity activity, ArrayList<String> imagePaths) {
        this.activity = activity;
        this.imagePaths = imagePaths;

    }

    @Override
    public int getCount() {
        return this.imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view ==  object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.list_gallery_fullimages, container, false);
        String imageUrl = imagePaths.get(position);
        imageViewGalleryFullImage =(ImageView) viewLayout.findViewById(R.id.imageView_Gallery_FullImage);
        UrlImageViewHelper.setUrlDrawable(imageViewGalleryFullImage, imageUrl, R.drawable.imagebackground);
        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((View) object);

    }
}
