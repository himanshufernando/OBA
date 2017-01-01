package tkhub.project.mscoba.MyClass.List;

/**
 * Created by Himanshu on 4/10/2015.
 */
public class Newsitem {

    String iD, titel, content, publisDate,image,url;

    public String getiD() {
        return iD;
    }

    public void setiD(String iD) {
        this.iD = iD;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisDate() {
        return publisDate;
    }

    public void setPublisDate(String publisDate) {
        this.publisDate = publisDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Newsitem(String iD, String image, String titel, String content, String publisDate,String url) {
        this.iD = iD;
        this.image = image;
        this.titel = titel;
        this.content = content;
        this.publisDate = publisDate;
        this.url=url;

    }

    public Newsitem(String iD, String image) {
        this.iD = iD;
        this.image = image;
    }
}
