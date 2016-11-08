package tkhub.project.mscoba.MyClass.Servies;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import tkhub.project.mscoba.MyClass.DB.CalendarDB;
import tkhub.project.mscoba.MyClass.DB.CommitteeDB;
import tkhub.project.mscoba.MyClass.DB.EventDB;
import tkhub.project.mscoba.MyClass.DB.NewsDB;
import tkhub.project.mscoba.MyClass.DB.NewslattersDB;

/**
 * Created by Himanshu on 2/14/2016.
 */
public class JsonServices {
    Context context;
    private Realm mRealm;
    private RealmConfiguration realmConfig;

    public JsonServices(Context con) {

        try {
            context = con;
            Realm.init(context);

            mRealm = Realm.getDefaultInstance();
        } catch (Exception ex) {
        }
    }

    public void taskServices() {

        try {
            new getNews().execute();
            new getEvent().execute();
            new getNewsletters().execute();
            new getYearCalender().execute();
            new getCommite().execute();
        } catch (Exception ex) {
        }


    }

    private class getNews extends AsyncTask<Void, Void, Void> {

        JSONObject Jobject;
        JSONArray Jarray;
        JSONObject object;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.himanshufernando.com/App/OBA/php/allnews.php").build();
                Response responses = null;

                responses = client.newCall(request).execute();

                String jsonData = responses.body().string();
                Jobject = new JSONObject(jsonData);
                Jarray = Jobject.getJSONArray("allNews");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            try {

                final RealmResults<NewsDB> results = mRealm.where(NewsDB.class).findAll();
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        results.deleteAllFromRealm();
                        try {
                            for (int i = 0; i < Jarray.length(); i++) {

                                object = Jarray.getJSONObject(i);
                                NewsDB newsdb = realm.createObject(NewsDB.class,Integer.parseInt(object.getString("newsid")));
                                newsdb.setNewsid(object.getString("newsid"));
                                newsdb.setNewsimage(object.getString("newsimage"));
                                newsdb.setNewstitle(object.getString("newstitle"));
                                newsdb.setNewscontent(object.getString("newscontent"));
                                newsdb.setNewspublishDate(object.getString("newspublishDate"));
                                newsdb.setNewsurl(object.getString("newsurl"));


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }catch (Exception e){

            }




        }

    }

    private class getEvent extends AsyncTask<Void, Void, Void> {
        JSONObject Jobject;
        JSONArray Jarray;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.himanshufernando.com/App/OBA/php/allevent.php").build();
                Response responses = null;
                responses = client.newCall(request).execute();
                String jsonData = responses.body().string();
                Jobject = new JSONObject(jsonData);
                Jarray = Jobject.getJSONArray("allEvent");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLiteConstraintException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {

            final RealmResults<EventDB> results = mRealm.where(EventDB.class).findAll();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    results.deleteAllFromRealm();
                    try {
                        for (int i = 0; i < Jarray.length(); i++) {

                            Jobject = Jarray.getJSONObject(i);

                            EventDB eventdb = realm.createObject(EventDB.class,Integer.parseInt(Jobject.getString("eventid")));
                            eventdb.setEventid(Jobject.getString("eventid"));
                            eventdb.setEventname(Jobject.getString("eventname"));
                            eventdb.setEventcontent(Jobject.getString("eventcontent"));
                            eventdb.setEventtumbimage(Jobject.getString("eventtumbimage"));
                            eventdb.setEventpublishdate(Jobject.getString("eventpublishdate"));
                            eventdb.setEventduedate(Jobject.getString("eventduedate"));
                            eventdb.setEventvenue(Jobject.getString("eventvenue"));
                            eventdb.setEventtime(Jobject.getString("eventtime"));
                            eventdb.setEventlatitude(Double.parseDouble(Jobject.getString("eventlatitude")));
                            eventdb.setEventlongitude(Double.parseDouble(Jobject.getString("eventlongitude")));


                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

        }catch (Exception ex){}
        }

    }

    private class getNewsletters extends AsyncTask<Void, Void, Void> {
        JSONObject Jobject;
        JSONArray Jarray;
        JSONObject object;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://www.himanshufernando.com/App/OBA/php/allnewslatters.php").build();
                Response responses = null;

                responses = client.newCall(request).execute();

                String jsonData = responses.body().string();
                Jobject = new JSONObject(jsonData);
                Jarray = Jobject.getJSONArray("allnewslatters");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            try {

                final RealmResults<NewslattersDB> results = mRealm.where(NewslattersDB.class).findAll();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    results.deleteAllFromRealm();
                    try {
                        for (int i = 0; i < Jarray.length(); i++) {

                            object = Jarray.getJSONObject(i);

                            NewslattersDB newslatterdb = realm.createObject(NewslattersDB.class,Integer.parseInt(object.getString("newsletterid")));
                            newslatterdb.setNewsletterid(object.getString("newsletterid"));
                            newslatterdb.setNewslettertitle(object.getString("newslettertitle"));
                            newslatterdb.setNewsletterpublisDate(object.getString("newsletterpublisDate"));
                            newslatterdb.setNewsletterpdf(object.getString("newsletterpdf"));

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            }catch (Exception ex){}
        }

    }


    private class getYearCalender extends AsyncTask<Void, Void, Void> {
        JSONObject Jobject;
        JSONArray Jarray;
        JSONObject object;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://himanshufernando.com/App/OBA/php/allcalender.php").build();
                Response responses = null;

                responses = client.newCall(request).execute();

                String jsonData = responses.body().string();
                Jobject = new JSONObject(jsonData);
                Jarray = Jobject.getJSONArray("allCalendar");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {
            final RealmResults<CalendarDB> results = mRealm.where(CalendarDB.class).findAll();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    results.deleteAllFromRealm();
                    try {
                        for (int i = 0; i < Jarray.length(); i++) {

                            object = Jarray.getJSONObject(i);

                            CalendarDB calendardb = realm.createObject(CalendarDB.class,Integer.parseInt(object.getString("calendarid")));
                            calendardb.setCalendarid(object.getString("calendarid"));
                            calendardb.setCalendarevent(object.getString("calendarevent"));
                            calendardb.setCalendardate(object.getString("calendardate"));
                            calendardb.setCalendarvanue(object.getString("calendarvanue"));

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
        }catch (Exception ex){}
        }

    }


    private class getCommite extends AsyncTask<Void, Void, Void> {
        JSONObject Jobject;
        JSONArray Jarray;
        JSONObject object;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://himanshufernando.com/App/OBA/php/committee.php").build();
                Response responses = null;
                responses = client.newCall(request).execute();
                String jsonData = responses.body().string();
                Jobject = new JSONObject(jsonData);
                Jarray = Jobject.getJSONArray("committee");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {
            final RealmResults<CommitteeDB> results = mRealm.where(CommitteeDB.class).findAll();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    results.deleteAllFromRealm();
                    try {
                        for (int i = 0; i < Jarray.length(); i++) {

                            object = Jarray.getJSONObject(i);
                            CommitteeDB committeedb = realm.createObject(CommitteeDB.class,Integer.parseInt(object.getString("commiteeid")));
                            committeedb.setCommiteeid(object.getString("commiteeid"));
                            committeedb.setCommiteename(object.getString("commiteename"));
                            committeedb.setCommiteeimage(object.getString("commiteeimage"));
                            committeedb.setCommiteeposition(object.getString("commiteeposition"));
                            committeedb.setCommiteequote(object.getString("commiteequote"));

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

            }catch (Exception ex){}
        }
    }






}

