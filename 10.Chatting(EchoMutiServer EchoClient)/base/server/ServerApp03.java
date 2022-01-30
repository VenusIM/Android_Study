package base.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp03 {
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
			ServerSocket serverSocket = new ServerSocket(7000);
			int count = 0;
			while(true) {
				++count;
				System.out.println("[Server] : Client"+count+"의 접속을 기다린다...");
				
				Socket socket = serverSocket.accept();
				System.out.println("[Server] : Client"+count+" 연결 완료... \n");
				
				new SocketThread(socket,count).start();
				
				System.out.println("\n\n\n ===> 여기는 main"+count+"의 while문 끝 ...\n\n\n");
			}
	}
}
	
class SocketThread extends Thread{
		
	private BufferedReader fromClient;
	private PrintWriter toClient;
		
	public SocketThread() {
		super();
	}
		
	public SocketThread(Socket socket, int count) {
		try {
			fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
				
			toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
				
			System.out.println("[Server] : Client"+count+" 접속 후 data를 받기 위한 무한 loop start \n");
			
							
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void run() {
		
		int count = 0;
		try {
			while(true) {
				++count;
				String clientData = fromClient.readLine();
				
				System.out.println("[Server] : Client로 부터 전송 받은 Data ==>"+clientData);
				toClient.println("server 회신[Data receiver OK]");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
