package com.obiectumclaro.factronica.authorizer.api.repo;

import com.obiectumclaro.factronica.authorizer.api.Authorization;

/**
 * Created by fdelatorre on 26/09/14.
 */
public interface AuthorizationRepository {
    void add(Authorization authorization);

    Authorization find(String id);
}
