package PriorityQueue;

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
 * 重写pq comparator：
 * 1.Campus Bikes
 * 2.MergeSortedKLists
 * 3.TopKFrequentWords (Map.Entry)
 *
 * 重写Collections.sort() / Ararys.sort():
 * 1168. Optimize Water Distribution in a Village
 *
 *
 *
 * */
public class A_Override题目集锦 {

}
