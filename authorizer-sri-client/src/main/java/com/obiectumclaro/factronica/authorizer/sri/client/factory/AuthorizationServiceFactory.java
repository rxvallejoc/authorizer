package com.obiectumclaro.factronica.authorizer.sri.client.factory;

import com.obiectumclaro.factronica.authorizer.sri.client.SRIEnvironment;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.authorization.AutorizacionComprobantes;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.authorization.AutorizacionComprobantesService;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fdelatorre on 07/09/14.
 */
public class AuthorizationServiceFactory {

    private static String URL_QUERY = "comprobantes-electronicos-ws/AutorizacionComprobantes?wsdl";

    public static AutorizacionComprobantes create(SRIEnvironment env) throws MalformedURLException {
        URL wsdlLocation = new URL(String.format("%s/%s", env.getLocation(), URL_QUERY));
        QName serviceName = new QName("http://ec.gob.sri.ws.autorizacion", "AutorizacionComprobantesService");

        AutorizacionComprobantesService service = new AutorizacionComprobantesService(wsdlLocation, serviceName);
        return service.getAutorizacionComprobantesPort();
    }
}
