package com.obiectumclaro.factronica.authorizer.sri.client.factory;

import com.obiectumclaro.factronica.authorizer.sri.client.SRIEnvironment;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.reception.RecepcionComprobantes;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.reception.RecepcionComprobantesService;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by fdelatorre on 07/09/14.
 */
public class ReceptionServiceFactory {

    private static String URL_REQUEST = "comprobantes-electronicos-ws/RecepcionComprobantes?wsdl";

    public static RecepcionComprobantes create(SRIEnvironment env) throws MalformedURLException {
        URL wsdlLocation = new URL(String.format("%s/%s", env.getLocation(), URL_REQUEST));
        QName serviceName = new QName("http://ec.gob.sri.ws.recepcion", "RecepcionComprobantesService");

        RecepcionComprobantesService service = new RecepcionComprobantesService(wsdlLocation, serviceName);
        return service.getRecepcionComprobantesPort();
    }
}
