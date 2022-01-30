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

public class ChatServerSocketThread11 extends Thread{
	
	private BufferedReader fromClient;
	private PrintWriter toClient;
	private Socket socket;
	private List<ChatServerSocketThread11> list;
	boolean loopFlag;
	private SocketAddress socketAddress;
	
	public ChatServerSocketThread11() {
	}
	
	public ChatServerSocketThread11(Socket socket, List<ChatServerSocketThread11> list) {
		
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
		
		System.out.println("\n [ChatServerScoketThread"+socketAddress+"] : data�� ����, �۽� Lopp Start");
		
		String fromClientData = null;
		
		while(loopFlag) {
			try {
				if((fromClientData = fromClient.readLine()) != null && !fromClientData.equals("Quit")) {
					
					System.out.println("\n [ChatServerScoketThread"+socketAddress+"] : Client ���� ���� Data ==> "+fromClientData);
					toAllClient(fromClientData);
					
				}else {
					System.out.println("\n [ChatServerScoketThread"+socketAddress+"] : Client ��������� Thread ������");
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
		
		System.out.println("\n [ChatServerScoketThread"+socketAddress+"] : data�� ����, �۽� Loop End \n");
		this.close();
	}
	
	public synchronized void toAllClient(String message) {
		for (ChatServerSocketThread11 chatServerScoketThread : list) {
			chatServerScoketThread.getWriter().println(message);
		}
	}
	
	public PrintWriter getWriter() {
		return toClient;
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
			
			System.out.println("������ �� : "+list.size());
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(":: close() end.....\n");
	}

	public void setLoopFlag(boolean loopFlag) {
		this.loopFlag = loopFlag;
	}
		
}
