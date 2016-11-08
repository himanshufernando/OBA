package tkhub.project.mscoba.MyClass.DB;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Himanshu on 9/16/2016.
 */
public class CalendarDB extends RealmObject {

    @PrimaryKey
    private int id;
    private String calendarid;
    private String calendarevent;
    private String calendardate;
    private String calendarvanue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCalendarid() {
        return calendarid;
    }

    public void setCalendarid(String calendarid) {
        this.calendarid = calendarid;
    }

    public String getCalendarevent() {
        return calendarevent;
    }

    public void setCalendarevent(String calendarevent) {
        this.calendarevent = calendarevent;
    }

    public String getCalendardate() {
        return calendardate;
    }

    public void setCalendardate(String calendardate) {
        this.calendardate = calendardate;
    }

    public String getCalendarvanue() {
        return calendarvanue;
    }

    public void setCalendarvanue(String calendarvanue) {
        this.calendarvanue = calendarvanue;
    }
}
