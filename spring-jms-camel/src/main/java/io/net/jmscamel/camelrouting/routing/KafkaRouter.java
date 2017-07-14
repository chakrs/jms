package io.net.jmscamel.camelrouting.routing;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaRouter extends RouteBuilder
{
	@Value ("${kafka.location}")
	private String Location;
	
	private String Topic;
	private String Group;
	private String ZooKeeper;
	@Value ("${kafka.readRemote:true}")
	private Boolean ReadRemote;
	
	@Autowired
	private MyProcessor myProcessor;
	
	 @Override
	    public void configure() throws Exception {
		 
		// from("file:" + location + "?file:" + Topic +"file:" + location +"ZooKeeper:" + location +"Group:" + location + "");
		 
		 
	 }
	
	
	

}
