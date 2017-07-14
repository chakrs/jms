package io.net.jmscamel.camelrouting.Processors;

public class MyService {
	
	public void implementSomeCRUD(MyItem myItem){
		
		// connect to some dao layer to some database / legacy system to persist the data 
		
		System.out.println("Posted the Item Details" +  myItem.toString());
		
	}

}
