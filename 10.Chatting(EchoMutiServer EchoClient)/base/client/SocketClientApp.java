package base.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


public class SocketClientApp {
	
	private Socket socket;
	private BufferedReader fromServer;
	private PrintWriter toServer;

	public SocketClientApp() {
		// TODO Auto-generated constructor stub
		this.connect("127.0.0.1", "7000");
		
		this.dataSendAndReceive();
	}
	
	public void connect(String connectIp, String connectPort) {
		try {
			
			socket = new Socket(connectIp, Integer.parseInt(connectPort));
			
			socket.setSoTimeout(3000);
			
			toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
			 fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void dataSendAndReceive() {
		
		BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			while(true) {
				System.out.print("[Client] : Server로 전송할 Data : ");
				String content = keyboard.readLine();
				
				if(content.equals("quit")) {
					break;
				}
				
				toServer.println(content);
				
				String serverData = fromServer.readLine();
				
				System.out.println("[Client] : Server로 부터 받는 Data : "+serverData);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception{
		new SocketClientApp();
	}

}
