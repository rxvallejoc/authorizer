package com.obiectumclaro.factronica.authorizer.implementation.repo.couchbase;

import com.couchbase.client.CouchbaseClient;
import com.google.gson.Gson;
import com.obiectumclaro.factronica.authorizer.api.Authorization;
import com.obiectumclaro.factronica.authorizer.api.repo.AuthorizationRepository;
import com.obiectumclaro.factronica.authorizer.implementation.qualifiers.Authorizations;

import javax.inject.Inject;

/**
 * Created by fdelatorre on 29/09/14.
 */
public class AuthorizationRepositoryImpl implements AuthorizationRepository {

    private CouchbaseClient client;

    @Inject
    public AuthorizationRepositoryImpl(@Authorizations CouchbaseClient client) {
        this.client = client;
    }

    @Override
    public void add(Authorization authorization) {
        String jsonAuthorization = new Gson().toJson(authorization);
        client.add(authorization.getId(), jsonAuthorization);
    }

    @Override
    public Authorization find(String id) {
        String jsonAuthorization = String.class.cast(client.get(id));
        return  new Gson().fromJson(jsonAuthorization, Authorization.class);
    }
}
