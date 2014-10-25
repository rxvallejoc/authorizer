package com.obiectumclaro.factronica.authorizer.sri.client.wsdl;

import com.obiectumclaro.factronica.authorizer.sri.client.SRIResponseReception;
import com.obiectumclaro.factronica.authorizer.sri.client.factory.ReceptionServiceFactory;
import com.obiectumclaro.factronica.authorizer.sri.client.SRIEnvironment;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.reception.RecepcionComprobantes;
import com.obiectumclaro.factronica.authorizer.sri.client.wsdl.reception.RespuestaSolicitud;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import static org.junit.Assert.assertEquals;

/**
 * Created by fdelatorre on 07/09/14.
 */
public class TestReceptionSRI {

    private byte[] invoice;
    private byte[] invalidDocument;

    @Before
    public void initDocuments() throws IOException {
        InputStream is = ClassLoader.getSystemResourceAsStream("signedInvoice.xml");
        invoice = new byte[is.available()];
        is.read(invoice);

        invalidDocument = "this is not an xml file".getBytes();
    }

    @Test
    public void shouldReceiveInvoice() throws MalformedURLException {
        RecepcionComprobantes service = ReceptionServiceFactory.create(SRIEnvironment.TEST);
        RespuestaSolicitud response = service.validarComprobante(invoice);

        assertEquals(SRIResponseReception.RECEIVED.getDescription(), response.getEstado());
    }

    @Test
    public void shouldNotReceiveInvalidDocument() throws MalformedURLException {
        RecepcionComprobantes service = ReceptionServiceFactory.create(SRIEnvironment.TEST);
        RespuestaSolicitud response = service.validarComprobante(invalidDocument);

        assertEquals(SRIResponseReception.RETURNED.getDescription(), response.getEstado());
    }
}
