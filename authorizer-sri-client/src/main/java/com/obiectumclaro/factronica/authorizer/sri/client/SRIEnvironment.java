package com.obiectumclaro.factronica.authorizer.sri.client;

/**
 * Created by fdelatorre on 07/09/14.
 */
public enum SRIEnvironment {

    TEST("https://celcer.sri.gob.ec");

    private String location;

    private SRIEnvironment(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}
