package base.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp02 {
	
	public ServerApp02(Socket socket) {
		
		try {BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
		
			PrintWriter toClient = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
			
			System.out.println("[Server] : Client 접속 후 data를 받기 위한 무한 loop start \n");
			
			while(true) {
				String clientData = fromClient.readLine();
				
				System.out.println("[Server] : Client로 부터 전송 받은 Data ==>"+clientData);
				toClient.println("server 회신[Data receiver OK]");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
			ServerSocket serverSocket = new ServerSocket(7000);
			
			while(true) {
				
				System.out.println("[Server] : Client의 접속을 기다린다...");
				Socket socket = serverSocket.accept();
				System.out.println("[Server] : Client 연결 완료... \n");
				
				new ServerApp02(socket);
				
				System.out.println("\n\n\n ===> 여기는 main의 while문 끝 ...\n\n\n");
			}
			
	}
	
	

}
