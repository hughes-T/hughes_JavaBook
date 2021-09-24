package com.hughes.design.pattern.decorator.general;

/**
 * @author hughes-T
 * @since 2021/9/24 13:47
 */
public class ConcreteDecoratorA extends Decorator{

    protected ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    void operation() {
        operationA();
        component.operation();
    }

    void operationA(){
        System.out.println("ConcreteDecoratorA operationA");
    }
}
