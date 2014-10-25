package com.obiectumclaro.factronica.authorizer.implementation.repo.couchbase;

import com.couchbase.client.CouchbaseClient;
import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequest;
import com.obiectumclaro.factronica.authorizer.api.Document;
import com.obiectumclaro.factronica.authorizer.api.repo.AuthorizationRequestRepository;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by fdelatorre on 29/09/14.
 */
public class TestAuthorizationRequestRepositoryImpl {

    private CouchbaseClient client;
    private String invoice;
    private AuthorizationRequestRepository repository;

    @Before
    public void setUp() throws IOException {
        ConnectionProducer producer = new ConnectionProducer();
        producer.produce();

        client = producer.getClientRequests();
        invoice = IOUtils.toString(ClassLoader.getSystemResourceAsStream("invoice.xml"));

        repository = new AuthorizationRequestRepositoryImpl(client);
    }

    @Test
    public void shouldAddNewRequest() throws InterruptedException {

        AuthorizationRequest request = new AuthorizationRequest();
        request.setDocument(new Document(invoice));
        repository.add(request);

        Thread.sleep(1000);

        AuthorizationRequest result = repository.find(request.getId());
        assertEquals(request.getId(), result.getId());
        assertEquals(request.getDocument().getXml(), result.getDocument().getXml());
    }

    @Test()
    public void shouldNotAddRequestWithSameId() throws InterruptedException {
        AuthorizationRequest request = new AuthorizationRequest();

        repository.add(request);

        Thread.sleep(1000);

        AuthorizationRequest result = repository.find(request.getId());
        assertNotNull(result);

        request.setDocument(new Document(invoice));
        repository.add(request);

        result = repository.find(request.getId());
        assertNull(result.getDocument());
    }

    @Test
    public void shouldUpdateStatus() throws InterruptedException {
        AuthorizationRequest request = new AuthorizationRequest();
        request.setDocument(new Document(invoice));
        repository.add(request);

        request.setStatus(AuthorizationRequest.Status.CLOSE);
        repository.updateStatus(request);

        Thread.sleep(1000);

        AuthorizationRequest result = repository.find(request.getId());
        assertNotNull(result.getDocument());
        assertEquals(invoice, result.getDocument().getXml());
        assertEquals(AuthorizationRequest.Status.CLOSE, result.getStatus());
    }


    @Test
    public void shouldUpdateAuthorization() throws InterruptedException {
        AuthorizationRequest request = new AuthorizationRequest();
        request.setDocument(new Document(invoice));
        repository.add(request);

        request.getDocument().setAuthorization("ABC123");
        repository.updateAuthorization(request);

        Thread.sleep(1000);

        AuthorizationRequest result = repository.find(request.getId());
        assertNotNull(result.getDocument());
        assertEquals(invoice, result.getDocument().getXml());
        assertEquals("ABC123", result.getDocument().getAuthorization());
    }
}
