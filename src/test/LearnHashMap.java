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
		 *hashMap Ƕ�׽ӿ�   ����ת������� 
		 *final�ؼ��� �ع�
		 *concurrentHashMap �˽� ���ֶμ���
		 */
		
		/*
		 * 2018/12/11
		 * ���Է���
		 */
		/*Temple<String> temple = new LearnHashMap.Temple();
		temple.setS(new String("aaa"));
		System.out.println(temple.output());*/
		
		/*
		 * 2018/12/11
		 * tableSizeFor()��HashMap��������ʼ������ת��������������������2���������ݵ�����
		 * ����initialCapacity = 7����ôת�������8��
		 * 
		 * 1.7�õ���roundUpToPowerOf2()�����������õ�����>>�Լ�����������������˵�϶���1.8�ĸߡ�
		 */
		/*System.out.println(LearnHashMap.tableSizeFor(8));*/
		
		
		/*
		 * 2018/12/11
		 * ���׳�ConcurrentModificationException
		 * ��"GoddessY".equals(a)����"Joemsu".equals(a)�����׳��쳣��
		 * https://www.cnblogs.com/joemsu/p/7724623.html#_caption_2
		 * 
		 * �����Ƽ�Ҳ��ȷ�������������п��Եõ���ȷ���������쳣��
			ConcurrentModificationException��
			��1�� ���һ���߳����ڵ�������һ������Collection.��һ���߳�ͬʱ�޸��˴�Collection�ڵ�ֵ�������ˡ��ṹ�ԡ��仯����ô����쳣�ͻ��׳�.
			��Ӧ�ĵ�������iterator����һ�ֿ���ʧ�ܵĵ�������fail-fast�������ð�ŷ��ռ��������ĵ�����������.
			��2�������Ƕ��̲߳���������.����쳣������ֻ������������׳�.��ʹ���̵߳ĳ���������쳣Ҳ���п����׳���.
			���磺һ���߳��������õ������������ϣ���ͬʱ�޸��˼��ϵ�ֵ�������˼��ϵĽṹ�Ա仯�����쳣����׳�.
			�����Ǹ�modCount������̽��ĳ���ϱ���������ʱ����ṹ�����ڲ������Ƿ����˱仯.
		 */
		Map<String, Integer> map = new HashMap<>();
		map.put("GoddessY", 1);
		map.put("Joemsu", 2);
		for (String a : map.keySet()) {
		  if ("GoddessY".equals(a)) {
		    map.remove(a);
		  }
			/*if ("Joemsu".equals(a)) {
			    map.remove(a);
			  }*/
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
     *����������ʼ������ת��������������������2���������ݵ���������initialCapacity = 7����ôת�������8��
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
