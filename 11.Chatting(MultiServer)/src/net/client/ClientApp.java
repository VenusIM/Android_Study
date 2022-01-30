package net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.client.thread.ClientSocketThread;

public class ClientApp {
	
	public static void main(String[] args){
		
		System.out.println("================================= =========================");
		System.out.println("[ClientApp Main Thread] : STARTUP.....\n");
		
		String connectIp = "127.0.0.1";
		int connectPort = 7777;
		
		ClientSocketThread clientSocketThread = new ClientSocketThread(connectIp, connectPort);
		clientSocketThread.start();
		
		try {
			
			Thread.sleep(100);
			
			System.out.print("[전송문자입력 [종료시 Quit]] : ");
			
			while(true) {
				String message = new BufferedReader(new InputStreamReader(System.in)).readLine();
				clientSocketThread.toServerMessage(message);
				
				if(message.equals("Quit")) {
					break;
				}
			}
			
			clientSocketThread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("\n[ClientApp Main Thread] : SHUTDOWN.....\n");
		System.out.println("==========================================================");
	}
}
