package OOD.BasicPattern.Singleton;

/**
 * 创建私有静态成员对象，每次获取时返回此对象。
 */
/**
 * This reference program is provided by @jiuzhang.com
 * Copyright is reserved. Please indicate the source for forwarding
 */

class Solution {


    /**
     * @return: The same instance of this class every time
     */
    public static Solution instance = null;
    public static Solution getInstance() {
        if (instance == null) {
            instance = new Solution();
        }
        return instance;
    }
};

/**
 编写单例模式需要考虑的事情
 单例模式：任何时候获取的对象，应该是同一个

 需禁止类外使用new关键字构造对象，所以用private关键字修饰构造函数，将其私有化
 因为不能使用new关键字构建对象，所以我们需要暴露一个方法给外部，作为单例对象与外界沟通的唯一方式。使用静态方法是个不错的选择。
 多线程情况下，需要保证对象是单例的。
 */
/**
 * This reference program is provided by @jiuzhang.com
 * Copyright is reserved. Please indicate the source for forwarding
 */


// V1:双重检查+synchronized锁懒汉版
class SolutionV1{

    // 1. 私有单例对象，禁止通过 类名.属性 访问
    // 2. 将其声明为静态成员，使得在静态方法中得以访问
    // 3. 使用volatile关键字，消除指令重排序的影响
    private static volatile SolutionV1 instance;

    // 1. 私有构造函数
    private SolutionV1(){

    }

    // 静态方法，返回单例对象。双重检查+synchroinzed锁，保证多线程下instance对象唯一
    public static SolutionV1 getInstance(){
        if(instance == null){
            synchronized(SolutionV1.class){
                if(instance == null){
                    // 多线程并发访问，只会有一个线程初始化成功
                    instance = new SolutionV1();
                }
            }
        }
        return instance;

    }
}


// V2: 静态内部类版
class SolutionV2{

    private static volatile SolutionV2 instance; //??对吗？

    static class InnerClass{
        private static SolutionV2 instance = new SolutionV2();
    }

    public static SolutionV2 getInstance(){
        return SolutionV2.instance;
    }

}


// V3: 枚举类版
enum SolutionV3{
    INSTANCE;

    public static SolutionV3 getInstance(){
        return SolutionV3.INSTANCE;
    }
}


