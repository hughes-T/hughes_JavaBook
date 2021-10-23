package com.hughes.spring.source.v2mvc.framework.servlet;

import java.io.File;

/**
 * @author hughes-T
 * @since 2021/10/23 19:54
 */
public class HViewResolver {

    //.vm   .ftl  .jsp  .gp  .tom
    private final String DEFAULT_TEMPLATE_SUFFIX = ".html";

    private File templateRootDir;

    public HViewResolver(String templateRoot) {
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        templateRootDir = new File(templateRootPath);
    }

    public HView resolveViewName(String viewName) {
        if(null == viewName || "".equals(viewName.trim())){return null;}
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFIX) ? viewName : (viewName + DEFAULT_TEMPLATE_SUFFIX);
        File templateFile = new File((templateRootDir.getPath() + "/" + viewName)
                .replaceAll("/+","/"));

        return new HView(templateFile);
    }
}
