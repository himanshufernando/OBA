package tkhub.project.mscoba.MyClass.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tkhub.project.mscoba.R;


/**
 * Created by Himanshu on 1/28/2016.
 */
public class CalenderAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<Calenderitem> item;


    public CalenderAdapter(Context context, ArrayList<Calenderitem> AReworditem) {
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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_calander_event, null);


        } else {
            view = convertView;

        }

        TextView eventTitel = (TextView) view.findViewById(R.id.textView_calander_event_name);
        TextView eventDate = (TextView) view.findViewById(R.id.textView_calander_event_date);
        TextView eventvenue = (TextView) view.findViewById(R.id.textView_calander_event_vanue);


        eventTitel.setText(item.get(position).title);
        eventDate.setText(item.get(position).date.substring(8,10));
        eventvenue.setText(item.get(position).vanue+" "+item.get(position).date.substring(0,10));

        return view;
    }



}