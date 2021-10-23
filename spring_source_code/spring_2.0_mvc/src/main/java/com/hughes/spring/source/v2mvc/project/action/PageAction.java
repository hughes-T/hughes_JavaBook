package com.hughes.spring.source.v2mvc.project.action;


import com.hughes.spring.source.v2mvc.framework.annotation.HAutowired;
import com.hughes.spring.source.v2mvc.framework.annotation.HController;
import com.hughes.spring.source.v2mvc.framework.annotation.HRequestMapping;
import com.hughes.spring.source.v2mvc.framework.annotation.HRequestParam;
import com.hughes.spring.source.v2mvc.framework.servlet.HModelAndView;
import com.hughes.spring.source.v2mvc.project.service.IQueryService;

import java.util.HashMap;
import java.util.Map;

/**
 * 公布接口url
 * @author Tom
 *
 */
@HController
@HRequestMapping("/")
public class PageAction {

    @HAutowired
    IQueryService queryService;

    @HRequestMapping("/first.html")
    public HModelAndView query(@HRequestParam("teacher") String teacher){
        String result = queryService.query(teacher);
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("teacher", teacher);
        model.put("data", result);
        model.put("token", "123456");
        return new HModelAndView("first.html",model);
    }

}
