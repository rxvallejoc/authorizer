package com.obiectumclaro.factronica.authorizer.implementation.repo.couchbase;

import com.couchbase.client.CouchbaseClient;
import com.obiectumclaro.factronica.authorizer.api.Authorization;
import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequest;
import com.obiectumclaro.factronica.authorizer.implementation.qualifiers.Authorizations;
import com.obiectumclaro.factronica.authorizer.implementation.qualifiers.Requests;
import com.sun.management.OperatingSystemMXBean;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by fdelatorre on 29/09/14.
 */
@RunWith(Arquillian.class)
public class TestConnectionProducer {

    @Deployment(name = "connManager")
    public static WebArchive getDeployable() {
        File[] libs = Maven.resolver().resolve("com.couchbase.client:couchbase-client:1.4.4").withTransitivity().asFile();

        final WebArchive deployable = ShrinkWrap.create(WebArchive.class)
                .addClasses(ConnectionProducer.class, CouchbaseProperties.class)
                .addClass(OperatingSystemMXBean.class)
                .addClasses(Requests.class, Authorizations.class)
                .addAsLibraries(libs)
                .addClasses(AuthorizationRequest.class, com.obiectumclaro.factronica.authorizer.api.Document.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        System.out.println(deployable.toString(true));
        return deployable;
    }

    @Requests @Inject
    private CouchbaseClient requestsClient1;
    @Requests @Inject
    private CouchbaseClient requestsClient2;
    @Authorizations @Inject
    private CouchbaseClient authorizationsClient1;
    @Authorizations @Inject
    private CouchbaseClient authorizationsClient2;

    @Test
    public void shouldBeSingleton(){
        assertEquals(requestsClient1, requestsClient2);
        assertEquals(authorizationsClient1, authorizationsClient2);
    }

    @Test
    public void ShouldBeDifferents(){
        assertNotEquals(requestsClient1, authorizationsClient1);
    }

}
