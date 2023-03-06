package com.hzm.freestyle.rmi.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年11月18日
 */
@Configuration
@ComponentScan("com.hzm.freestyle.rmi.service")
public class RmiServiceConfig {

	public static final String SERVICE_NAME = "helloService";

//	@Bean
	public RmiServiceExporter myService(RmiService rmiService) {
		RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
		rmiServiceExporter.setService(rmiService);
		rmiServiceExporter.setServiceName(SERVICE_NAME);
		rmiServiceExporter.setServiceInterface(RmiService.class);
		rmiServiceExporter.setRegistryPort(8888);
		return rmiServiceExporter;
	}

}
