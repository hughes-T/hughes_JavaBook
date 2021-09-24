package com.hughes.design.pattern.decorator.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author hughes-T
 * @since 2021/9/24 14:40
 */
public class JsonLoggerFactory {

    public static JsonLogger getLogger(Class clazz){
        Logger logger = LoggerFactory.getLogger(clazz);
        return new JsonLogger(logger);
    }
}
