package com.hughes.spring.source.v2mvc.framework.servlet;

import java.util.Map;

/**
 * @author hughes-T
 * @since 2021/10/23 19:55
 */
public class HModelAndView {

    private String viewName;
    private Map<String,?> model;
    public HModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public HModelAndView(String s) {
        this.viewName = s;
    }

    public String getViewName() {
        return viewName;
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }

    public void setModel(Map<String, ?> model) {
        this.model = model;
    }
}
