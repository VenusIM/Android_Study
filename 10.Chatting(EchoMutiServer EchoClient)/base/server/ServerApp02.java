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
			
			System.out.println("[Server] : Client ���� �� data�� �ޱ� ���� ���� loop start \n");
			
			while(true) {
				String clientData = fromClient.readLine();
				
				System.out.println("[Server] : Client�� ���� ���� ���� Data ==>"+clientData);
				toClient.println("server ȸ��[Data receiver OK]");
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
				
				System.out.println("[Server] : Client�� ������ ��ٸ���...");
				Socket socket = serverSocket.accept();
				System.out.println("[Server] : Client ���� �Ϸ�... \n");
				
				new ServerApp02(socket);
				
				System.out.println("\n\n\n ===> ����� main�� while�� �� ...\n\n\n");
			}
			
	}
	
	

}
