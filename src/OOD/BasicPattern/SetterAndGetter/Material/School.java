package OOD.BasicPattern.SetterAndGetter.Material;

/**
 * lintcode 222
 */

/**
 * Description
 * Implement a class School, including the following attributes and methods:
 *
 * A private attribute name of type string.
 * A setter method setName which expect a parameter name of type string.
 * A getter method getName which expect no parameter and return the name of the school.
 */

/**
 * Example
 * Java:
 *     School school = new School();
 *     school.setName("MIT");
 *     school.getName(); // should return "MIT" as a result.
 *
 * Python:
 *     school = School();
 *     school.setName("MIT")
 *     school.getName() # should return "MIT" as a result.
 */

//面向对象程序设计，name为私有成员，setter和getter是公有成员。

/**
 * This reference program is provided by @jiuzhang.com
 * Copyright is reserved. Please indicate the source for forwarding
 */

public class School {
    /*
     * Declare a private attribute *name* of type string.
     */
    private String name;

    /**
     * Declare a setter method `setName` which expect a parameter *name*.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Declare a getter method `getName` which expect no parameter and return
     * the name of this school
     */
    public String getName() {
        return this.name;
    }
}