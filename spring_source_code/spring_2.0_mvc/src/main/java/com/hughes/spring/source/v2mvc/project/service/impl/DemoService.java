package com.hughes.spring.source.v2mvc.project.service.impl;


import com.hughes.spring.source.v2ioc.framework.annotation.GPService;
import com.hughes.spring.source.v2ioc.project.service.IDemoService;

/**
 * 核心业务逻辑
 */
@GPService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name + ",from service.";
	}

}
