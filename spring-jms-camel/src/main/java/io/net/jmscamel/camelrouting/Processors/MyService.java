package io.net.jmscamel.camelrouting.Processors;

import org.springframework.stereotype.Service;

@Service
public class MyService {
	
	public void implementSomeCRUD(MyItem myItem){
		
		// connect to some dao layer or some database / backend system to persist the data 
		
		System.out.println("Posted the Item Details " +  myItem.getName() + " Calling other services");
		
	}

}
