package test;

import java.util.List;


public class CharSequenceImpl implements CharSequence {
	CharSequence charSequence;
	List list;
	/*
	 * CharSequence �ǽӿ� ������������ʵ�ֵķ���
	 * ��һ��pulic�ĳ��󷽷�
	 *  public String toString();
	 *  ��������������1.8������
	 *  public default IntStream chars() {
	 *   public default IntStream codePoints() {
	 * */
	/**
	 * 
	 */
	@Override
	public int length() {
		String string = new String();
		/*string.chars()//�����ĸ��
        .filter(Character::isLowerCase)//ɸѡ������Сд��ĸ
        .count;//ͳ������
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
