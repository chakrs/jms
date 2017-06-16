package net.neophyte.messaging.spring.jms.constants;

/**
 * 
 * @author shuvro
 *
 */
public interface Constants {

	final String P2P_APP_CONTEXT_XML_NAME = "p2pApplicationContext.xml";
	final String PUBSUB_APP_CONTEXT_XML_NAME = "pubSubApplicationContext.xml";
	final String DURABLE_PUBSUB_APP_CONTEXT_XML_NAME = "durablePubSubApplicationContext.xml";
	final String QUEUE_BEAN_ID = "jmsQueue";
	final String TOPIC_BEAN_ID = "jmsTopic";
	final String APP_CONFIG_READER_BEAN_ID = "appConfigReader";
	final String QUEUE_NAME_PROP = "queue.name";
	final String TOPIC_NAME_PROP = "topic.name";
	final int MSG_MIN_PRIORITY = 0;
	final int MSG_DEFAULT_PRIORITY = 4;
	final int MSG_MAX_PRIORITY = 9;
}
