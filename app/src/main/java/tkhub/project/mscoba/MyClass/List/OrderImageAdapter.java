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

import tkhub.project.mscoba.Layout.Event;
import tkhub.project.mscoba.Layout.Gallery;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class OrderImageAdapter extends RecyclerView.Adapter<OrderImageAdapter.MyViewHolder> implements View.OnClickListener {

    Context mContext;
    ArrayList<Galleryitem> item;



    public OrderImageAdapter(Context mContext, ArrayList<Galleryitem> albumList) {
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


        try {
            Picasso.with(mContext).load(item.get(position).coverimage).into(holder.coverimage);
        }catch (Exception ex){

        }

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

        ImageView coverimage;
        TextView title;


        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text_gallery_title);
            coverimage =(ImageView)itemView.findViewById(R.id.image_gallery);


        }


        @Override
        public void onClick(View v) {

        }

    }

}
