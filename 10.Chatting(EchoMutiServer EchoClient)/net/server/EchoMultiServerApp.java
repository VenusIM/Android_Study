package net.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.List;
import java.util.Vector;

import net.server.thread.ServerSocketThread;

public class EchoMultiServerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=====================================");
		System.out.println("[EchoMultiServerApp Main Thread] : STARTUP...\n");
		
		List<ServerSocketThread> list = new Vector<ServerSocketThread>(10,10);
		List<Socket> token = new Vector<Socket>();
		
		ServerSocket serverSocket = null;
		Socket socket = null;
		ServerSocketThread serverSocketThread = null;
		
		
		boolean loopFlag = false;
		
		try {
			serverSocket = new ServerSocket(7000);
			loopFlag = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		while(loopFlag) {
			try {
				System.out.println("\n\t\t\t\t/////////////////////////////////////");
				System.out.println("\t\t\t\t[EchoMultiServerApp Main Thread] : Client Connection Waiting..");
				
				socket = serverSocket.accept();
				
				System.out.println("\n\t\t\t\t[Host Main Thread] : client"+socket.getRemoteSocketAddress()+" 연결");
		
				serverSocketThread = new ServerSocketThread(socket,list);
				
				list.add(serverSocketThread);
				
				System.out.println("\t\t\t\t[EchoMultiServerApp Main Thread] : 현재 접속자 수"+list.size()+"\n");
				serverSocketThread.start();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				loopFlag = false;
			}
		}
		
		System.out.println("\t\t\t\t[EchoMultiServerApp Main Thread] : Client Connection END..");
		System.out.println("\n\t\t\t\t/////////////////////////////////////\n");
		
		synchronized (list) {
			for(ServerSocketThread thread : list) {
				
				thread.setLoopFlag(false);
			}
		}
		
		while(true) {
			if(list.size() != 0) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}else {
				break;
			}
		}
		
		try {
			if(serverSocket != null) {
				serverSocket.close();
				serverSocket = null;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("\n[EchoMultiServerApp Main Thread] SHUTDOWN...");
		System.out.println("==============================================");
	}

}
