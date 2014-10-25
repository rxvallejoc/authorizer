package com.obiectumclaro.factronica.authorizer.sri.client.wsdl;

import com.obiectumclaro.factronica.authorizer.sri.client.SRIEnvironment;
import com.obiectumclaro.factronica.authorizer.sri.client.factory.AuthorizationServiceFactory;
import com.obiectumclaro.factronica.authorizer.sri.client.factory.ReceptionServiceFactory;
import com.obiectumclaro.factronica.authorizer.sri.client.util.SRIDocumentInfoFinder;
import com.obiectumclaro.factronica.authorizer.sri.client.util.exception.InvalidXMLDocumentException;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.authorization.Autorizacion;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.authorization.AutorizacionComprobantes;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.authorization.Mensaje;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.authorization.RespuestaComprobante;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.reception.RecepcionComprobantes;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.reception.RespuestaSolicitud;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

/**
 * Created by fdelatorre on 07/09/14.
 */
public class TestAuthorizationResponseSRI {

    private byte[] invoice;
    private SRIDocumentInfoFinder finder;

    @Before
    public void initDocuments() throws IOException, InvalidXMLDocumentException {
        InputStream is = ClassLoader.getSystemResourceAsStream("signedInvoice.xml");
        invoice = new byte[is.available()];
        is.read(invoice);
        finder = new SRIDocumentInfoFinder(invoice);
    }

    @Test
    public void shouldRetrieveAuthorizationResponse() throws MalformedURLException, InterruptedException {
        AutorizacionComprobantes service = AuthorizationServiceFactory.create(SRIEnvironment.TEST);
        RespuestaComprobante response = service.autorizacionComprobante(finder.getAccessKey());
        Integer expected = Integer.valueOf(response.getNumeroComprobantes()) + 1;

        sendValidDocument();

        int i = 0;
        while(i < 5){
            Thread.sleep(1000);
            response = service.autorizacionComprobante(finder.getAccessKey());
            if(expected <= Integer.valueOf(response.getNumeroComprobantes())){
                break;
            }
            i++;
        }

        assertEquals(expected.intValue(), response.getAutorizaciones().getAutorizacion().size());
        assertEquals(response.getNumeroComprobantes(), ""+response.getAutorizaciones().getAutorizacion().size());

    }

    private void sendValidDocument() throws MalformedURLException {
        RecepcionComprobantes service = ReceptionServiceFactory.create(SRIEnvironment.TEST);
        RespuestaSolicitud response = service.validarComprobante(invoice);
    }


}
