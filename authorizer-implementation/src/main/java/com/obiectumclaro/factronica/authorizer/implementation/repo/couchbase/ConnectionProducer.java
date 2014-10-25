package com.obiectumclaro.factronica.authorizer.implementation.repo.couchbase;

import com.couchbase.client.CouchbaseClient;
import com.obiectumclaro.factronica.authorizer.implementation.qualifiers.Authorizations;
import com.obiectumclaro.factronica.authorizer.implementation.qualifiers.Requests;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.io.IOException;

/**
 * Created by fdelatorre on 29/09/14.
 */
@Singleton
public class ConnectionProducer {

    @Produces @Requests
    private CouchbaseClient clientRequests;

    @Produces @Authorizations
    private CouchbaseClient clientAuthorizations;

    @PostConstruct
    public void produce() {
        try {
            clientRequests = new CouchbaseClient(CouchbaseProperties.NODES, CouchbaseProperties.BUCKET_REQUESTS, CouchbaseProperties.BUCKET_PASSWORD);
            clientAuthorizations = new CouchbaseClient(CouchbaseProperties.NODES, CouchbaseProperties.BUCKET_AUTHORIZATIONS, CouchbaseProperties.BUCKET_PASSWORD);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    public void disconnect() {
        clientRequests.shutdown();
    }

    public CouchbaseClient getClientRequests() {
        return clientRequests;
    }

    public CouchbaseClient getClientAuthorizations() {
        return clientAuthorizations;
    }
}
