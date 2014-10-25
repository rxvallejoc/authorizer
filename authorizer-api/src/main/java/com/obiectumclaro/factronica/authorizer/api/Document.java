package com.obiectumclaro.factronica.authorizer.api;

import java.io.Serializable;

/**
 * Created by fdelatorre on 29/09/14.
 */
public class Document implements Serializable {

    private String xml;
    private String authorization;

    public Document(String xml) {
        this.xml = xml;
    }

    public String getXml() {
        return xml;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

}
