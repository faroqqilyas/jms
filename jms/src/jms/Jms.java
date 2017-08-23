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
public class Jms {

    
    //Run the producer first, then the consumer
    public static void main(String[] args)  {
        runInNewthread(new jmsproducer());
        runInNewthread(new jmsconsumer()); 
    }

    public static void runInNewthread(Runnable runnable) {
        Thread brokerThread = new Thread(runnable);
        brokerThread.setDaemon(false);
        brokerThread.start();
    }
    
}
