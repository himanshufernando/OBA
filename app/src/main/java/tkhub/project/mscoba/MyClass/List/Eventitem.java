package tkhub.project.mscoba.MyClass.List;

/**
 * Created by Himanshu on 4/9/2015.
 */
public class Eventitem {
    String eventID, eventTitle, eventContent, eventTumbimage, eventPublsdate, eventDuedate, eventVenue, eventTime;
    double eventLan, eventLon;

    public Eventitem(String eventID, String eventTitle, String eventContent, String eventTumbimage, String eventPublsdate, String eventDuedate, String eventVenue, String eventTime, double eventLan, double eventLon) {
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.eventContent = eventContent;
        this.eventTumbimage = eventTumbimage;
        this.eventPublsdate = eventPublsdate;
        this.eventDuedate = eventDuedate;
        this.eventVenue = eventVenue;
        this.eventTime = eventTime;
        this.eventLan = eventLan;
        this.eventLon = eventLon;
    }
}