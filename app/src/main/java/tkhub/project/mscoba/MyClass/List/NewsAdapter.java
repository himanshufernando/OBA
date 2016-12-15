package tkhub.project.mscoba.MyClass.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tkhub.project.mscoba.Layout.News;
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
        holder.textNew.setText("TODAY");

        Picasso.with(mContext).load(item.get(position).image).into(holder.imageCover);
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

      //  view.setOnClickListener(new OnItemClickListener(position));
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
        TextView textNew;
        RelativeLayout newNews;
        String content, url;
        RelativeLayout share;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageCover = (ImageView) itemView.findViewById(R.id.imageView_news);
            titel = (TextView) itemView.findViewById(R.id.textView_news_title);
            textNew = (TextView) itemView.findViewById(R.id.textView_new);
            newNews = (RelativeLayout) itemView.findViewById(R.id.relativelayout_new);
            share = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_share);
        }


        @Override
        public void onClick(View v) {

        }

    }

}
