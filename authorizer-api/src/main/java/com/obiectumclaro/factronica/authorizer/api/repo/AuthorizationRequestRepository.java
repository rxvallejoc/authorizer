package com.obiectumclaro.factronica.authorizer.api.repo;

import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequest;

/**
 * Created by fdelatorre on 26/09/14.
 */
public interface AuthorizationRequestRepository {
    void updateAuthorization(AuthorizationRequest request);

    void updateStatus(AuthorizationRequest request);

    void add(AuthorizationRequest request);

    AuthorizationRequest find(String id);
}
