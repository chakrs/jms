package io.net.jmscamel.camelrouting.routing;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JmsRoute extends RouteBuilder {

	
	static final Logger log = LoggerFactory.getLogger(JmsRoute.class);
	
	@Override
	public void configure() throws Exception {
		
		from("{{inbound.endpoint}}")
		.transacted()
		.log(LoggingLevel.INFO, log, "Received Message")
		.startupOrder(1)
		.loop()
		// it loops for given number of times  
		.simple("{{outbound.loop.count}}")
		// Multicast EIP  -- here we are multicasting to different endpoints like jms and file components endpoints
		.multicast()  
		.to("{{queueOut_1}}","{{queueOut_2}}", "{{queueOut_3}}", "jms:someQueue", "file:{{file.outbox}}")		
		.log(LoggingLevel.INFO, log, "Message sent. Iteration: ${property.CamelLoopIndex}")
		.end();
		
		//Constructing ReceipientList EIP - similar to Mutlicast but recipientList can be constructed dynamically 		
		from("{{queueOut_1}}")
		.process(new Processor() {
			public void process(Exchange exchange) throws Exception {
				String recipients = "jms:accounting";
				String customer = exchange.getIn().getBody(String.class);				
				if (customer.equalsIgnoreCase("gold")) {
					recipients += ",jms:production";
					}
					exchange.getIn().setHeader("recipients", recipients);
				}
			})
		//ReceipientList EIP 
		.recipientList(header("recipients"));
	}

}
