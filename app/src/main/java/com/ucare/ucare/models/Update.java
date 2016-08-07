package com.ucare.ucare.models;

/**
 * Created by Knight on 06/08/2016.
 */
public class Update {
    public String image;
    public String details;
    public String date;

    public Update(){}

    public Update(String details, String image, String date){
        this.image = image;
        this.details = details;
        this.date = date;
    }
}
