package tkhub.project.mscoba.MyClass.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import tkhub.project.mscoba.Layout.Event;
import tkhub.project.mscoba.MyClass.Servies.CustomVolleyRequest;
import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> implements View.OnClickListener {

    Context mContext;
    ArrayList<Eventitem> item;


    public EventAdapter(Context mContext, ArrayList<Eventitem> albumList) {
        this.mContext = mContext;
        this.item = albumList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_event, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.imageUrl=item.get(position).eventTumbimage;
        holder.eventTitel.setText(item.get(position).eventTitle);


        holder.imageLoader = CustomVolleyRequest.getInstance(mContext).getImageLoader();
        holder.imageLoader.get(item.get(position).eventTumbimage, ImageLoader.getImageListener(holder.eventtumbimage, R.drawable.imagebackground, android.R.drawable.ic_dialog_alert));
        holder.eventtumbimage.setImageUrl(item.get(position).eventTumbimage, holder.imageLoader);

        

        holder.eventtumbimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Event) mContext).lodeEvent(item.get(position).eventID, item.get(position).eventTumbimage, item.get(position).eventTitle, item.get(position).eventContent, item.get(position).eventDuedate, item.get(position).eventTime, item.get(position).eventVenue, item.get(position).eventLan, item.get(position).eventLon);


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

        TextView eventTitel;
        String imageUrl;
        private ImageLoader imageLoader;
        NetworkImageView eventtumbimage;


        public MyViewHolder(View itemView) {
            super(itemView);
            eventTitel = (TextView) itemView.findViewById(R.id.textView_event_titel);
            eventtumbimage =(NetworkImageView)itemView.findViewById(R.id.imageView_event);

        }

        @Override
        public void onClick(View v) {
        }

    }

}
