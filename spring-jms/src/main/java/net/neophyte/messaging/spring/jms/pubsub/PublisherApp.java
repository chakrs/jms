/**
 * 
 */
package net.neophyte.messaging.spring.jms.pubsub;

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
public class PublisherApp {

	private static Logger logger = LoggerFactory.getLogger(PublisherApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext = null;
		try {
			logger.debug("PublisherApp started...");
			appContext = new ClassPathXmlApplicationContext(Constants.PUBSUB_APP_CONTEXT_XML_NAME);
			JMSTemplateWrapper jqs = (JMSTemplateWrapper) appContext.getBean(Constants.TOPIC_BEAN_ID);
			AppConfigReader acr = (AppConfigReader) appContext.getBean(Constants.APP_CONFIG_READER_BEAN_ID);
			String topicName = acr.getConfig(Constants.TOPIC_NAME_PROP);
			for (int i = 0; i < 10; i++) {
				String msg = "msg: " + i;
				jqs.sendTextMessage(topicName, Constants.MSG_DEFAULT_PRIORITY, msg);
				System.out.println("Sent: " + msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (appContext != null) {
				appContext.close();
			}
		}
		logger.debug("PublisherApp exiting...");
		System.exit(0);
	}
}
