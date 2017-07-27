package io.net.jmscamel.camelrouting.Processors;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemProcessor implements Processor{
	
	
	@Autowired
	private JsonParseUtility parseUtility;

	@Autowired
	private ItemService myservice;
	
		
	@Override
	public void process(Exchange exchange) throws Exception {
				
		Message message = exchange.getIn();
		Item myItem = parseUtility.getParseFromString(message.getBody(String.class), Item.class);
		//MyItem myItem = parseUtility.getParseFromMessage(message, MyItem.class);
		//MyItem myItem = parseUtility.getParseFromObject(message.getBody(), MyItem.class);		
		myservice.implementSomeCRUD(myItem);
		
	}
	

}
