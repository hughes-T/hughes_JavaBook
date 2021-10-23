package com.hughes.spring.source.v2di.project.action;



import com.hughes.spring.source.v2di.framework.annotation.HAutowired;
import com.hughes.spring.source.v2di.framework.annotation.HController;
import com.hughes.spring.source.v2di.framework.annotation.HRequestMapping;
import com.hughes.spring.source.v2di.framework.annotation.HRequestParam;
import com.hughes.spring.source.v2di.project.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//虽然，用法一样，但是没有功能
@HController
@HRequestMapping("/demo")
public class DemoAction {

  	@HAutowired
	private IDemoService demoService;

  	@HAutowired
  	private TwoAction twoAction;

	@HRequestMapping("/query")
	public void query(HttpServletRequest req, HttpServletResponse resp,
					  @HRequestParam("name") String name){
//		String result = demoService.get(name);
		String result = "My name is " + name;
		try {
			resp.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@HRequestMapping("/add")
	public void add(HttpServletRequest req, HttpServletResponse resp,
					@HRequestParam("a") Integer a, @HRequestParam("b") Integer b){
		try {
			resp.getWriter().write(a + "+" + b + "=" + (a + b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@HRequestMapping("/sub")
	public void sub(HttpServletRequest req, HttpServletResponse resp,
					@HRequestParam("a") Double a, @HRequestParam("b") Double b){
		try {
			resp.getWriter().write(a + "-" + b + "=" + (a - b));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@HRequestMapping("/remove")
	public String  remove(@HRequestParam("id") Integer id){
		return "" + id;
	}

}
