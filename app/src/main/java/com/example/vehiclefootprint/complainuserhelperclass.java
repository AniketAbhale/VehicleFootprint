package com.example.vehiclefootprint;

public class complainuserhelperclass {
    String name,email,dis,filleddate;
    public complainuserhelperclass() {

    }

    public complainuserhelperclass(String name, String email, String dis, String filleddate) {
        this.name = name;
        this.email = email;
        this.dis = dis;
        this.filleddate = filleddate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
