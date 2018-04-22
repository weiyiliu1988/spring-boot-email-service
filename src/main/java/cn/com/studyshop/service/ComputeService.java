package cn.com.studyshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ComputeService {

	private Logger logger = LoggerFactory.getLogger(ComputeService.class);

	/**
	 * ribbon 均衡特性利用
	 * 
	 */
	@Value("${compute.service.uri}")
	private String computeServiceUri;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * 熔断器
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "addFallBack")
	public Integer add(int a, int b) {

		String requestURL = computeServiceUri + "/add?a=" + a + "&b=" + b + "&accessToken='adasd'";
		logger.debug("服务uri {} 请求地址:{}", computeServiceUri, requestURL);
		return restTemplate.getForEntity(requestURL, Integer.class).getBody();

	}

	public Integer addFallBack(int a, int b) {
		logger.debug("[熔断器激活 返回999] a={},b={}", a, b);
		return 999;
	}
}
