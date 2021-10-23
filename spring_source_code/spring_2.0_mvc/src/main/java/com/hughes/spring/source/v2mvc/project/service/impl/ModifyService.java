package com.hughes.spring.source.v2mvc.project.service.impl;

import com.hughes.spring.source.v2mvc.framework.annotation.HService;
import com.hughes.spring.source.v2mvc.project.service.IModifyService;

/**
 * 增删改业务
 * @author Tom
 *
 */
@HService
public class ModifyService implements IModifyService {

	/**
	 * 增加
	 */
	public String add(String name,String addr) {
		return "modifyService add,name=" + name + ",addr=" + addr;
	}

	/**
	 * 修改
	 */
	public String edit(Integer id,String name) {
		return "modifyService edit,id=" + id + ",name=" + name;
	}

	/**
	 * 删除
	 */
	public String remove(Integer id) {
		return "modifyService id=" + id;
	}
	
}
