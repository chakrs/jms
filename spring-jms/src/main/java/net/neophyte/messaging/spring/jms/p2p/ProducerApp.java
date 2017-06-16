/**
 * 
 */
package net.neophyte.messaging.spring.jms.p2p;

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
public class ProducerApp {

	private static Logger logger = LoggerFactory.getLogger(ProducerApp.class);

	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext = null;
		try {
			logger.debug("ProducerApp started...");
			appContext = new ClassPathXmlApplicationContext(Constants.P2P_APP_CONTEXT_XML_NAME);
			JMSTemplateWrapper jqs = (JMSTemplateWrapper) appContext.getBean(Constants.QUEUE_BEAN_ID);
			AppConfigReader acr = (AppConfigReader) appContext.getBean(Constants.APP_CONFIG_READER_BEAN_ID);
			String queueName = acr.getConfig(Constants.QUEUE_NAME_PROP);
			for (int i = 0; i < 10; i++) {
				jqs.sendTextMessage(queueName, Message.DEFAULT_PRIORITY, "msg: " + i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (appContext != null) {
				appContext.close();
			}
		}
		logger.debug("ProducerApp exiting...");
		System.exit(0);
	}
}
