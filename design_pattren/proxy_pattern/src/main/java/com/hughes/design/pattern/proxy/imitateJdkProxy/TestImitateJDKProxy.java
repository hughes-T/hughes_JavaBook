package com.hughes.design.pattern.proxy.imitateJdkProxy;

import com.hughes.design.pattern.proxy.domain.IConsumer;
import com.hughes.design.pattern.proxy.domain.SimpleConsumer;

/**
 * @author hughes-T
 * @since 2021/8/30 11:12
 */
public class TestImitateJDKProxy {

    public static void main(String[] args) throws Exception {
        ConsumerImitateJdkProxy proxy = new ConsumerImitateJdkProxy();
        IConsumer consumer = proxy.getInstance(new SimpleConsumer());
        consumer.eatFood();

    }
}
