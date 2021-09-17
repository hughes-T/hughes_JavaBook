package com.hughes.design.pattren.prototype;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author hughes-T
 * @since 2021/8/11 14:42
 */
@Data
@Accessors(chain = true)
public class PrototypeObject implements Cloneable, Serializable {

    private String name;
    private int age;
    private List<String> hobbies;

    @Override
    protected PrototypeObject clone(){
        try {
            return (PrototypeObject) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
