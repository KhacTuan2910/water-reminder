package com.example.water;

import java.io.Serializable;

public class Water implements Serializable {
    private int id;
    private String title, des, time;


    public Water(int id, String title, String des, String time) {
        this.id = id;
        this.title = title;
        this.des = des;
        this.time = time;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTime() {
        return time;
    }

    public void setPrice(String price) {
        this.time = price;
    }
}
