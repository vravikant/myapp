package com.android.newlogin;

/**
 * Created by user on 03-01-18.
 */

public class getdetails {
    String id;
    String UName;
    String email;

    public getdetails() {

    }

    public getdetails(String id, String UName, String email) {
        this.id = id;
        this.UName = UName;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getUName() {
        return UName;
    }

    public String getEmail() {
        return email;
    }
}


