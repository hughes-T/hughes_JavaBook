package com.hughes.design.pattern.decorator.logger;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;

/**
 * @author hughes-T
 * @since 2021/9/24 14:34
 */
public class JsonLogger extends LoggerDecorator{

    protected JsonLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void info(String s) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MSG",s);
        logger.info(jsonObject.toJSONString());
    }

    @Override
    public void error(String s) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MSG",s);
        logger.error(jsonObject.toJSONString());
    }
}
