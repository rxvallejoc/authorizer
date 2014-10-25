package com.obiectumclaro.factronica.authorizer.sri.client;

/**
 * Created by fdelatorre on 07/09/14.
 */
public enum SRIResponseReception {

    RECEIVED("RECIBIDA"), RETURNED("DEVUELTA");

    private String description;

    private SRIResponseReception(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
