package tkhub.project.mscoba.MyClass.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tkhub.project.mscoba.Layout.Gallery;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/28/2016.
 */
public class GalleryAdapter extends BaseAdapter implements View.OnClickListener {
        private Context mContext;

        ArrayList<Galleryitem> item;


    public GalleryAdapter(Context context, ArrayList<Galleryitem> AReworditem) {
        mContext = context;
        item = AReworditem;

    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int i) {
        return item.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.grid_item_gallery, null);


        } else {
            view = convertView;

        }

        ImageView coverimage =(ImageView)view.findViewById(R.id.image_gallery);
        TextView title=(TextView)view.findViewById(R.id.text_gallery_title);

        Picasso.with(mContext).load(item.get(position).coverimage).into(coverimage);

        title.setText(item.get(position).title);

        view.setOnClickListener(new OnItemClickListener(position));
        return view;
    }


    @Override
    public void onClick(View v) {

    }
    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {

            ((Gallery)mContext).lodeAlbum(item.get(mPosition).id,item.get(mPosition).coverimage);

        }

    }

}
