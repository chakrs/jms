package io.net.jmscamel.camelrouting.routing;


import org.apache.camel.LoggingLevel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.net.jmscamel.camelrouting.Processors.ItemProcessor;


@Component
public class CamelComponentsRouter extends RouteBuilder {
		
		@Autowired
		private ItemProcessor itemProcessor;
				
	    @Override
	    public void configure() throws Exception {
	    	
	    	from("{{outbound.endpoint}}")
	    	// Naming the router
	    	.routeId("{{item.processorID}}")
	    	// Specify the router order to startup
	    	.startupOrder(2)
	    	// To further process the data. can do inline processing or use a separate class
	    	.process(itemProcessor)	    	
	    	.to("{{itemQueue}}")
	    	.log(LoggingLevel.INFO, log, "Process ended");

	    }	
}

