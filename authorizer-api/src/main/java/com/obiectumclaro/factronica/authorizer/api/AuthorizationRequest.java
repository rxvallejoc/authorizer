package com.obiectumclaro.factronica.authorizer.api;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by fdelatorre on 04/09/14.
 */
public class AuthorizationRequest implements Serializable {

    public enum Status {PROGRESS, CLOSE}

    private String id;
    private Status status;
    private Document document;

    public AuthorizationRequest() {
        this(UUID.randomUUID().toString());
    }

    public AuthorizationRequest(String id) {
        this.id = id;
        this.status = Status.PROGRESS;
    }

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        if(this.status.equals(Status.CLOSE))
            throw new IllegalArgumentException("No es posible cambiar el estado cuando ya se encuentra cerrado");
        this.status = status;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
}
