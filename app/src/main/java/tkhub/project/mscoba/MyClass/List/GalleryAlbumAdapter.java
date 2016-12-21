package tkhub.project.mscoba.MyClass.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tkhub.project.mscoba.Layout.Gallery;
import tkhub.project.mscoba.MyClass.Servies.CustomVolleyRequest;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class GalleryAlbumAdapter extends RecyclerView.Adapter<GalleryAlbumAdapter.MyViewHolder> implements View.OnClickListener {

    Context mContext;
    ArrayList<Albumitem> item;

    public GalleryAlbumAdapter(Context mContext, ArrayList<Albumitem> albumList) {
        this.mContext = mContext;
        this.item = albumList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_gallery_album, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.imageLoader = CustomVolleyRequest.getInstance(mContext).getImageLoader();
        holder.imageLoader.get(item.get(position).image, ImageLoader.getImageListener(holder.coverimage, R.drawable.imagebackground, android.R.drawable.ic_dialog_alert));
        holder.coverimage.setImageUrl(item.get(position).image, holder.imageLoader);
        holder.coverimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Gallery)mContext).lodeAlbumslider(item.get(position).id,item.get(position).image);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public void onClick(View v) {
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        NetworkImageView coverimage;
        private ImageLoader imageLoader;

        public MyViewHolder(View itemView) {
            super(itemView);
            coverimage = (NetworkImageView) itemView.findViewById(R.id.image_gallery);
        }


        @Override
        public void onClick(View v) {

        }

    }

}
