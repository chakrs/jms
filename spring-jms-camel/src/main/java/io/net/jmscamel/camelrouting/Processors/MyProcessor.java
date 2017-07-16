package io.net.jmscamel.camelrouting.Processors;

import java.io.Reader;
import java.io.StringReader;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.databind.ObjectMapper;
@Component
public class MyProcessor implements Processor{
	
	
	@Autowired
	private JsonParseUtility parseUtility;

	@Autowired
	private MyService myservice;
	
	@Autowired
	private ObjectMapper localMapper; 
	
	@Override
	public void process(Exchange exchange) throws Exception {
				
		Message message = exchange.getIn();
		MyItem myItem = parseUtility.getParseFromString(message.getBody(String.class), MyItem.class);
		//MyItem myItem = parseUtility.getParseFromMessage(message, MyItem.class);
		//MyItem myItem = parseUtility.getParseFromObject(message.getBody(), MyItem.class);		
		myservice.implementSomeCRUD(myItem);
		
	}
	

}
