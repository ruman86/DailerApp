package net.islbd.ruman.dailerapp;

/**
 * Created by Ruman on 5/5/2018.
 */

public class Contact {
    private String name;
    private String phoneNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String toString() {
        return "name: " + name + "\n phoneNumber: " + phoneNumber;
    }
}
