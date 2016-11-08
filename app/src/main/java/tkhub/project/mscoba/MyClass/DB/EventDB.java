package tkhub.project.mscoba.MyClass.DB;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Himanshu on 9/16/2016.
 */
public class EventDB extends RealmObject {

    @PrimaryKey
    private int id;
    private String eventid;
    private String eventname;
    private String eventcontent;
    private String eventtumbimage;
    private String eventpublishdate;
    private String eventduedate;
    private String eventvenue;
    private String eventtime;
    private Double eventlatitude;
    private Double eventlongitude;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventcontent() {
        return eventcontent;
    }

    public void setEventcontent(String eventcontent) {
        this.eventcontent = eventcontent;
    }

    public String getEventtumbimage() {
        return eventtumbimage;
    }

    public void setEventtumbimage(String eventtumbimage) {
        this.eventtumbimage = eventtumbimage;
    }

    public String getEventpublishdate() {
        return eventpublishdate;
    }

    public void setEventpublishdate(String eventpublishdate) {
        this.eventpublishdate = eventpublishdate;
    }

    public String getEventduedate() {
        return eventduedate;
    }

    public void setEventduedate(String eventduedate) {
        this.eventduedate = eventduedate;
    }

    public String getEventvenue() {
        return eventvenue;
    }

    public void setEventvenue(String eventvenue) {
        this.eventvenue = eventvenue;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public Double getEventlatitude() {
        return eventlatitude;
    }

    public void setEventlatitude(Double eventlatitude) {
        this.eventlatitude = eventlatitude;
    }

    public Double getEventlongitude() {
        return eventlongitude;
    }

    public void setEventlongitude(Double eventlongitude) {
        this.eventlongitude = eventlongitude;
    }
}
