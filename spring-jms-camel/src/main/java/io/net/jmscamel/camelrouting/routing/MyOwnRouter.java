package io.net.jmscamel.camelrouting.routing;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyOwnRouter extends RouteBuilder {
		
		
		String queueIn = "jms:TEST.QUEUE";
		
		String queueOut_1 = "jms:queue:OUT_1";
		
		String queueOut_2 = "jms:queue:OUT_2";
		
		String queueOut_3 = "jms:queue:OUT_3";
		
	    @Override
	    public void configure() throws Exception {
	    	        
	    	
	    	//String dirSource = "C:/temp/Camel/camelinaction-master/chapter7/file/src/main/resources/";
	    	String dirSource = "C:/camelInputFolder/";
	    	//String dirTarget = "C:/camelOutputFolder/";
	    	String dirTarget = dirSource + "target/";
	    	String fileName = "myJson.txt";
	    	String fromEndpoint = String.format("file://%s?fileName=%s&noop=true", dirSource, fileName);
	    	String toEndpoint = String.format("file://%s?fileName=%s", dirTarget, fileName);
	    	//from(fromEndpoint).to(toEndpoint);
	    	
	    		    	
	    	
	    	from("file://C:/camelInputFolder/").process(new Processor() {
	    		  public void process(Exchange exchange) throws Exception {
	    		    Object body = exchange.getIn().getBody();
	    		    
	    		    // do some business logic with the input body
	    		  }
	    		});	    	
	    
	    	from("file://C:/camelInputFolder/").convertBodyTo(String.class).to("jms:TEST.QUEUE");
	    	
	    	from(queueIn)
	        .choice()
	        	.when()
	        		.simple("${body} contains 'activemq'")
	        		.to(queueOut_1)
	        	.when()
	         	 	.simple("${body} contains 'mqseries'")
	         	 	.to(queueOut_2)
	         	.otherwise()
	         		.to(queueOut_3)
	        .endChoice();
	    }	

}
