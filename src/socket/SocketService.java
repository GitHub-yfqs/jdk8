package socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;

public class SocketService {

	public static void main(String[] args) throws IOException {
		//http://127.0.0.1:6666/gfhgchg=123  可以访问
		//http协议 是应用层 Socket传输层
		//http协议需要Socket支持  以Socket为基础
		ServerSocket ss = new ServerSocket(6666);
		while (true) {
		//接收客户端的请求
		System.out.println("监听客户端的数据");
		Socket sc = ss.accept();
		InputStream is = sc.getInputStream();
		byte[] buffer = new byte[1024];
		int len=-1;
		len = is.read(buffer);
		String getData = new String(buffer, 0, len);
		System.out.println("从客户端获取的数据:"+getData);
		//业务处理 大小写转化
		String outPutData = getData.toUpperCase();
		//向客户端写数据
		OutputStream oStream = sc.getOutputStream();
		oStream.write(outPutData.getBytes("UTF-8"));
		//释放资源
		oStream.close();
		is.close();
		sc.close();
		}
		//ss.close();
	}

}
