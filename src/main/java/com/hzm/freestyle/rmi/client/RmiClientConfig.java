package com.hzm.freestyle.rmi.client;

import com.hzm.freestyle.rmi.service.RmiServiceConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年11月18日
 */
@Configuration
public class RmiClientConfig {

	@Bean
	public RmiProxyFactoryBean myClient() {
		RmiProxyFactoryBean rmiProxyFactoryBean = new RmiProxyFactoryBean();
		rmiProxyFactoryBean.setServiceUrl("RMI://127.0.0.1:8888/" + RmiServiceConfig.SERVICE_NAME);
		rmiProxyFactoryBean.setServiceInterface(RmiService.class);
		return rmiProxyFactoryBean;
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RmiClientConfig.class);
		RmiService rmiService = context.getBean(RmiService.class);
		rmiService.say();
	}

}
