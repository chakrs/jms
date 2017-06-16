/**
 * 
 */
package net.neophyte.messaging.spring.jms.pubsub;

import javax.jms.Message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.neophyte.messaging.spring.jms.JMSTemplateWrapper;
import net.neophyte.messaging.spring.jms.config.AppConfigReader;
import net.neophyte.messaging.spring.jms.constants.Constants;

/**
 * @author shuvro
 *
 */
public class SubscriberApp {

	private static Logger logger = LoggerFactory.getLogger(SubscriberApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext = null;
		try {
			logger.debug("SubscriberApp started...");
			appContext = new ClassPathXmlApplicationContext(Constants.PUBSUB_APP_CONTEXT_XML_NAME);
			JMSTemplateWrapper jqs = (JMSTemplateWrapper) appContext.getBean(Constants.TOPIC_BEAN_ID);
			AppConfigReader acr = (AppConfigReader) appContext.getBean(Constants.APP_CONFIG_READER_BEAN_ID);
			String topicName = acr.getConfig(Constants.TOPIC_NAME_PROP);
			Message msg = null;
			while (true) {
				msg = jqs.receiveMessage(topicName);
				if (msg != null) {
					System.out.println("Received: " + msg);
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
