package tkhub.project.mscoba.MyClass.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tkhub.project.mscoba.Layout.Newslatters;
import tkhub.project.mscoba.R;


/**
 * Created by attract on 4/30/15.
 */
public class NewsLetterAdapter extends BaseAdapter implements View.OnClickListener {
    Context mContext;
    ArrayList<NewsLettersitem> item;

    public NewsLetterAdapter(Context context, ArrayList<NewsLettersitem> AReworditem) {
        mContext = context;
        item = AReworditem;

    }


    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_news_latters, null);
        } else {
            view = convertView;
        }

        TextView newslatterName = (TextView) view.findViewById(R.id.TextView_NewsLetters_Title);

        newslatterName.setText(item.get(position).newsletterTitle);
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

            ((Newslatters) mContext).lodeNewslatters(item.get(mPosition).newsletterId, item.get(mPosition).newsletterpdf);

        }
    }
}
