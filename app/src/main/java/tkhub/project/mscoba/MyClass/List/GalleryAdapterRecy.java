package tkhub.project.mscoba.MyClass.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tkhub.project.mscoba.Layout.News;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class GalleryAdapterRecy extends RecyclerView.Adapter<GalleryAdapterRecy.MyViewHolder> implements View.OnClickListener {

    Context mContext;
    ArrayList<Galleryitem> item;

    boolean status = false;


    public GalleryAdapterRecy(Context mContext, ArrayList<Galleryitem> albumList) {
        this.mContext = mContext;
        this.item = albumList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_gallery, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.titel.setText(item.get(position).title);

        try {
            Picasso.with(mContext).load(item.get(position).coverimage).into(holder.imageCover);
        }catch (Exception ex){

        }

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public void onClick(View v) {
        System.out.println("sssdsdsdsdsdsdsdsd");
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageCover;
        TextView titel;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageCover = (ImageView) itemView.findViewById(R.id.image_gallery);
            titel = (TextView) itemView.findViewById(R.id.text_gallery_title);

        }


        @Override
        public void onClick(View v) {

        }

    }

}
