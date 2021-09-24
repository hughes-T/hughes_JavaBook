package com.hughes.design.pattern.decorator.general;

/**
 * @author hughes-T
 * @since 2021/9/24 13:46
 */
public class ConcreteComponent extends Component{

    @Override
    void operation() {
        System.out.println("Component Operation ...");
    }
}
