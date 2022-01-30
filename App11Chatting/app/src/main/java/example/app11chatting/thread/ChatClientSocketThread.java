package example.app11chatting.thread;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;


public class ChatClientSocketThread extends Thread{

	private BufferedReader fromServer;
	private PrintWriter toServer;
	private Socket socket;
	private int timeOut = 3000;
	boolean loopFlag = false;

	private Handler handler;
	private String connectIP = "172.30.1.42";
	private int connectPort = 7777;
	
	public ChatClientSocketThread() {
	}
	
	public ChatClientSocketThread(Handler handler) {
		this.handler = handler;
	}
	
	public void run() {
		
		System.out.println("[Client Thread] : "+getClass().getSimpleName()+".run() START............");

		try {

			this.socket = new Socket();

			socket.setSoTimeout(timeOut);
			socket.setSoLinger(true, timeOut);

			SocketAddress socketAddress = new InetSocketAddress(connectIP, connectPort);
			System.out.println("Address 생성완료 : "+socketAddress);
			socket.connect(socketAddress, timeOut*3);
			System.out.println("연결되었음");

			toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));

			this.loopFlag = true;

		}catch(Exception e) {
			e.printStackTrace();
		}

		while(loopFlag) {
			
			try {
				System.out.println("\n[Client Thread] : Server와 data 수신 무한 Loop Start");
				
				String fromHostData = fromServer.readLine();
				
				if(fromHostData == null) {
					break;
				}
				
				System.out.println("\n:: Server에서 수신 Data : "+fromHostData);

				Message message = new Message();
				message.what = 200;
				message.obj = fromHostData;
				this.handler.sendMessage(message);


			}catch(SocketTimeoutException stoe) {
				System.out.println(stoe.toString());
			}catch(Exception e) {
				e.printStackTrace();

				Message message = new Message();
				message.what = 500;
				message.obj = "서버 접속이 강제 종료됨";
				this.handler.sendMessage(message);

				loopFlag = false;
			}
		}
		
		this.close();
		
		System.out.println("[Cilent Thread] : "+getClass().getSimpleName()+".run() END.....");
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

	public void onDestroy(){
		System.out.println("[Client Tread] : "+getClass().getSimpleName()+".onDestroy");
		loopFlag = false;
	}
	
	public void sendMessagetoServer(String message) {
		toServer.println(message);
	}
}
