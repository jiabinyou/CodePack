package Graph.BFS2.Plan1;

/**
 * 1.Override equals()
 * 在hashMap中，JAVA自带的equals()比较的是reference，即地址，然而这不是我们想要的，我们想要的是比较内部的value
 *
 * 2.Override hashCode()
 * 虽然在hashMap中，由key生成hashcode直接调用hashCode()即可，但是这个函数通常也是根据当前key所在的address生成hashcode，
 * 即address不同的hashcode不同，但是一旦address相同，可能结果就不理想，显然这也不是我们想要的
 * 在JAVA中：
 * if one.equals(two), it is a must that one.hashCode() == two.hashCode();
 * if one.hashCode() == two.hashCode(), it is not necessary one.equals(two)
 * when you want to override equals(), please definitely override hashCode() as well
 * 即：
 * 两个object equal，那么他们的hashcode一定相同
 * 两个object的hashcode相同，他们不一定equals
 * 要是override equals()，就必须override hashCode()!!
 */

/**
 * Java中，long数据类型范围内的所有整数称为long类型的整数字面量。long类型的整数常数总是以大写L或小写l结尾。
 * 比如： int 0
 *       Long 0L 或者 0l
 */

/**
 * Default PQ 不处理dup，如果input有dup，需自定义pq
 * Default teeSet可处理dup，
 */
public class A_BFS2_Step {

}
