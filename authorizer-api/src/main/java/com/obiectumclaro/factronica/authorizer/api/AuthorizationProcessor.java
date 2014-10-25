package com.obiectumclaro.factronica.authorizer.api;

import com.obiectumclaro.factronica.authorizer.api.repo.AuthorizationRequestRepository;

import javax.inject.Inject;

/**
 * Created by fdelatorre on 04/09/14.
 */
public class AuthorizationProcessor {

    private AuthorizationRequestRepository repository;
    private Authorizer authorizer;
    private Notifier notifier;

    @Inject
    public AuthorizationProcessor(AuthorizationRequestRepository repository, Authorizer authorizer, Notifier notifier) {
        this.repository = repository;
        this.authorizer = authorizer;
        this.notifier = notifier;
    }

    public void process(AuthorizationRequest request){
        repository.add(request);
        authorizer.authorize(request);
        closeRequest(request);
        notifier.notify(request);
    }

    private void closeRequest(AuthorizationRequest request){
        request.setStatus(AuthorizationRequest.Status.CLOSE);
        repository.updateStatus(request);
    }

}
