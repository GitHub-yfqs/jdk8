package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;

public class LearnHashMap {

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		HashMap<String, Object> map2 = new HashMap<>();
		/*
		 * 2018/12/11
		 *hashMap 嵌套接口   链表转换红黑树 
		 *final关键字 回顾
		 *concurrentHashMap 了解
		 */
		
		/*
		 * 2018/12/11
		 * 尝试泛型
		 */
		Temple<String> temple = new LearnHashMap.Temple();
		temple.setS(new String("aaa"));
		System.out.println(temple.output());
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
}
