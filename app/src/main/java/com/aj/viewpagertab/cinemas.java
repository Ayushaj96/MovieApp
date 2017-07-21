package com.aj.viewpagertab;

/**
 * Created by AYUSH on 7/19/2017.
 */

public class cinemas {
    private String name;
    private long phone;
    private String address1;
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {

        return url;
    }

    public cinemas(String url) {

        this.url = url;
    }

    public cinemas() {
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getName() {

        return name;
    }

    public long getPhone() {
        return phone;
    }

    public cinemas(String name, long phone) {

        this.name = name;
        this.phone = phone;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress1() {

        return address1;
    }
}
