package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;

public class SocketService {

	public static void main(String[] args) throws IOException {
		//http://127.0.0.1:6666/gfhgchg=123  ���Է���
		//httpЭ�� ��Ӧ�ò� Socket�����
		//httpЭ����ҪSocket֧��  ��SocketΪ����
		ServerSocket ss = new ServerSocket(6666);
		while (true) {
		//���տͻ��˵�����
		System.out.println("�����ͻ��˵�����");
		Socket sc = ss.accept();
		InputStream is = sc.getInputStream();
		byte[] buffer = new byte[1024];
		int len=-1;
		len = is.read(buffer);
		String getData = new String(buffer, 0, len);
		System.out.println("�ӿͻ��˻�ȡ������:"+getData);
		//ҵ���� ��Сдת��
		String outPutData = getData.toUpperCase();
		//��ͻ���д����
		OutputStream oStream = sc.getOutputStream();
		oStream.write(outPutData.getBytes("UTF-8"));
		//�ͷ���Դ
		oStream.close();
		is.close();
		sc.close();
		}
		//ss.close();
	}

}
