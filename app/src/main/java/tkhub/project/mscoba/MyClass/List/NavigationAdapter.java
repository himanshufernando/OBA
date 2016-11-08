package tkhub.project.mscoba.MyClass.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tkhub.project.mscoba.R;


/**
 * Created by attract on 3/13/15.
 */
public class NavigationAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<NavigationItem> mNavItems;

    public NavigationAdapter(Context context, ArrayList<NavigationItem> navItems) {
        mContext = context;
        mNavItems = navItems;
    }

    @Override
    public int getCount() {
        return mNavItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mNavItems.get(position);
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
            view = inflater.inflate(R.layout.list_navigation, null);
        } else {
            view = convertView;
        }

        TextView titleView = (TextView) view.findViewById(R.id.textView16);
        ImageView iconView = (ImageView) view.findViewById(R.id.imageView14);

        titleView.setText(mNavItems.get(position).mTitle);
        iconView.setImageResource(mNavItems.get(position).mIcon);

        return view;
    }
}
