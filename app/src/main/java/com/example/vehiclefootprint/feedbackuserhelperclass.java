package com.example.vehiclefootprint;

public class feedbackuserhelperclass {
    String uname,dis,filleddate,rating;
    public feedbackuserhelperclass() {

    }
    public feedbackuserhelperclass(String uname, String dis, String filleddate, String rating) {
        this.uname = uname;
        this.dis = dis;
        this.filleddate = filleddate;
        this.rating = rating;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public String getFilleddate() {
        return filleddate;
    }

    public void setFilleddate(String filleddate) {
        this.filleddate = filleddate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
