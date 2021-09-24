package com.hughes.design.pattern.decorator.logger;


/**
 * @author hughes-T
 * @since 2021/9/24 14:35
 */
public class Client {
   private static final JsonLogger logger = JsonLoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {
        logger.info("哈哈哈");
        logger.error("呜呜呜");
    }
}
