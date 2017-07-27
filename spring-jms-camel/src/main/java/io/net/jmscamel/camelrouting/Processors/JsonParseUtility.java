package io.net.jmscamel.camelrouting.Processors;

import org.apache.camel.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.Reader; 
import java.io.StringReader;
import java.io.File;
import java.io.IOException;

@Component
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
		String actualMessage = localMapper.writeValueAsString(obj);
		parsed =localMapper.readValue(actualMessage, clazz);
		return (T) parsed;
	}
	
	public <T> T getParseFromString(String str,Class<T> clazz)throws Exception{
		Object parsed = null;
		Reader actualMessage = new StringReader(str);		
		parsed =localMapper.readValue(actualMessage, clazz);
		return (T) parsed;
	}
	
	
	
	
	

}
