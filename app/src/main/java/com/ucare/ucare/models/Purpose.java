package com.ucare.ucare.models;

import java.io.Serializable;

/**
 * Created by Knight on 06/08/2016.
 */
public class Purpose implements Serializable {
    public String name;
    public String image;
    public String date;

    public Purpose(){}

    public Purpose(String name, String image, String date){
        this.name = name;
        this.image = image;
        this.date = date;
    }
}
