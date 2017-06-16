/**
 * 
 */
package net.neophyte.messaging.spring.jms.p2p;

import java.util.concurrent.atomic.AtomicInteger;

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
public class BrowserApp {
	private static Logger logger = LoggerFactory.getLogger(BrowserApp.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext appContext = null;
		try {
			logger.debug("BrowserApp started...");
			appContext = new ClassPathXmlApplicationContext(Constants.P2P_APP_CONTEXT_XML_NAME);
			JMSTemplateWrapper jqs = (JMSTemplateWrapper) appContext.getBean(Constants.QUEUE_BEAN_ID);
			AppConfigReader acr = (AppConfigReader) appContext.getBean(Constants.APP_CONFIG_READER_BEAN_ID);
			String queueName = acr.getConfig(Constants.QUEUE_NAME_PROP);
			AtomicInteger count = new AtomicInteger(5);
			jqs.browseMessage(queueName, count);
			while (count.get() >= 0) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException iee) {
					iee.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (appContext != null) {
				appContext.close();
			}
		}
		logger.debug("BrowserApp exiting...");
		System.exit(0);
	}
}
