package com.hughes.design.pattern.proxy.jdkProxy;

import com.hughes.design.pattern.proxy.domain.IConsumer;
import com.hughes.design.pattern.proxy.domain.SimpleConsumer;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @author hughes-T
 * @since 2021/8/15 12:50
 */
public class TestJdkProxy {
    public static void main(String[] args) throws Exception {
        ConsumerJdkProxy consumerJdkProxy = new ConsumerJdkProxy();
        IConsumer proxyConsumer = consumerJdkProxy.getInstance(new SimpleConsumer());
        proxyConsumer.eatFood();

        //获取编译class文件
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{IConsumer.class});
        FileOutputStream fos = new FileOutputStream("E://$Proxy0.class");
        fos.write(bytes);
        fos.close();
    }
}
