package com.obiectumclaro.factronica.authorizer.implementation;

import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequestQueue;
import com.obiectumclaro.factronica.authorizer.api.AuthorizationRequest;

import javax.annotation.Resource;
import javax.jms.*;

/**
 * Created by fdelatorre on 04/09/14.
 */

public class AuthorizationRequestQueueJMSProducer implements AuthorizationRequestQueue {

    @Resource(lookup = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "java:/jms/queue/FactronicaAuthorization")
    private Queue authorizationQueue;

    @Override
    public void push(AuthorizationRequest request) {

        Connection connection = null;
        Session session = null;
        MessageProducer producer = null;

        try {
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(authorizationQueue);

            ObjectMessage message = session.createObjectMessage(request);
            producer.send(message);

        } catch (final JMSException e) {
            throw new RuntimeException("Could not deliver document authorization request to the outgoing queue", e);
        } finally {
            closeResources(producer, session, connection);
        }
    }

    private void closeResources(MessageProducer producer, Session session, Connection connection) {
        try {
            if (producer != null)
                producer.close();
            if(session != null)
                session.close();
            if(connection != null)
                connection.close();
        } catch (JMSException e){
            throw  new RuntimeException(e);
        }
    }
}
