package com.hughes.liskow.substitution;

/**
 * @author hughes-T
 * @since 2021/8/4  13:54
 */
public class LSPTest {

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setHigh(10);
        rectangle.setWidth(5);
        resize(rectangle);
        Rectangle square = new Square();
        square.setHigh(10);
        square.setWidth(5);
        resize(square);
    }

    public static void resize(Rectangle rectangle){
        while (rectangle.getHigh() >= rectangle.getWidth()){
            rectangle.setHigh(rectangle.getHigh() + 1);
            System.out.println("High:"+ rectangle.getHigh() + " " + "Width:"+ rectangle.getWidth());
        }

    }
}
