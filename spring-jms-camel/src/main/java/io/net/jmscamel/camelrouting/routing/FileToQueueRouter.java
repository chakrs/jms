package io.net.jmscamel.camelrouting.routing;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FileToQueueRouter extends RouteBuilder {
	    @Override
	    public void configure() throws Exception {    	
	    
	    	// Consumer end-point
	    	from("file:{{file.inbox}}")
	    	// camel in-built converting the data to text message prior sending to queue
	    	.convertBodyTo(String.class)
	    	// producer end-point
	    	.to("{{outbound.endpoint}}");
	    	
	    	//second router consumer end-point
	    	from("{{outbound.endpoint}}")	  
	    	// content based router EIP	    	 
	        .choice()
	        	.when(header("CamelFileName").endsWith(".txt"))
	        		.to("{{queueOut_1}}")
	        	.when()
	         	 	.simple("${body} contains 'gold'")
	         	 	.to("{{queueOut_2}}")
	         	.when()
	         	 	.simple("${body} contains 'silver'")
	         	 	.to("{{queueOut_4}}")
	         	.otherwise()
	         		.to("{{queueOut_3}}")
	        .endChoice();
	    	
	    }	

}
