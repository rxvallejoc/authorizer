package com.obiectumclaro.factronica.authorizer.api;

/**
 * Created by fdelatorre on 04/09/14.
 */
public class AuthorizationService {
    private AuthorizationRequestQueue queue;

    public AuthorizationService(AuthorizationRequestQueue queue) {
        this.queue = queue;
    }

    public void authorize(AuthorizationRequest request) {
        queue.push(request);
    }
}
