package tkhub.project.mscoba;


import android.os.AsyncTask;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by Himanshu on 10/8/2016.
 */

public class FCMInsnaceIdSeervies extends FirebaseInstanceIdService {
    String resToken;
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        resToken = FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        new saveToken().execute();
    }

    private class saveToken extends AsyncTask<String, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                RequestBody formBody = new FormBody.Builder()
                        .add("RegID", resToken)
                        .build();
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://himanshufernando.com/App/OBA/php/register.php").post(formBody).build();
                Response responses = null;
                responses = client.newCall(request).execute();

            } catch (Exception e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }

    }


}
