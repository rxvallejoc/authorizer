package com.obiectumclaro.factronica.authorizer.sri.client.util;

import com.obiectumclaro.factronica.authorizer.sri.client.util.exception.InvalidXMLDocumentException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by fdelatorre on 07/09/14.
 */
public class SRIDocumentInfoFinder {

    private String accessKey;
    private byte[] document;
    private final Document xmlDocument;

    public SRIDocumentInfoFinder(byte[] document) throws InvalidXMLDocumentException {

        this.document = document;
        xmlDocument = createXMLDocument();
    }

    public String getAccessKey() {
        if(accessKey == null){
            accessKey = find("/*/infoTributaria/claveAcceso");
        }
        return accessKey;
    }

    private String find(String path){
        try {
            XPathFactory xFactory = XPathFactory.newInstance();
            XPath xpath = xFactory.newXPath();
            XPathExpression expression = xpath.compile(path);

            return (String) expression.evaluate(xmlDocument, XPathConstants.STRING);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }

    private Document createXMLDocument() throws InvalidXMLDocumentException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        docFactory.setNamespaceAware(true);
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.parse(new InputSource(new ByteArrayInputStream(document)));
        } catch (ParserConfigurationException |  SAXException | IOException e) {
            throw new InvalidXMLDocumentException(e);
        }
    }


}
