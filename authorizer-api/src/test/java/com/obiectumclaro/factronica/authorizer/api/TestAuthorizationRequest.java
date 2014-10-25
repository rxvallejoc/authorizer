package com.obiectumclaro.factronica.authorizer.api;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by fdelatorre on 04/09/14.
 */
public class TestAuthorizationRequest {

    @Test
    public void shouldGenerateId(){
        AuthorizationRequest request = new AuthorizationRequest();
        assertNotNull(request.getId());
    }

    @Test
    public void shouldCreateWithStatusOPEN(){
        AuthorizationRequest request = new AuthorizationRequest();
        assertEquals(AuthorizationRequest.Status.PROGRESS, request.getStatus());
    }

    @Test
    public void shouldAllowChangeStatusToCLOSEWhenIsPROGRESS(){
        AuthorizationRequest request = new AuthorizationRequest();

        request.setStatus(AuthorizationRequest.Status.CLOSE);
        assertEquals(AuthorizationRequest.Status.CLOSE, request.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowChangeStatusWhenIsClose(){
        AuthorizationRequest request = new AuthorizationRequest();

        request.setStatus(AuthorizationRequest.Status.CLOSE);
        request.setStatus(AuthorizationRequest.Status.PROGRESS);
    }
}
