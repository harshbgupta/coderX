package com.kritsn.lld.designPattern;

/**
 * Copyright © 2026 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Jul 23, 2026
 */

public class _02GoFFactoryMethodDemo {
    public static void main(String[] args) {
        ShapeCreator squareShape = new SquareCreator();
        squareShape.getFormula();

        ShapeCreator circleShape = new CircleCreator();
        circleShape.getFormula();;
    }
}
//step 1 Define the AbstractProduct
interface Shape {
    void formulaOfArea();
}

//step 2 List your Concrete Products
class Square implements Shape {

    @Override
    public void formulaOfArea() {
        System.out.println("length * width ");
    }
}


class Circle implements Shape {

    @Override
    public void formulaOfArea() {
        System.out.println("pi * pow(radius,2)");
    }
}

//step 3 Define the Creator (abstract class)
abstract class ShapeCreator {
    abstract Shape createShape();

    void getFormula() {
        Shape shape = createShape();
        shape.formulaOfArea();
    }
}

//step 4 define the Creator (concrete class)
class SquareCreator extends ShapeCreator {

    @Override
    Shape createShape() {
        return new Square();
    }
}

class CircleCreator extends ShapeCreator {

    @Override
    Shape createShape() {
        return new Circle();
    }
}

