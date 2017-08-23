/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

/**
 *
 * @author Administrator
 */

import javax.jms.*;
import javax.naming.Context;
import javax.jms.ConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class jmsproducer implements Runnable {
    
    private static final Log LOG = LogFactory.getLog(jmsproducer.class);

public jmsproducer() {
}

//Run method implemented to run this as a thread.
public void run(){
Context jndiContext = null;
ConnectionFactory connectionFactory = null;
Connection connection = null;
Session session = null;
Destination destination = null;
MessageProducer producer = null;
String destinationName = null;
final int numMsgs; 
destinationName = "MyQueue";
numMsgs = 5;
LOG.info("Destination name is " + destinationName);

/*
* Create a JNDI API InitialContext object
*/
try {
    jndiContext = new InitialContext();
} catch (NamingException e) {
    LOG.info("Could not create JNDI API context: " + e.toString());
    System.exit(1);
}

/*
* Look up connection factory and destination.
*/
try {
    connectionFactory = (ConnectionFactory)jndiContext.lookup("queueConnectionFactory");
    destination = (Destination)jndiContext.lookup(destinationName);
} catch (NamingException e) {
    LOG.info("JNDI API lookup failed: " + e);
    System.exit(1);
}

/*
* Create connection. Create session from connection; false means
* session is not transacted.create producer, set the text message, set the co-relation id and send the message.
*/


try {
        connection = connectionFactory.createConnection();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage();
            
        for (int i = 0; i < numMsgs; i++) {
            message.setText("This is message " + (i + 1));
            LOG.info("Sending message: " + message.getText());
            producer.send(message);
            }
        
         producer.send(session.createMessage());
        } 
        catch (JMSException e) {
            LOG.info("Exception occurred: " + e);
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } 
                catch (JMSException ignored) {}
            }
}
}
    
}