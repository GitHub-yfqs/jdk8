package test;

import java.util.List;


public class CharSequenceImpl implements CharSequence {
	CharSequence charSequence;
	List list;
	/*
	 * CharSequence 是接口 但是它有两个实现的方法
	 * 和一个pulic的抽象方法
	 *  public String toString();
	 *  下面两个方法是1.8新增的
	 *  public default IntStream chars() {
	 *   public default IntStream codePoints() {
	 * */
	/**
	 * 
	 */
	@Override
	public int length() {
		String string = new String();
		/*string.chars()//获得字母流
        .filter(Character::isLowerCase)//筛选出所有小写字母
        .count;//统计数量
*/		return 0;
	}

	@Override
	public char charAt(int index) {
		return 0;
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

}
