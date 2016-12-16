package tkhub.project.mscoba.MyClass.Servies;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Attract WW on 3/20/2015.
 */
public class NetworkReceiver extends BroadcastReceiver {



    ConnectivityManager conMgr;
    NetworkInfo netInfo;


    @Override
    public void onReceive(final Context context, Intent intent) {


        conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null) {

        } else {
            try {
                JsonServices js=new JsonServices(context);
                js.taskServices();
            }catch (Exception ex){

            }

        }

    }


}
