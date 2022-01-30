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
import java.util.StringTokenizer;

public class ChatServerSocketThread13 extends Thread{
	
	private BufferedReader fromClient;
	private PrintWriter toClient;
	private Socket socket;
	private List<ChatServerSocketThread13> list;
	boolean loopFlag;
	private SocketAddress socketAddress;
	private String clientName;
	
	public ChatServerSocketThread13() {
	}
	
	public ChatServerSocketThread13(Socket socket, List<ChatServerSocketThread13> list) {
		
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
		for (ChatServerSocketThread13 chatServerScoketThread : list) {
			chatServerScoketThread.getWriter().println(message);
		}
	}
	
	public synchronized boolean hasUserName(String message) {
		
		for (ChatServerSocketThread13 chatServerSocketThread13 : list) {
			if(chatServerSocketThread13 != this
					&& chatServerSocketThread13.getUserName().equals(message) ){
				return true;
			}
		}
		return false;
	}
	
	public synchronized void whisperClient(String name, String message) {
		for (ChatServerSocketThread13 chatServerSocketThread13 : list) {
			if(chatServerSocketThread13 != null
					&& chatServerSocketThread13.getUserName().equals(name) ){
				chatServerSocketThread13.getWriter().println(message);
			}
		}
	}
	
	public PrintWriter getWriter() {
		return toClient;
	}
	
	public String getUserName() {
		return clientName;
	}
	
	public void execute(String protocol, String message) {
		
		
		if(protocol.equals("100")) {
			
			System.out.println(message);
			
			if( this.hasUserName(message) ) {
				toClient.println("중복된 이름입니다.");
				loopFlag = false;
			}else {				
				clientName = message;
				toAllClient("["+message+"]님 입장");
			}
				
		}else if(protocol.equals("200")) {
			if(message.indexOf(":") != -1) {
				
				String name = message.substring(0,message.indexOf(":"));
				message = message.substring(message.indexOf(":")+1);
				System.out.println("name : "+name);
				System.out.println("message :"+message);
				
				if(name.indexOf(",") != -1) {
					String[] nameList = name.split(",");
					for(String str : nameList) {
						for(ChatServerSocketThread13 chatServerSocketThread13 : list) {
							if(chatServerSocketThread13 != null
									&& chatServerSocketThread13.getUserName().equals(str)) {
								this.whisperClient(str, message);
							}
						}
					}
					
				}else {
					
					this.whisperClient(name, message);					
				}
				
			}else {
				toAllClient("["+clientName+"] : "+message);
			}
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
