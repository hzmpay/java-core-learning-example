package com.hzm.freestyle.rmi.service;

import org.springframework.stereotype.Service;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年11月18日
 */
@Service
public class RmiServiceImpl implements RmiService {

	@Override
	public void say() {
		System.out.println("hello");
	}
}
