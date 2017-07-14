package io.net.jmscamel.camelrouting.routing;

import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Reader; 
import java.io.StringReader;
import java.io.IOException;



public class JsonParseUtility {
	
	@Autowired
	private ObjectMapper localMapper; 
	
	public <T> T getParseFromMessage(Message msg,Class<T> clazz)throws IOException{
		Object parsed = null;
		byte[] bytes = (byte[])msg.getBody();
		String actualMessage = new String(bytes);
		parsed =localMapper.readValue(actualMessage, clazz);
		return (T) parsed;
	}
	
	public <T> T getParseFromObject(Object obj,Class<T> clazz)throws Exception{
		Object parsed = null;
		//byte[] bytes = (byte[])msg.getBody();
		//String actualMessage = new String(bytes);
		
		String actualMessage = localMapper.writeValueAsString(obj);
		parsed =localMapper.readValue(actualMessage, clazz);
		return (T) parsed;
	}
	
	public <T> T getParseFromString(String str,Class<T> clazz)throws Exception{
		Object parsed = null;
		//byte[] bytes = (byte[])msg.getBody();
		//String actualMessage = new String(bytes);
		Reader actualMessage = new StringReader(str);
	//	String actualMessage = localMapper.writeValueAsString(obj);
		parsed =localMapper.readValue(actualMessage, clazz);
		return (T) parsed;
	}
	
	
	
	
	

}
