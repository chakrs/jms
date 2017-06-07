package net.neophyte.messaging.jms;
/**
 * 
 * @author shuvro
 *
 */
public abstract class Configuration {

	public static final int IGNORE = -1;
	private static final String brokerUrl = "tcp://localhost:32780";
	private static final String userId = "admin";
	private static final String password = "admin";
	private static final String queueName = "TEST.QUEUE";
	private static final String topicName = "TEST.TOPIC";
	private final static int MSG_SIZE_IN_BYTES = 1024;// 1KB
	private final static int messageCount = 100;
	private final static long runTime = IGNORE;//1 minute
	
	public static String getBrokerurl() {
		return brokerUrl;
	}
	public static String getUserid() {
		return userId;
	}
	public static String getPassword() {
		return password;
	}
	public static String getQueueName() {
		return queueName;
	}
	public static String getTopicName() {
		return topicName;
	}
	public static int getMessageSize() {
		return MSG_SIZE_IN_BYTES;
	}
	public static int getMessageCount() {
		return messageCount;
	}
	public static long getRuntime() {
		return runTime;
	}
}
