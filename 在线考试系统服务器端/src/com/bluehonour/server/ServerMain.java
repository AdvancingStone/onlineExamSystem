package com.bluehonour.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	public static void main(String[] args) {
		ServerSocket ss;
		Socket socket = null;
		try {
			ss = new ServerSocket(10086);
			System.out.println("服务器已启动，等待客户端的连接。。。");
			
			while(true) {
				socket = ss.accept();
				new Thread(new ServerThread(socket)).start();
			}
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e1) {
			}
		}
	}
}
