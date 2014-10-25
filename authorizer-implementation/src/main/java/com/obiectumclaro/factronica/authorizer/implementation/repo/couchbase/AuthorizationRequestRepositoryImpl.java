package com.obiectumclaro.factronica.authorizer.implementation.repo.couchbase;

import com.couchbase.client.CouchbaseClient;
import com.google.gson.Gson;
import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequest;
import com.obiectumclaro.factronica.authorizer.api.repo.AuthorizationRequestRepository;
import com.obiectumclaro.factronica.authorizer.implementation.qualifiers.Requests;
import net.spy.memcached.internal.OperationFuture;

import javax.inject.Inject;

/**
 * Created by fdelatorre on 29/09/14.
 */
public class AuthorizationRequestRepositoryImpl implements AuthorizationRequestRepository {


    private CouchbaseClient client;

    @Inject
    public AuthorizationRequestRepositoryImpl(@Requests CouchbaseClient client) {
        this.client = client;
    }

    @Override
    public void updateAuthorization(AuthorizationRequest request) {
        //AuthorizationRequest minimalRequest = new AuthorizationRequest(request.getId());
        //minimalRequest.getDocument().setAuthorization(request.getDocument().getAuthorization());

        //String jsonRequest = new Gson().toJson(minimalRequest);
        //client.set(minimalRequest.getId(), jsonRequest);

        String jsonRequest = new Gson().toJson(request);
        client.set(request.getId(), jsonRequest);
    }

    @Override
    public void updateStatus(AuthorizationRequest request) {
        //AuthorizationRequest minimalRequest = new AuthorizationRequest(request.getId());
        //minimalRequest.setStatus(request.getStatus());

        //String jsonRequest = new Gson().toJson(minimalRequest);
        //client.set(minimalRequest.getId(), jsonRequest);

        String jsonRequest = new Gson().toJson(request);
        client.set(request.getId(), jsonRequest);
    }

    @Override
    public void add(AuthorizationRequest request) {
        String jsonRequest = new Gson().toJson(request);
        client.add(request.getId(), jsonRequest);
    }

    @Override
    public AuthorizationRequest find(String id) {
        String jsonRequest = String.class.cast(client.get(id));
        return new Gson().fromJson(jsonRequest, AuthorizationRequest.class);
    }
}
