package com.obiectumclaro.factronica.authorizer.sri.client.util;

import com.obiectumclaro.factronica.authorizer.sri.client.util.exception.InvalidXMLDocumentException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by fdelatorre on 07/09/14.
 */
public class TestSRIDocumentInfoFinder {

    private static final String ACCESS_KEY = "0709201401179193142400110010015075634501410112112";

    private byte[] invoice;

    @Before
    public void initDocuments() throws IOException {
        InputStream is = ClassLoader.getSystemResourceAsStream("signedInvoice.xml");
        invoice = new byte[is.available()];
        is.read(invoice);
    }

    @Test
    public void shouldFindValueIntoInvoice() throws InvalidXMLDocumentException {

        SRIDocumentInfoFinder helper = new SRIDocumentInfoFinder(invoice);
        String accessKey = helper.getAccessKey();

        assertEquals(ACCESS_KEY, accessKey);
    }
}
