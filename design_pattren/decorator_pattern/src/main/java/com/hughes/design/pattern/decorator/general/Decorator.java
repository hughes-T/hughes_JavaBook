package com.hughes.design.pattern.decorator.general;

/**
 * 装饰器接口
 * @author hughes-T
 * @since 2021/9/24 13:36
 */
public abstract class Decorator extends Component{

    protected Component component;

    protected Decorator(Component component){
        this.component = component;
    }

    abstract void operation();
}
