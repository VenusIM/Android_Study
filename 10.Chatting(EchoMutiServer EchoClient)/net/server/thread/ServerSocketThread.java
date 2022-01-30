package net.server.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ServerSocketThread extends Thread{

	private BufferedReader fromClient;
	private PrintWriter toClient;
	private BufferedReader fromClientA;
	private PrintWriter toClientA;
	private Socket socket;
	private List<ServerSocketThread> list;
	boolean loopFlag;
	private String fromClientData;
	
	public ServerSocketThread() {
		// TODO Auto-generated constructor stub
	}
	
	public ServerSocketThread(Socket socket, List<ServerSocketThread> list) {
		this.socket = socket;
		this.list = list;
		
		try {
			fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
			
			
			loopFlag = true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		System.out.println("\n[ServerSocketThread ("+list.indexOf(this)+")] : data를 수신, 송신 Loop Start");
		
		
		while(loopFlag) {
			try {
				if((fromClientData = fromClient.readLine()) != null) {
					System.out.println("\n[ServerSocketThread ("+list.indexOf(this)+")] : Client 전송 받은 Data ==>"+fromClientData);
					toClient.println(fromClientData);
					
				}else {
					break;
				}
			}catch(SocketException se) {
				se.printStackTrace();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				loopFlag = false;
			}
		}
		System.out.println("\n[ServerSocketThread ("+list.indexOf(this)+")] : data를 수신, 송신 Loop END");
		this.close();
	}
	
	public void close() {
		System.out.println("::close() start...");
		
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
			
			System.out.println("접속자수 : "+list.size());
		}catch (IOException ie) {
			ie.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("::close() end...\n");
	}
	
	public void setLoopFlag(boolean loopFlag) {
		this.loopFlag = loopFlag;
	}
	
}
