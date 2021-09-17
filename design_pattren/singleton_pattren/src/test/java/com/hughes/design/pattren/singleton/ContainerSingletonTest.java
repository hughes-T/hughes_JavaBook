package com.hughes.design.pattren.singleton;

/**
 * @author hughes-T
 * @since 2021/8/11 10:47
 */
public class ContainerSingletonTest {
    public static void main(String[] args) {
        new Thread(()-> {
            try {
                System.out.println(ContainerSingleton.getBean("com.hughes.design.pattren.singleton.ContainerSingleton"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                System.out.println(ContainerSingleton.getBean("com.hughes.design.pattren.singleton.ContainerSingleton"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
