package com.obiectumclaro.factronica.authorizer.implementation;

import com.obiectumclaro.factronica.authorizer.api.AuthorizationProcessor;
import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequest;
import org.junit.Before;
import org.junit.Test;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import java.io.Serializable;
import java.util.Enumeration;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by fdelatorre on 04/09/14.
 */
public class TestAuthorizationRequestQueueReader {

    private AuthorizationRequest request;
    private AuthorizationProcessor authorizer;

    @Before
    public void prepareRequest(){
        request = new AuthorizationRequest();
        authorizer = mock(AuthorizationProcessor.class);
    }

    @Test
    public void shouldSendRequestToProcess(){
        AuthorizationRequestQueueJMSReader reader = new AuthorizationRequestQueueJMSReader(authorizer);
        Message message = new JMSMessage();

        reader.onMessage(message);

        verify(authorizer).process(request);
    }

    @Test
    public void shouldConstructWithoutParametersForEJB(){
        assertNotNull(new AuthorizationRequestQueueJMSReader());
    }

    private class JMSMessage implements ObjectMessage {


        @Override
        public void setObject(Serializable serializable) throws JMSException {

        }

        @Override
        public Serializable getObject() throws JMSException {
            return request;
        }

        @Override
        public String getJMSMessageID() throws JMSException {
            return null;
        }

        @Override
        public void setJMSMessageID(String s) throws JMSException {

        }

        @Override
        public long getJMSTimestamp() throws JMSException {
            return 0;
        }

        @Override
        public void setJMSTimestamp(long l) throws JMSException {

        }

        @Override
        public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
            return new byte[0];
        }

        @Override
        public void setJMSCorrelationIDAsBytes(byte[] bytes) throws JMSException {

        }

        @Override
        public void setJMSCorrelationID(String s) throws JMSException {

        }

        @Override
        public String getJMSCorrelationID() throws JMSException {
            return null;
        }

        @Override
        public Destination getJMSReplyTo() throws JMSException {
            return null;
        }

        @Override
        public void setJMSReplyTo(Destination destination) throws JMSException {

        }

        @Override
        public Destination getJMSDestination() throws JMSException {
            return null;
        }

        @Override
        public void setJMSDestination(Destination destination) throws JMSException {

        }

        @Override
        public int getJMSDeliveryMode() throws JMSException {
            return 0;
        }

        @Override
        public void setJMSDeliveryMode(int i) throws JMSException {

        }

        @Override
        public boolean getJMSRedelivered() throws JMSException {
            return false;
        }

        @Override
        public void setJMSRedelivered(boolean b) throws JMSException {

        }

        @Override
        public String getJMSType() throws JMSException {
            return null;
        }

        @Override
        public void setJMSType(String s) throws JMSException {

        }

        @Override
        public long getJMSExpiration() throws JMSException {
            return 0;
        }

        @Override
        public void setJMSExpiration(long l) throws JMSException {

        }

        @Override
        public int getJMSPriority() throws JMSException {
            return 0;
        }

        @Override
        public void setJMSPriority(int i) throws JMSException {

        }

        @Override
        public void clearProperties() throws JMSException {

        }

        @Override
        public boolean propertyExists(String s) throws JMSException {
            return false;
        }

        @Override
        public boolean getBooleanProperty(String s) throws JMSException {
            return false;
        }

        @Override
        public byte getByteProperty(String s) throws JMSException {
            return 0;
        }

        @Override
        public short getShortProperty(String s) throws JMSException {
            return 0;
        }

        @Override
        public int getIntProperty(String s) throws JMSException {
            return 0;
        }

        @Override
        public long getLongProperty(String s) throws JMSException {
            return 0;
        }

        @Override
        public float getFloatProperty(String s) throws JMSException {
            return 0;
        }

        @Override
        public double getDoubleProperty(String s) throws JMSException {
            return 0;
        }

        @Override
        public String getStringProperty(String s) throws JMSException {
            return null;
        }

        @Override
        public Object getObjectProperty(String s) throws JMSException {
            return null;
        }

        @Override
        public Enumeration getPropertyNames() throws JMSException {
            return null;
        }

        @Override
        public void setBooleanProperty(String s, boolean b) throws JMSException {

        }

        @Override
        public void setByteProperty(String s, byte b) throws JMSException {

        }

        @Override
        public void setShortProperty(String s, short i) throws JMSException {

        }

        @Override
        public void setIntProperty(String s, int i) throws JMSException {

        }

        @Override
        public void setLongProperty(String s, long l) throws JMSException {

        }

        @Override
        public void setFloatProperty(String s, float v) throws JMSException {

        }

        @Override
        public void setDoubleProperty(String s, double v) throws JMSException {

        }

        @Override
        public void setStringProperty(String s, String s2) throws JMSException {

        }

        @Override
        public void setObjectProperty(String s, Object o) throws JMSException {

        }

        @Override
        public void acknowledge() throws JMSException {

        }

        @Override
        public void clearBody() throws JMSException {

        }
    }
}
