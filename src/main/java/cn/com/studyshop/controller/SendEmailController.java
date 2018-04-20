package cn.com.studyshop.controller;

import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEmailController {

	private Logger logger = LoggerFactory.getLogger(SendEmailController.class);

	@Autowired
	private DiscoveryClient client;

	@RequestMapping("/send/email")
	public String sendEmail() {

		try {
			System.out.println(client.getInstances("email-service").get(0).getUri().toURL().toString());

			logger.debug("service url:->{}", client.getInstances("compute-service").get(0).getUri().toURL().toString());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("Send email out!");
		logger.info("Send email out!");
		return "Send email out!";
	}
}
