package com.obiectumclaro.factronica.authorizer.api;

/**
 * Created by fdelatorre on 04/09/14.
 */
public interface AuthorizationRequestQueue {

    void push(AuthorizationRequest request);
}
