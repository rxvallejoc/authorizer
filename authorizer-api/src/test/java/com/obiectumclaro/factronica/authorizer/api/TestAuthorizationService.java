package com.obiectumclaro.factronica.authorizer.api;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by fdelatorre on 03/09/14.
 */
public class TestAuthorizationService {

    private AuthorizationRequestQueue queue;
    private AuthorizationService service;

    @Before
    public void setUp() throws Exception {
        queue = mock(AuthorizationRequestQueue.class);
        service = new AuthorizationService(queue);
    }

    @Test
    public void shouldSendAuthorizationRequestToQueue(){
        AuthorizationRequest request = new AuthorizationRequest();
        service.authorize(request);

        verify(queue).push(request);
    }

}
