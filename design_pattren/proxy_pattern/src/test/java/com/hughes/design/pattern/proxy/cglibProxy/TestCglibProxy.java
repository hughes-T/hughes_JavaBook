package com.hughes.design.pattern.proxy.cglibProxy;

import com.hughes.design.pattern.proxy.domain.IConsumer;
import com.hughes.design.pattern.proxy.domain.SimpleConsumer;
import net.sf.cglib.core.DebuggingClassWriter;

/**
 * @author hughes-T
 * @since 2021/8/30 13:40
 */
public class TestCglibProxy {
    public static void main(String[] args) throws Exception {
        //将Cglib代理类字节码写入本地磁盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"E://cglib_proxy_class/");
        ConsumerCglibProxy proxy = new ConsumerCglibProxy();
        IConsumer consumer = proxy.getInstance(SimpleConsumer.class);
        consumer.eatFood();

    }
}
