package OOD.BasicPattern.ShapeFactory.Material;


/**
 * lintcode 497
 * https://www.lintcode.com/problem/shape-factory/description
 */

/**
 * Description
 * Factory is a design pattern in common usage. Implement a ShapeFactory that can generate correct shape.
 *
 * You can assume that we have only three different shapes: Triangle, Square and Rectangle.
 */

/**
 * Example 1:
 *
 * Input:
 * ShapeFactory sf = new ShapeFactory();
 * Shape shape = sf.getShape("Square");
 * shape.draw();
 * Output:
 *   ----
 *  |    |
 *  |    |
 *   ----
 * Example 2:
 *
 * Input:
 * ShapeFactory sf = new ShapeFactory();
 * shape = sf.getShape("Triangle");
 * shape.draw();
 * Output:
 *    /\
 *   /  \
 *  /____\
 * Example 3:
 *
 *
 * Input:
 * ShapeFactory sf = new ShapeFactory();
 * shape = sf.getShape("Rectangle");
 * shape.draw();
 * Output:
 *   ----
 *  |    |
 *   ----
 */

/**
 * This reference program is provided by @jiuzhang.com
 * Copyright is reserved. Please indicate the source for forwarding
 */

/**
 * Your object will be instantiated and called as such:
 * ShapeFactory sf = new ShapeFactory();
 * Shape shape = sf.getShape(shapeType);
 * shape.draw();
 */
interface Shape {
    void draw();
}

class Rectangle implements Shape {
    // Write your code here
    @Override
    public void draw() {
        System.out.println(" ---- ");
        System.out.println("|    |");
        System.out.println(" ---- ");
    }
}

class Square implements Shape {
    // Write your code here
    @Override
    public void draw() {
        System.out.println(" ---- ");
        System.out.println("|    |");
        System.out.println("|    |");
        System.out.println(" ---- ");
    }
}

class Triangle implements Shape {
    // Write your code here
    @Override
    public void draw() {
        System.out.println("  /\\");
        System.out.println(" /  \\");
        System.out.println("/____\\");
    }
}

public class ShapeFactory {
    /**
     * @param shapeType a string
     * @return Get object of type Shape
     */
    public Shape getShape(String shapeType) {
        // Write your code here
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("Triangle")) {
            return new Triangle();
        } else if(shapeType.equalsIgnoreCase("Rectangle")) {
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("Square")) {
            return new Square();
        }
        return null;
    }
}
