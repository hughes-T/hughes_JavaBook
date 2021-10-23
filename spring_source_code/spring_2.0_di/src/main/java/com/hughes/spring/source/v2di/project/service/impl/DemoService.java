package com.hughes.spring.source.v2di.project.service.impl;


import com.hughes.spring.source.v2di.framework.annotation.HService;
import com.hughes.spring.source.v2di.project.service.IDemoService;

/**
 * 核心业务逻辑
 */
@HService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name + ",from service.";
	}

}
