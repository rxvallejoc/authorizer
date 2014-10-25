package com.obiectumclaro.factronica.authorizer.implementation.repo.couchbase;

import com.couchbase.client.CouchbaseClient;
import com.obiectumclaro.factronica.authorizer.api.Authorization;
import com.obiectumclaro.factronica.authorizer.api.repo.AuthorizationRepository;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by fdelatorre on 29/09/14.
 */
public class TestAuthorizationRepositoryImpl {

    private CouchbaseClient client;
    private AuthorizationRepository repository;

    @Before
    public void setUp() throws IOException {
        ConnectionProducer producer = new ConnectionProducer();
        producer.produce();

        client = producer.getClientAuthorizations();

        repository = new AuthorizationRepositoryImpl(client);
    }

    @Test
    public void shouldAddNewRequest() throws InterruptedException {

        Authorization authorization = new Authorization();

        repository.add(authorization);

        Thread.sleep(1000);

        Authorization result = repository.find(authorization.getId());
        assertEquals(authorization.getId(), result.getId());
    }
}
