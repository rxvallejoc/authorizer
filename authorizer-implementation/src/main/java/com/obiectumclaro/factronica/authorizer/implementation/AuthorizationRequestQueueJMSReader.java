package com.obiectumclaro.factronica.authorizer.implementation;

import com.obiectumclaro.factronica.authorizer.api.AuthorizationProcessor;
import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequest;
import org.apache.log4j.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
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
public class AuthorizationRequestQueueJMSReader implements MessageListener {

    private Logger log = Logger.getLogger(AuthorizationRequestQueueJMSReader.class);

    @Inject
    private AuthorizationProcessor processor;

    public AuthorizationRequestQueueJMSReader() { }

    public AuthorizationRequestQueueJMSReader(AuthorizationProcessor processor) {
        this();
        this.processor = processor;
    }

    @Override
    public void onMessage(Message message) {
        try {
            ObjectMessage om = (ObjectMessage)message;
            AuthorizationRequest request = (AuthorizationRequest) om.getObject();

            processor.process(request);
        } catch (JMSException e) {
            log.error(String.format("Error trying to retrieve and convert message %s to AuthorizationRequest", message), e);
        }
    }
}
