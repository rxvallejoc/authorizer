package com.obiectumclaro.factronica.authorizer.api;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by fdelatorre on 26/09/14.
 */
public class TestAuthorization {

    @Test
    public void shouldGenerateId(){
        Authorization authorization = new Authorization();
        assertNotNull(authorization.getId());
    }
}
