package net.neophyte.messaging.spring.jms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * 
 * @author shuvro
 *
 */
@Configuration
@PropertySource("classpath:config.properties")
public class AppConfigReader {
	@Autowired
	Environment env;

	public String getConfig(String key) {
		return env.getProperty(key);
	}
}
