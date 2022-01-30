package net.server.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.List;

public class ChatServerSocketThread12 extends Thread{
	
	private BufferedReader fromClient;
	private PrintWriter toClient;
	private Socket socket;
	private List<ChatServerSocketThread12> list;
	boolean loopFlag;
	private SocketAddress socketAddress;
	private String clientName;
	
	public ChatServerSocketThread12() {
	}
	
	public ChatServerSocketThread12(Socket socket, List<ChatServerSocketThread12> list) {
		
		this.socket = socket;
		this.socketAddress = socket.getRemoteSocketAddress();
		this.list = list;
		
		try {
			
			fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
			
			loopFlag = true;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		System.out.println("\n [ChatServerScoketThread"+socketAddress+"] : data를 수신, 송신 Lopp Start");
		
		String fromClientData = null;
		
		while(loopFlag) {
			try {
				if((fromClientData = fromClient.readLine()) != null && !fromClientData.equals("Quit")) {
					
					System.out.println("\n [ChatServerScoketThread"+socketAddress+"] : Client 전송 받은 Data ==> "+fromClientData);
//					toAllClient(fromClientData);
					execute(fromClientData.substring(0,3), fromClientData.substring(4));
					
				}else {
					System.out.println("\n [ChatServerScoketThread"+socketAddress+"] : Client 접속종료로 Thread 종료함");
					break;
				}
			}catch(SocketException se) {
				se.printStackTrace();
				loopFlag = false;
			}catch(Exception e) {
				e.printStackTrace();
				loopFlag = false;
			}
		}
		
		System.out.println("\n [ChatServerScoketThread"+socketAddress+"] : data를 수신, 송신 Loop End \n");
		this.close();
	}
	
	public synchronized void toAllClient(String message) {
		for (ChatServerSocketThread12 chatServerScoketThread : list) {
			chatServerScoketThread.getWriter().println(message);
		}
	}
	
	public PrintWriter getWriter() {
		return toClient;
	}
	
	public void execute(String protocol, String message) {
		
		
		if(protocol.equals("100")) {
			
			clientName = message;
			toAllClient("["+message+"]님 입장");
			
		}else if(protocol.equals("200")) {
			
			toAllClient("["+clientName+"] : "+message);
			
		}else if(protocol.equals("300")) {
			
			
			
		}else if(protocol.equals("400")) {
			
			toAllClient("["+clientName+"]님 퇴장");
			
		}
	}
	
	public void close() {
		System.out.println(":: close() start.....");
		
		try {
			if(toClient != null) {
				toClient.close();
				toClient = null;
			}
			
			if(fromClient != null) {
				fromClient.close();
				fromClient = null;
			}
			
			if(socket != null) {
				socket.close();
				socket = null;
			}
			
			list.remove(this);
			
			System.out.println("접속자 수 : "+list.size());
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(":: close() end.....\n");
	}

	public void setLoopFlag(boolean loopFlag) {
		this.loopFlag = loopFlag;
	}
		
}
