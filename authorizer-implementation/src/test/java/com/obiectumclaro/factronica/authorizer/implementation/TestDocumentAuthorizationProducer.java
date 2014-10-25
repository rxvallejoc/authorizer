package com.obiectumclaro.factronica.authorizer.implementation;

import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequest;
import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequestQueue;
import com.obiectumclaro.factronica.authorizer.api.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by fdelatorre on 04/09/14.
 */
@RunWith(Arquillian.class)
public class TestDocumentAuthorizationProducer {

    @Deployment(name = "request")
    public static WebArchive getDeployable() {
        final WebArchive deployable = ShrinkWrap.create(WebArchive.class)
                .addClass(AuthorizationRequest.class)
                .addClass(AuthorizationRequestQueue.class)
                .addClass(AuthorizationRequestQueueJMSProducer.class)
                .addClass(SampleAuthorizationRequestQueueReader.class)
                .addClass(Document.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource("META-INF/factronica-authorization-queue-jms.xml");

        System.out.println(deployable.toString(true));
        return deployable;
    }

    @Inject
    private AuthorizationRequestQueue queue;
    private AuthorizationRequest request;

    @Before
    public void createMessage() throws Exception {
        request = new AuthorizationRequest();
    }

    @Test
    public void shouldPushRequestInQueue() throws InterruptedException {
        queue.push(request);
        Thread.sleep(1000);
        String requestId = SampleAuthorizationRequestQueueReader.requestId;
        assertEquals(request.getId(), requestId);
    }
}
