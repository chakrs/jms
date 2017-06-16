package net.neophyte.messaging.spring.jms.async;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 
 * @author shuvro
 *
 */
public class MyMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			try {
				System.out.println("Received: " + ((TextMessage) message).getText());
			} catch (JMSException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}
}