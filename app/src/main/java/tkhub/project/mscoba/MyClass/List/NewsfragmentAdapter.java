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
import tkhub.project.mscoba.MyClass.Font.TextViewFontAwesome;
import tkhub.project.mscoba.MyClass.Servies.CustomVolleyRequest;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class NewsfragmentAdapter extends RecyclerView.Adapter<NewsfragmentAdapter.MyViewHolder> implements View.OnClickListener {

    Context mContext;
    ArrayList<Newsitem> item;

    boolean status = false;


    public NewsfragmentAdapter(Context mContext, ArrayList<Newsitem> albumList) {
        this.mContext = mContext;
        this.item = albumList;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news_fragment, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.imageLoader = CustomVolleyRequest.getInstance(mContext).getImageLoader();
        holder.imageLoader.get(item.get(position).image, ImageLoader.getImageListener(holder.imageCover, R.drawable.imagebackground, android.R.drawable.ic_dialog_alert));
        holder.imageCover.setImageUrl(item.get(position).image, holder.imageLoader);

        if(position==0){
            holder.textrightarrow.setVisibility(View.INVISIBLE);
        }else if(position==(item.size())-1){
            holder.textlefttarrow.setVisibility(View.INVISIBLE);
        }else {
            holder.textrightarrow.setVisibility(View.VISIBLE);
            holder.textlefttarrow.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    @Override
    public void onClick(View v) {
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageLoader imageLoader;
        NetworkImageView imageCover;
        TextViewFontAwesome textrightarrow,textlefttarrow;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageCover = (NetworkImageView) itemView.findViewById(R.id.imageView5);
            textrightarrow=(TextViewFontAwesome)itemView.findViewById(R.id.textView12);
            textlefttarrow=(TextViewFontAwesome)itemView.findViewById(R.id.textView15);

        }


        @Override
        public void onClick(View v) {

        }

    }

}
