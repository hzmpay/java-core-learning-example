package com.hzm.freestyle.rpc;


public class HelloServcieImpl implements HelloServcie {

	@Override
	public String hello(String message) {
		return "hello" + message;
	}
}
