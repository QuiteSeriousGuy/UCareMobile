package com.ucare.ucare.models;

/**
 * Created by Knight on 06/08/2016.
 */
public class LoginBody {
    public String email;
    public String password;

    public LoginBody(String email, String password){
        this.email = email;
        this.password = password;
    }
}
