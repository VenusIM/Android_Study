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
	private boolean loopFlag;
	
	public ClientSocketThread() {
		// TODO Auto-generated constructor stub
	}
	
	public ClientSocketThread(String connetIp, int connectPort) {
		// TODO Auto-generated constructor stub\
		
		try {
			this.socket = new Socket();
			socket.setSoTimeout(timeOut);
//			socket.setSoLinger(true, timeOut);
			
			SocketAddress socketAddress = new InetSocketAddress(connetIp, connectPort);
			socket.connect(socketAddress, timeOut);
			System.out.println(socketAddress);
			
			toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
			
			loopFlag = true;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		
			System.out.println("[Client Thread] : ClientSocketThread.run() START...");
			
			while(loopFlag) {
				try {
					System.out.println("\n[ClientThread] : Sever와 Data 수신, 송신 무한 Loop");
					System.out.print("[전송문자입력 (종료시 Quit)] : ");
					String toHostValue = new BufferedReader(new InputStreamReader(System.in)).readLine();
					
					if(toHostValue.equals("Quit")) {
						break;
					}
					
					toServer.println(toHostValue);
					
					String fromHostData = fromServer.readLine();
					
					if(fromHostData == null) {
						break;
					}
					System.out.println("::Server에 수신 Data : "+fromHostData);
				
				}catch(SocketTimeoutException se) {
					se.printStackTrace();
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					loopFlag = false;
				}
			}
			this.close();
			System.out.println("[Client Thread] : ClientSocketThread.run() END...");
	}
	
	public void close() {
		System.out.println("::close() Start...");
		
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
			
			Thread.sleep(1000);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println("::close() end..");
	}

}
