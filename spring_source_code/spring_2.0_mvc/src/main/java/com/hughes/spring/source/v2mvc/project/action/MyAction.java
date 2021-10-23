package com.hughes.spring.source.v2mvc.project.action;


import com.hughes.spring.source.v2mvc.framework.annotation.HAutowired;
import com.hughes.spring.source.v2mvc.framework.annotation.HController;
import com.hughes.spring.source.v2mvc.framework.annotation.HRequestMapping;
import com.hughes.spring.source.v2mvc.framework.annotation.HRequestParam;
import com.hughes.spring.source.v2mvc.framework.servlet.HModelAndView;
import com.hughes.spring.source.v2mvc.project.service.IModifyService;
import com.hughes.spring.source.v2mvc.project.service.IQueryService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 公布接口url
 * @author Tom
 *
 */
@HController
@HRequestMapping("/web")
public class MyAction {

	@HAutowired
	IQueryService queryService;
	@HAutowired
	IModifyService modifyService;

	@HRequestMapping("/query.json")
	public HModelAndView query(HttpServletRequest request, HttpServletResponse response,
							   @HRequestParam("name") String name){
		String result = queryService.query(name);
		return out(response,result);
	}
	
	@HRequestMapping("/add*.json")
	public HModelAndView add(HttpServletRequest request,HttpServletResponse response,
			   @HRequestParam("name") String name,@HRequestParam("addr") String addr){
		String result = modifyService.add(name,addr);
		return out(response,result);
	}
	
	@HRequestMapping("/remove.json")
	public HModelAndView remove(HttpServletRequest request, HttpServletResponse response,
								 @HRequestParam("id") Integer id){
		String result = modifyService.remove(id);
		return out(response,result);
	}
	
	@HRequestMapping("/edit.json")
	public HModelAndView edit(HttpServletRequest request,HttpServletResponse response,
			@HRequestParam("id") Integer id,
			@HRequestParam("name") String name){
		String result = modifyService.edit(id,name);
		return out(response,result);
	}
	
	
	
	private HModelAndView out(HttpServletResponse resp,String str){
		try {
			resp.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
