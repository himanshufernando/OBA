package tkhub.project.mscoba.MyClass.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> implements View.OnClickListener {

    Context mContext;
    ArrayList<Galleryitem> item;

    public GalleryAdapter(Context mContext, ArrayList<Galleryitem> albumList) {
        this.mContext = mContext;
        this.item = albumList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cardview_gallery, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.imageLoader = CustomVolleyRequest.getInstance(mContext).getImageLoader();
        holder.imageLoader.get(item.get(position).coverimage, ImageLoader.getImageListener(holder.coverimage, R.drawable.imagebackground, android.R.drawable.ic_dialog_alert));
        holder.coverimage.setImageUrl(item.get(position).coverimage,  holder.imageLoader);
        holder.title.setText(item.get(position).title);
        holder.coverimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Gallery)mContext).lodeAlbum(item.get(position).id,item.get(position).coverimage);
            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public void onClick(View v){
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

       // ImageView coverimage;
        NetworkImageView coverimage;
        TextView title;
        private ImageLoader imageLoader;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text_gallery_title);
         //   coverimage =(ImageView)itemView.findViewById(R.id.image_gallery);
            coverimage =(NetworkImageView)itemView.findViewById(R.id.image_gallery);


        }


        @Override
        public void onClick(View v) {

        }

    }

}
