package com.hughes.spring.source.v1.project.service.impl;


import com.hughes.spring.source.v1.framework.annotation.HService;
import com.hughes.spring.source.v1.project.service.IDemoService;

/**
 * 核心业务逻辑
 */
@HService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name + ",from service.";
	}

}
