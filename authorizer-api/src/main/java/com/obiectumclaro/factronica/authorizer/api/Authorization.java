package com.obiectumclaro.factronica.authorizer.api;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by fdelatorre on 26/09/14.
 */
public class Authorization implements Serializable {

    private String id;

    public Authorization() {
        this.id = UUID.randomUUID().toString();;
    }

    public String getId() {
        return id;
    }
}
