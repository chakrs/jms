package io.net.jmscamel.camelrouting.routing;


import org.apache.camel.LoggingLevel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.net.jmscamel.camelrouting.Processors.MyProcessor;


@Component
public class MyOwnRouter extends RouteBuilder {
		
		@Autowired
		private MyProcessor myProcessor;
		
		
	    @Override
	    public void configure() throws Exception {
	    	
	    	from("{{outbound.endpoint}}")
	    	.routeId("myRouteId")
	    	.startupOrder(2)
	    	.process(myProcessor)	    	
	    	.to("jms:queue:OMY_QUEUE")
	    	.log(LoggingLevel.INFO, log, "Process ended");

	    }	
}

