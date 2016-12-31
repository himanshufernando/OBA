package tkhub.project.mscoba.MyClass.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


import java.util.ArrayList;

import tkhub.project.mscoba.Layout.News;
import tkhub.project.mscoba.MyClass.Servies.CustomVolleyRequest;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> implements View.OnClickListener {

    Context mContext;
    ArrayList<Newsitem> item;

    boolean status = false;


    public NewsAdapter(Context mContext, ArrayList<Newsitem> albumList) {
        this.mContext = mContext;
        this.item = albumList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.titel.setText(item.get(position).titel);
        holder.imageLoader = CustomVolleyRequest.getInstance(mContext).getImageLoader();
        holder.imageLoader.get(item.get(position).image, ImageLoader.getImageListener(holder.imageCover, R.drawable.imagebackground, android.R.drawable.ic_dialog_alert));
        holder.imageCover.setImageUrl(item.get(position).image, holder.imageLoader);

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = true;
                ((News) mContext).lodeNews(item.get(position).iD, item.get(position).image, item.get(position).titel, item.get(position).content, item.get(position).publisDate, item.get(position).url, status);

            }
        });

        holder.imageCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = false;
                ((News) mContext).lodeNews(item.get(position).iD, item.get(position).image, item.get(position).titel, item.get(position).content, item.get(position).publisDate, item.get(position).url, status);

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

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView titel;
        RelativeLayout newNews;
        RelativeLayout share;
        private ImageLoader imageLoader;
        NetworkImageView imageCover;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageCover = (NetworkImageView) itemView.findViewById(R.id.imageView_news);
            titel = (TextView) itemView.findViewById(R.id.textView_news_title);
            share = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_share);
        }


        @Override
        public void onClick(View v) {

        }

    }

}
