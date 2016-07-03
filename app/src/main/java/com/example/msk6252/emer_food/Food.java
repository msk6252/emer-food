package com.example.msk6252.emer_food;

/**
 * Created by msk6252 on 16/07/02.
 */

//getter、setterを定義し、AdapterやMainActivityで利用
public class Food {
    private String id;
    private String name;
    private String day;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
