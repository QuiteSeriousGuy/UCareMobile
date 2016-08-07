package com.ucare.ucare.models;

/**
 * Created by Knight on 06/08/2016.
 */
public class Transaction {
    public String donorName;
    public String date;
    public String amount;
    public Boolean isAnon;
    public String message;

    public Transaction(){}

    public Transaction(String donorName, String date, String amount, Boolean isAnon, String message){
        this.donorName = donorName;
        this.date = date;
        this.amount = amount;
        this.isAnon = isAnon;
        this.message = message;
    }
}
