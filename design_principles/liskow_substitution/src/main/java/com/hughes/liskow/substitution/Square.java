package com.hughes.liskow.substitution;

/**
 * @author hughes-T
 * @since 2021/8/4  13:52
 */
public class Square extends Rectangle{
    private int length;

    @Override
    public int getWidth() {
        return length;
    }

    @Override
    public void setWidth(int width) {
       this.length = width;
    }

    @Override
    public int getHigh() {
        return length;
    }

    @Override
    public void setHigh(int high) {
        this.length = high;
    }
}
