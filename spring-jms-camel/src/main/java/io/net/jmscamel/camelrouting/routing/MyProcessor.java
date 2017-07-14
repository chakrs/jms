package io.net.jmscamel.camelrouting.routing;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MyProcessor implements Processor{
	
	@Autowired
	private ObjectMapper localMapper;
	
	@Autowired
	private JsonParseUtility parseUtility;

	@Autowired
	private MyService myservice;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Message message = exchange.getIn();
		MyItem myItem = parseUtility.getParseFromMessage(message, MyItem.class);
		
	//	myservice.implementSomeCRUD(myItem);
		
		
		
		
		
		
		
	}
	

}
