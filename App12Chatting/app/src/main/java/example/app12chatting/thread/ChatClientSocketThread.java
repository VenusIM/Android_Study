package example.app12chatting.thread;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.nio.Buffer;

public class ChatClientSocketThread extends Thread{

    private BufferedReader fromServer;
    private PrintWriter toServer;
    private Socket socket;
    private int timeOut = 0;

    private boolean loopFlag = false;
    private Handler handler;
    private String connectIp = "192.168.0.136";
    private int connectPort = 7777;
    private String clientName;

    public ChatClientSocketThread(){
    }

    public ChatClientSocketThread(Handler handler, String clientName){
        this.handler = handler;
        this.clientName = clientName;
    }

    @Override
    public void run(){

        System.out.println("[ClientTread] : "+getClass().getSimpleName()+".run() START ...");

        try {

            this.socket = new Socket();
            System.out.println("socket 생성");

            socket.setSoTimeout(timeOut);
            socket.setSoLinger(true, timeOut);

            SocketAddress socketAddress = new InetSocketAddress(connectIp,connectPort);
            System.out.println("SocketAddress 생성됨");
            socket.connect(socketAddress,timeOut*3);
            System.out.println("Connect 정상 완료");

            toServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"),true);
            fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream(),"utf-8"));

            toServer.println("100:"+clientName);
            loopFlag = true;
        }catch (Exception e){
            e.printStackTrace();
        }

        while (loopFlag){
            System.out.println("\n[ClientThread] : Server 와 Data 수신 무한 Loop Start");

            String fromHostData = null;
            try {

                fromHostData = fromServer.readLine();

                if(fromHostData == null){
                    break;
                }

                System.out.println(":: Server에서 수신 Data : "+fromHostData);

                Message message = new Message();
                message.what = 100;
                message.obj = fromHostData;
                this.handler.sendMessage(message);

            } catch (SocketTimeoutException stoe) {
                System.out.println(stoe.toString());
                stoe.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();

                Message message = new Message();
                message.what = 500;
                message.obj = "서버접속이 강제 종료됨";
                this.handler.sendMessage(message);

                loopFlag = false;
            }
        }

        this.close();
        System.out.println("[CLientTread] : "+getClass().getSimpleName()+".run() END ... ");
    }

    public void close(){
        System.out.println(":: Close Start");

        try {

            if(toServer != null){
                toServer.close();
                toServer = null;
            }

            if(fromServer != null){
                fromServer.close();
                fromServer = null;
            }

            if(socket != null){
                socket.close();
                socket = null;
            }

            Thread.sleep(timeOut);

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(":: CLoese END ... ");
    }

    public void onDestroy(){
        System.out.println("[ClientThread] : "+getClass().getSimpleName()+".onDestroy()");
        loopFlag = false;
    }

    public void sendMessageToServer(String message){
        if(toServer != null){
            toServer.println(message);
        }
    }

}
