package com.obiectumclaro.factronica.authorizer.api;

/**
 * Created by fdelatorre on 26/09/14.
 */
public interface Signer {

    String sign(String xmlDocument);
}
