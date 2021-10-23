package com.hughes.spring.source.v2di.project.action;

import com.hughes.spring.source.v2di.framework.annotation.HAutowired;
import com.hughes.spring.source.v2di.framework.annotation.HService;

/**
 * @author hughes-T
 * @since 2021/10/23 17:43
 */
@HService
public class TwoAction {

    @HAutowired
    private OneAction oneAction;
}
