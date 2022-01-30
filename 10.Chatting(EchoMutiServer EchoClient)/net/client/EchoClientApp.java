package net.client;

import net.client.thread.ClientSocketThread;

public class EchoClientApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("=====================================");
		System.out.println("[EchoClientApp Main Thread] : STARTUP...\n");
		
		String connetIp = "127.0.0.1";
		int connectPort = 7000;
		
		ClientSocketThread clientSocketThread = new ClientSocketThread(connetIp, connectPort);
		
		clientSocketThread.start();
		
		try {
			clientSocketThread.join();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		System.out.println("\n[EchoClientApp Main Thread] : SHUTDOWN...");
		System.out.println("==========================================");
	}

}
