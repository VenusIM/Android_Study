package net.client.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

public class ClientSocketThread extends Thread{

	private BufferedReader fromServer;
	private PrintWriter toServer;
	private Socket socket;
	private int timeOut = 3000;
	boolean loopFlag;
	
	public ClientSocketThread() {
	}
	
	public ClientSocketThread(String connectIp, int connectPort) {
		
		try {
			
			this.socket = new Socket();
			
			socket.setSoLinger(true, timeOut);
			
			SocketAddress socketAddress = new InetSocketAddress(connectIp, connectPort);
			socket.connect(socketAddress, timeOut);
			
			toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			loopFlag = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		System.out.println("[Client Thread] : ClientSocketThread.run() START............");
		
		while(loopFlag) {
			
			try {
				
				String fromHostData = fromServer.readLine();
				
				if(fromHostData == null) {
					break;
				}
				
				System.out.println("\n:: Server에서 수신 Data : "+fromHostData);
				
				System.out.print("[전송문자입력 [종료시 Quit]] : \n > ");
				
			}catch(SocketTimeoutException stoe) {
				stoe.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
				loopFlag = false;
			}
		}
		
		this.close();
		
		System.out.println("[Cilent Thread] : ClientStocketThread.run() END.....");
	}
	
	public void close() {
		
		System.out.println(":: client close() start.....");
		
		try {
			
			if(toServer != null) {
				toServer.close();
				toServer = null;
			}
			
			if(fromServer != null) {
				fromServer.close();
				fromServer = null;
			}
			
			if(socket != null) {
				socket.close();
				socket = null;
			}
	
			Thread.sleep(timeOut);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(":: client close() end.....\n");
	}
	
	public void toServerMessage(String message) {
		toServer.println(message);
	}
}
