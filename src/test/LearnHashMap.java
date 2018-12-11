package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class LearnHashMap {

	public static void main(String[] args) {
		/*
		 * 2018/12/11
		 *hashMap 嵌套接口   链表转换红黑树 
		 *final关键字 回顾
		 *concurrentHashMap 了解 锁分段技术
		 */
		
		/*
		 * 2018/12/11
		 * 尝试泛型
		 */
		/*Temple<String> temple = new LearnHashMap.Temple();
		temple.setS(new String("aaa"));
		System.out.println(temple.output());*/
		
		/*
		 * 2018/12/11
		 * tableSizeFor()是HashMap用来将初始化容量转化大于输入参数且最近的2的整数次幂的数，
		 * 比如initialCapacity = 7，那么转化后就是8。
		 * 
		 * 1.7用的是roundUpToPowerOf2()方法，里面用到里了>>以及减操作，性能上来说肯定还1.8的高。
		 */
		/*System.out.println(LearnHashMap.tableSizeFor(8));*/
		
		/*
		 * 2018/12/11
		 * 其抛出ConcurrentModificationException
		 * 把"GoddessY".equals(a)换成"Joemsu".equals(a)还会抛出异常吗？
		 * https://www.cnblogs.com/joemsu/p/7724623.html#_caption_2
		 */
		Map<String, Integer> map = new HashMap<>();
		map.put("GoddessY", 1);
		map.put("Joemsu", 2);
		for (String a : map.keySet()) {
		  /*if ("GoddessY".equals(a)) {
		    map.remove(a);
		  }*/
			if ("Joemsu".equals(a)) {
			    map.remove(a);
			  }
		}
		
		
	}
	
	public static class Temple<S>{
		S s;
		public void setS(Object o){
			s = (S) o;
		}
		public String output(){
			return s.toString();
		}
	}
	
	/**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    
    /**
     * Returns a power of two size for the given target capacity.
     *是用来将初始化容量转化大于输入参数且最近的2的整数次幂的数，比如initialCapacity = 7，那么转化后就是8。
     */
	static final int tableSizeFor(int cap) {
		  int n = cap - 1;
		  n |= n >>> 1;
		  n |= n >>> 2;
		  n |= n >>> 4;
		  n |= n >>> 8;
		  n |= n >>> 16;
		  return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
		}
}
