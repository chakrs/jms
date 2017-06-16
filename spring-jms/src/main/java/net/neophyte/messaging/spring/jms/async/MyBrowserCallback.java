package net.neophyte.messaging.spring.jms.async;

import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.BrowserCallback;

/**
 * @author shuvro
 * 
 * A message browser callback class, implements the BrowserCallback
 * interface to override the doInJms method to browse message in async
 * mode
 */
public class MyBrowserCallback implements BrowserCallback<Object> {
	private static Logger logger = LoggerFactory.getLogger(MyBrowserCallback.class);
	AtomicInteger msgCount = null;

	public MyBrowserCallback(AtomicInteger msgCount) {
		this.msgCount = msgCount;
	}

	@Override
	public Object doInJms(Session session, QueueBrowser qb) throws JMSException {
		@SuppressWarnings("unchecked")
		Enumeration<Message> messagesStream = (Enumeration<Message>) qb.getEnumeration();

		Message current;
		while (messagesStream.hasMoreElements()) {
			try {
				current = messagesStream.nextElement();
				msgCount.decrementAndGet();
				if (current instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) current;
					String text = textMessage.getText();
					System.out.println("Browsed: " + text);
				}
			} catch (Exception e) {
				logger.debug(e.getMessage());
			}
		}
		return null;
	}
}
