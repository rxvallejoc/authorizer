package com.obiectumclaro.factronica.authorizer.api;

import com.obiectumclaro.factronica.authorizer.api.repo.AuthorizationRequestRepository;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by fdelatorre on 04/09/14.
 */
public class TestAuthorizationProcessor {

    private AuthorizationRequestRepository repository;
    private Authorizer authorizer;
    private AuthorizationProcessor processor;
    private Notifier notifier;

    @Before
    public void setUp() throws Exception {
        repository = mock(AuthorizationRequestRepository.class);
        authorizer = mock(Authorizer.class);
        notifier = mock(Notifier.class);
        processor = new AuthorizationProcessor(repository, authorizer, notifier);
    }

    @Test
    public void shouldProcessRequest(){
        AuthorizationRequest request = new AuthorizationRequest();

        processor.process(request);

        verify(repository).add(request);
        verify(authorizer).authorize(request);
        verify(repository).updateStatus(request);
        verify((notifier)).notify(request);

        assertEquals(AuthorizationRequest.Status.CLOSE, request.getStatus());
    }


}
