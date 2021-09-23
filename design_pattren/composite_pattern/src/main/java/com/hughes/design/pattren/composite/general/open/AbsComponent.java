package com.hughes.design.pattren.composite.general.open;

/**
 * @author hughes-T
 * @since 2021/9/23 10:06
 */
public abstract class AbsComponent {

    protected String commonState;

    public AbsComponent(String commonState){
        this.commonState = commonState;
    }

    public void operation(){
        throw new UnsupportedOperationException("不支持操作");
    }

    public AbsComponent getChild(int index){
        throw new UnsupportedOperationException("不支持获取子节点");
    }

    public void addChild(AbsComponent component){
        throw new UnsupportedOperationException("不支持添加操作");
    }

    public void removeChild(AbsComponent component){
        throw new UnsupportedOperationException("不支持删除操作");
    }

}
