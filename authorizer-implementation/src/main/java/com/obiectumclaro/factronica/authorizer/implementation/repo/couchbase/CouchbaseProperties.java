package com.obiectumclaro.factronica.authorizer.implementation.repo.couchbase;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fdelatorre on 29/09/14.
 */
public class CouchbaseProperties {

    public static final List<URI> NODES = Arrays.asList(URI.create("http://127.0.0.1:8091/pools"));
    public static final String BUCKET_REQUESTS = "requests";
    public static final String BUCKET_AUTHORIZATIONS = "authorizations";
    public static final String BUCKET_PASSWORD = "";

}
