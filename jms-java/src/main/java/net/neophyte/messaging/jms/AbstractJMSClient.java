package net.neophyte.messaging.jms;

import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author shuvro
 *
 */
public abstract class AbstractJMSClient {
	protected final Logger logger = LoggerFactory.getLogger(AbstractJMSClient.class);
	protected AtomicInteger msgs = new AtomicInteger(0);
	protected AtomicInteger errors = new AtomicInteger(0);
	protected long startTime = 0;
	
	protected boolean runTimeRemains(long runTime) {
		if (runTime == Configuration.IGNORE) {
			return false;
		}
		return (System.currentTimeMillis() - startTime) < runTime;
	}

	private boolean moreMessagesToCount(long messageCount) {
		if (messageCount == Configuration.IGNORE) {
			return false;
		}
		return msgs.get() < messageCount;
	}

	protected boolean moreMessagesToBrowse(long messageCount) {
		return moreMessagesToCount(messageCount);
	}
	
	protected boolean moreMessagesToReceive(long messageCount) {
		return moreMessagesToCount(messageCount);
	}
	
	protected boolean moreMessagesToProduce(long messageCount) {
		return moreMessagesToCount(messageCount);
	}
	
    protected class SimpleMessageListener implements MessageListener {

		public SimpleMessageListener() {}

		@Override
        public void onMessage(Message msg) {
        	try {
	            msgs.incrementAndGet();
	            if (msg instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) msg;
					System.out.println("Received: " + textMessage.getText());
				}
        	}catch(Exception e){
    	        errors.incrementAndGet();
    	        logger.info(e.getMessage());
    	    }
        }
    }
}
