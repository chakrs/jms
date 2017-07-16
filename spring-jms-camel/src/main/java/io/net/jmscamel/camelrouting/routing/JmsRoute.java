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
		// TODO Auto-generated method stub
		from("{{inbound.endpoint}}")
		.transacted()
		.log(LoggingLevel.INFO, log, "Received Message")
		.startupOrder(1)
		.process(new Processor(){
			
			public void process(Exchange exchange)throws Exception{
				log.info("Exchange {}", exchange.getIn());

			}
		})
				
		.loop()
		.simple("{{outbound.loop.count}}")
		.to("{{outbound.endpoint}}")
		.log(LoggingLevel.INFO, log, "Message sent. Iteration: ${property.CamelLoopIndex}")
		.end();		
	}

}
