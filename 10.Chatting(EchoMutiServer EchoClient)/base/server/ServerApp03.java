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
				System.out.println("[Server] : Client"+count+"�� ������ ��ٸ���...");
				
				Socket socket = serverSocket.accept();
				System.out.println("[Server] : Client"+count+" ���� �Ϸ�... \n");
				
				new SocketThread(socket,count).start();
				
				System.out.println("\n\n\n ===> ����� main"+count+"�� while�� �� ...\n\n\n");
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
				
			System.out.println("[Server] : Client"+count+" ���� �� data�� �ޱ� ���� ���� loop start \n");
			
							
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
				
				System.out.println("[Server] : Client�� ���� ���� ���� Data ==>"+clientData);
				toClient.println("server ȸ��[Data receiver OK]");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
