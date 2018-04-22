package cn.com.studyshop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ServiceConfig {
	private Logger logger = LoggerFactory.getLogger(ServiceConfig.class);

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		logger.debug("loadBanced");
		return new RestTemplate();
	}
}
