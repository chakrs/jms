package io.net.jmscamel.camelrouting.routing;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.net.jmscamel.camelrouting.Processors.JsonParseUtility;
import io.net.jmscamel.camelrouting.Processors.MyItem;
import io.net.jmscamel.camelrouting.Processors.MyProcessor;
import io.net.jmscamel.camelrouting.Processors.MyService;

@Component
public class MyOwnRouter extends RouteBuilder {
		
		@Autowired
		private MyProcessor myProcessor;
		
		@Autowired
		private JsonParseUtility parseUtility;

		@Autowired
		private MyService myservice;
		
	    @Override
	    public void configure() throws Exception {
	    	
	    	from("{{inbound.endpoint}}")
	    	.routeId("myRouteId")
	    	/*.process(myProcessor)
	    	/*.end()	    	
	    	.log(LoggingLevel.INFO, log, "Process ended");*/
	    	
	    	.process(new Processor(){
				
				public void process(Exchange exchange)throws Exception{
					log.info("Exchange {}", exchange.getIn());
					Message message = exchange.getIn();
					MyItem myItem = parseUtility.getParseFromMessage(message, MyItem.class);
		
					myservice.implementSomeCRUD(myItem);

				}
			})
	    	.to("{{outbound.endpoint}}");

	    }	
}

