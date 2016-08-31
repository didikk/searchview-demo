package me.didik.searchviewdemo;

/**
 * Created by didik on 31/08/16.
 */
public class Country {
    private String name;
    private String isocode;

    public Country(String name, String isocode) {
        this.name = name;
        this.isocode = isocode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsocode() {
        return isocode;
    }

    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }
}