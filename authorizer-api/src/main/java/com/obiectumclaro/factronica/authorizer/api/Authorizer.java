package com.obiectumclaro.factronica.authorizer.api;

import com.obiectumclaro.factronica.authorizer.api.repo.AuthorizationRepository;
import com.obiectumclaro.factronica.authorizer.api.repo.AuthorizationRequestRepository;

/**
 * Created by fdelatorre on 04/09/14.
 */
public class Authorizer {

    private Signer signer;
    private SRIAuthorizer sriAuthorizer;
    private AuthorizationRepository authorizationRepository;
    private AuthorizationRequestRepository requestRepository;

    public Authorizer(Signer signer, SRIAuthorizer sriAuthorizer, AuthorizationRepository authorizationRepository, AuthorizationRequestRepository requestRepository) {

        this.signer = signer;
        this.sriAuthorizer = sriAuthorizer;
        this.authorizationRepository = authorizationRepository;
        this.requestRepository = requestRepository;
    }

    public void authorize(AuthorizationRequest request){
        Authorization authorization = authorizeDocument(request.getDocument());
        linkAuthorization(request, authorization);
    }

    private Authorization authorizeDocument(Document document){
        String signedDocument = signer.sign(document.getXml());
        Authorization authorization = sriAuthorizer.authorize(signedDocument);
        authorizationRepository.add(authorization);

        return authorization;
    }

    private void linkAuthorization(AuthorizationRequest request, Authorization authorization){
        request.getDocument().setAuthorization(authorization.getId());
        requestRepository.updateAuthorization(request);
    }
}
