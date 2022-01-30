package net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import net.server.thread.ChatServerSocketThread12;
import net.server.thread.ChatServerSocketThread13;

public class ChatServerApp13 {
	
	public static void main(String[] args){
		
	System.out.println("[ChatServerApp Main Thread] : STARTUP.....\n");
		
		List<ChatServerSocketThread13> list = new Vector<ChatServerSocketThread13>(10, 10);
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		ChatServerSocketThread13 chatServerScoketThread = null;
		
		boolean loopFlag = false;
		
		try {
			serverSocket = new ServerSocket(7777);
			loopFlag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		while(loopFlag){
			
			try {
				
				System.out.println("\t[ChatServerApp Main Thread] : Client Connection Wait");
				
				socket = serverSocket.accept();
				
				System.out.println("\n\t[Host Main thread] : client"+socket.getRemoteSocketAddress()+"����");
				
				chatServerScoketThread = new ChatServerSocketThread13(socket, list);
				
				list.add(chatServerScoketThread);
				
				System.out.println("\t[ChatServerApp Main Thread] : ���� �����ڼ�"+list.size()+"\n");
				chatServerScoketThread.start();
			}catch (Exception e) {
				e.printStackTrace();
				loopFlag = false;
			}
		}
		
		System.out.println("\t[ChatServerApp Main Thread] : Client Connection Wait END");
		System.out.println("//////////////////////////////////////////////////////////\n");
			
		synchronized (list) {
			for (ChatServerSocketThread13 thread : list) {
				thread.setLoopFlag(false);
			}
		}
		
		while(true) {
			if(list.size() != 0) {
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {}
			}else {
				break;
			}
		}
		
		try {
			if(serverSocket != null) {
				serverSocket.close();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n[ChatServerApp Main Thread] : SHUTDOWN.....");
	}

}
