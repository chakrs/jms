package net.neophyte.messaging.spring.jms;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import net.neophyte.messaging.spring.jms.async.MyBrowserCallback;

/**
 * @author shuvro
 *
 */
public class JMSTemplateWrapper {

	@Autowired
	private JmsTemplate jmsTemplate;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setPubSubDomain(boolean pubSubDomain) {
		this.jmsTemplate.setPubSubDomain(pubSubDomain);
	}

	public boolean getPubSubDomain() {
		return this.jmsTemplate.isPubSubDomain();
	}

	public void browseMessage(String destinationName, AtomicInteger msgCount) throws JMSException {
		jmsTemplate.browse(destinationName, new MyBrowserCallback(msgCount));
	}

	public Message receiveMessage(String destinationName) throws JMSException {
		return jmsTemplate.receive(destinationName);
	}

	public void sendTextMessage(String destinationName, int priority, String textMsg) throws JMSException {
		jmsTemplate.send(destinationName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage txtMessage = session.createTextMessage(textMsg);
				txtMessage.setJMSPriority(priority);
				return txtMessage;
			}
		});
	}

	public void sendMapMessage(String destinationName, int priority, HashMap<String, String> map) throws JMSException {
		jmsTemplate.send(destinationName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setJMSPriority(priority);
				for (String key : map.keySet()) {
					mapMessage.setString(key, map.get(key));
				}
				return mapMessage;
			}
		});
	}
}