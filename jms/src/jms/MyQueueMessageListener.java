/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jms;

import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import javax.jms.*;

/**
 *
 * @author Administrator
 */
public class MyQueueMessageListener implements MessageListener{
    
    private static final Log LOG = LogFactory.getLog(MyQueueMessageListener.class);
    /**
    *
    */
    public MyQueueMessageListener() {
    // TODO Auto-generated constructor stub
    }

    
    public void onMessage(Message arg0) {
        LOG.info("onMessage() called!");
        if(arg0 instanceof TextMessage){
            try {
                //Print it out
                System.out.println("Recieved message in listener: " + ((TextMessage)arg0).getText());

                System.out.println("Co-Rel Id: " + ((TextMessage)arg0).getJMSCorrelationID());
                try {
                    //Log it to a file
                    BufferedWriter outFile = new BufferedWriter(new FileWriter("MyQueueConsumer.txt"));
                    outFile.write("Recieved message in listener: " + ((TextMessage)arg0).getText());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (JMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else{
            System.out.println("~~~~Listener : Error in message format~~~~");
        }

    }

    }
    

