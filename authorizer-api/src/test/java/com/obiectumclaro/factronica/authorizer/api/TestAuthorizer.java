package com.obiectumclaro.factronica.authorizer.api;

import com.obiectumclaro.factronica.authorizer.api.repo.AuthorizationRepository;
import com.obiectumclaro.factronica.authorizer.api.repo.AuthorizationRequestRepository;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by fdelatorre on 26/09/14.
 */
public class TestAuthorizer {

    private String signedInvoice;
    private String invoice;

    private Signer signer;
    private SRIAuthorizer sriAuthorizer;
    private AuthorizationRepository authorizationRepository;
    private AuthorizationRequestRepository requestRepository;


    private AuthorizationRequest request;
    private Authorizer authorizer;

    @Before
    public void initDocuments() throws IOException {
        invoice = IOUtils.toString(ClassLoader.getSystemResourceAsStream("invoice.xml"));
        signedInvoice = IOUtils.toString(ClassLoader.getSystemResourceAsStream("signedInvoice.xml"));
    }

    @Before
    public void init(){
        signer = mock(Signer.class);
        sriAuthorizer = mock(SRIAuthorizer.class);
        authorizationRepository = mock(AuthorizationRepository.class);
        requestRepository = mock(AuthorizationRequestRepository.class);

        request = new AuthorizationRequest();
        request.setDocument(new Document(invoice));

        authorizer = new Authorizer(signer, sriAuthorizer, authorizationRepository, requestRepository);
    }

    @Test
    public void shouldSignBeforeSendToAuthorize(){
        when(signer.sign(request.getDocument().getXml())).thenReturn(signedInvoice);

        Authorization authorization = new Authorization();
        when(sriAuthorizer.authorize(signedInvoice)).thenReturn(authorization);

        authorizer.authorize(request);

        verify(sriAuthorizer).authorize(signedInvoice);
    }

    @Test
    public void shouldCreateAuthorization(){
        when(signer.sign(request.getDocument().getXml())).thenReturn(signedInvoice);

        Authorization authorization = new Authorization();
        when(sriAuthorizer.authorize(signedInvoice)).thenReturn(authorization);

        authorizer.authorize(request);

        verify(authorizationRepository).add(authorization);
    }

    @Test
    public void shouldAddAuthorizationToRequest(){
        when(signer.sign(request.getDocument().getXml())).thenReturn(signedInvoice);

        Authorization authorization = new Authorization();
        when(sriAuthorizer.authorize(signedInvoice)).thenReturn(authorization);

        authorizer.authorize(request);

        assertEquals(authorization.getId(), request.getDocument().getAuthorization());

        verify(requestRepository).updateAuthorization(request);
    }
}
