/**
 * 
 */
package net.neophyte.messaging.spring.jms.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.neophyte.messaging.spring.jms.constants.Constants;

/**
 * @author shuvro
 *
 */
public class DurableSubscriberApp {

	private static Logger logger = LoggerFactory.getLogger(DurableSubscriberApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final Object lock = new Object();
		ClassPathXmlApplicationContext appContext = null;
		try {
			logger.debug("SubscriberApp started...");
			appContext = new ClassPathXmlApplicationContext(Constants.DURABLE_PUBSUB_APP_CONTEXT_XML_NAME);
			while (true) {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException iee) {
						iee.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		} finally {
			if (appContext != null) {
				appContext.close();
			}
		}
		logger.debug("SubscriberApp exiting...");
		System.exit(0);
	}
}
