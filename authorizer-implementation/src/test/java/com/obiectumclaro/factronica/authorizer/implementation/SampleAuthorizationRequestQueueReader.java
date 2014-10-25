package com.obiectumclaro.factronica.authorizer.implementation;

import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequest;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Created by fdelatorre on 04/09/14.
 */
@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/FactronicaAuthorization")
})
public class SampleAuthorizationRequestQueueReader implements MessageListener {

    static String requestId;

    @Override
    public void onMessage(Message message) {

        try {
            ObjectMessage om = (ObjectMessage)message;
            AuthorizationRequest request = (AuthorizationRequest) om.getObject();
            this.requestId = request.getId();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
