package tkhub.project.mscoba.MyClass.DB;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Himanshu on 9/16/2016.
 */
public class NewslattersDB extends RealmObject {

    @PrimaryKey
    private int id;
    private String newsletterid;
    private String newslettertitle;
    private String newsletterpublisDate;
    private String newsletterpdf;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNewsletterid() {
        return newsletterid;
    }

    public void setNewsletterid(String newsletterid) {
        this.newsletterid = newsletterid;
    }

    public String getNewslettertitle() {
        return newslettertitle;
    }

    public void setNewslettertitle(String newslettertitle) {
        this.newslettertitle = newslettertitle;
    }

    public String getNewsletterpublisDate() {
        return newsletterpublisDate;
    }

    public void setNewsletterpublisDate(String newsletterpublisDate) {
        this.newsletterpublisDate = newsletterpublisDate;
    }

    public String getNewsletterpdf() {
        return newsletterpdf;
    }

    public void setNewsletterpdf(String newsletterpdf) {
        this.newsletterpdf = newsletterpdf;
    }
}
