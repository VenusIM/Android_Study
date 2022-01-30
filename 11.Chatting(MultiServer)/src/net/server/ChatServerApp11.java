package net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Vector;

import net.server.thread.ChatServerSocketThread11;

public class ChatServerApp11 {
	
	public static void main(String[] args){
		
	System.out.println("[ChatServerApp Main Thread] : STARTUP.....\n");
		
		List<ChatServerSocketThread11> list = new Vector<ChatServerSocketThread11>(10, 10);
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		ChatServerSocketThread11 chatServerScoketThread = null;
		
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
				
				System.out.println("\n\t[Host Main thread] : client"+socket.getRemoteSocketAddress()+"연결");
				
				chatServerScoketThread = new ChatServerSocketThread11(socket, list);
				
				list.add(chatServerScoketThread);
				
				System.out.println("\t[ChatServerApp Main Thread] : 현재 접속자수"+list.size()+"\n");
				chatServerScoketThread.start();
			}catch (Exception e) {
				e.printStackTrace();
				loopFlag = false;
			}
		}
		
		System.out.println("\t[ChatServerApp Main Thread] : Client Connection Wait END");
			
		synchronized (list) {
			for (ChatServerSocketThread11 thread : list) {
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
