package com.example.vehiclefootprint;

public class Userhelperclass2
{
    String rgno,  prevower,  newowner;
public Userhelperclass2(){
}
    public Userhelperclass2(String rgno, String prevower, String newowner) {
        this.rgno=rgno;
        this.prevower=prevower;
        this.newowner=newowner;
    }

    public String getRgno() {
        return rgno;
    }

    public void setRgno(String rgno) {
        this.rgno = rgno;
    }

    public String getPrevower() {
        return prevower;
    }

    public void setPrevower(String prevower) {
        this.prevower = prevower;
    }

    public String getNewowner() {
        return newowner;
    }

    public void setNewowner(String newowner) {
        this.newowner = newowner;
    }
}
