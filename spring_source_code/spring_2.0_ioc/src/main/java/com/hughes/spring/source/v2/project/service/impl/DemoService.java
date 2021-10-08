package com.hughes.spring.source.v2.project.service.impl;


import com.hughes.spring.source.v2.framework.annotation.GPService;
import com.hughes.spring.source.v2.project.service.IDemoService;

/**
 * 核心业务逻辑
 */
@GPService
public class DemoService implements IDemoService {

	public String get(String name) {
		return "My name is " + name + ",from service.";
	}

}
