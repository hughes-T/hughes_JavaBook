package com.hughes.spring.source.v2ioc.project.service.impl;


import com.hughes.spring.source.v2ioc.framework.annotation.HService;
import com.hughes.spring.source.v2ioc.project.service.IDemoService;

/**
 * 核心业务逻辑
 */
@HService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name + ",from service.";
	}

}
