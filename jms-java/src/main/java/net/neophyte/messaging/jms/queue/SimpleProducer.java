package net.neophyte.messaging.jms.queue;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import net.neophyte.messaging.jms.AbstractJMSClient;
import net.neophyte.messaging.jms.Configuration;
import net.neophyte.messaging.jms.SimpleConnectionProvider;
import net.neophyte.messaging.jms.Utils.MessageUtil;
import net.neophyte.messaging.jms.Utils.Util;

/**
 * A simple JMS message producer
 * 
 * @author shuvro
 *
 */
public class SimpleProducer extends AbstractJMSClient {

	public static void main(String[] arg) {
		SimpleProducer pc = new SimpleProducer();
		System.out.println("-Calling sendMessage-");
		pc.sendMessages(Configuration.getMessageCount(),
				Configuration.getRuntime());
		System.exit(0);
	}

	private void sendMessages(long numOfMessages, long runTime) {
		Session session = null;
		MessageProducer msgSender = null;
		startTime = System.currentTimeMillis();
		try {
			SimpleConnectionProvider.createConnectionInstance(
					Configuration.getBrokerurl(), Configuration.getUserid(),
					Configuration.getPassword());
			session = SimpleConnectionProvider
					.getSession(Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(Configuration
					.getQueueName());
			msgSender = session.createProducer(destination);
			msgSender.setDeliveryMode(DeliveryMode.PERSISTENT);
			TextMessage message = null;
			while (runTimeRemains(runTime)
					|| moreMessagesToProduce(numOfMessages)) {
				try {
					message = session.createTextMessage(MessageUtil
							.generateMessage(Configuration.getMessageSize()));
					msgSender.send(message);
					msgs.incrementAndGet();
					System.out.println("Produced: " + message);
				} catch (Exception e) {
					errors.incrementAndGet();
					logger.info(e.getMessage());
				}
			}
			long totalRunTime = System.currentTimeMillis() - startTime;
			logger.info("Total run time: "
					+ Util.getHh_Mm_Ss_ssss_Time(totalRunTime)
					+ ", Total messages produced: " + msgs.get());
			logger.info("Total errors: " + errors.get());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			SimpleConnectionProvider.closeConnection();
		}
	}
}