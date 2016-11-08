package tkhub.project.mscoba.MyClass.DB;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Himanshu on 9/5/2016.
 */
public class NewsDB extends RealmObject {

    @PrimaryKey
    private int id;
    private String newsid;
    private String newsimage;
    private String newstitle;
    private String newscontent;
    private String newspublishDate;
    private String newsurl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsid() {
        return newsid;
    }

    public void setNewsid(String newsid) {
        this.newsid = newsid;
    }

    public String getNewsimage() {
        return newsimage;
    }

    public void setNewsimage(String newsimage) {
        this.newsimage = newsimage;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public String getNewscontent() {
        return newscontent;
    }

    public void setNewscontent(String newscontent) {
        this.newscontent = newscontent;
    }

    public String getNewspublishDate() {
        return newspublishDate;
    }

    public void setNewspublishDate(String newspublishDate) {
        this.newspublishDate = newspublishDate;
    }

    public String getNewsurl() {
        return newsurl;
    }

    public void setNewsurl(String newsurl) {
        this.newsurl = newsurl;
    }
}
