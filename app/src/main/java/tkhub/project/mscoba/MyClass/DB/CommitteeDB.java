package tkhub.project.mscoba.MyClass.DB;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Himanshu on 9/16/2016.
 */
public class CommitteeDB extends RealmObject {

    @PrimaryKey
    private int id;
    private String commiteeid;
    private String commiteename;
    private String commiteeimage;
    private String commiteeposition;
    private String commiteequote;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommiteeid() {
        return commiteeid;
    }

    public void setCommiteeid(String commiteeid) {
        this.commiteeid = commiteeid;
    }

    public String getCommiteename() {
        return commiteename;
    }

    public void setCommiteename(String commiteename) {
        this.commiteename = commiteename;
    }

    public String getCommiteeimage() {
        return commiteeimage;
    }

    public void setCommiteeimage(String commiteeimage) {
        this.commiteeimage = commiteeimage;
    }

    public String getCommiteeposition() {
        return commiteeposition;
    }

    public void setCommiteeposition(String commiteeposition) {
        this.commiteeposition = commiteeposition;
    }

    public String getCommiteequote() {
        return commiteequote;
    }

    public void setCommiteequote(String commiteequote) {
        this.commiteequote = commiteequote;
    }
}
