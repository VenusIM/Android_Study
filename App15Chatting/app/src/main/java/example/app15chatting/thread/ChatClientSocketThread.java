package example.app15chatting.thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

import com.model2.mvc.service.domain.User;

import android.os.Handler;
import android.os.Message;

import example.app15chatting.rest.RestHttpClient;

/*
 *  Server 와 통신하는 Thread
 */
public class ChatClientSocketThread extends Thread{

	///Field
	private BufferedReader  fromServer;
	private PrintWriter toServer;
	private Socket socket;
	private int timeOut = 3000;
	// 무한루프 제어용 Flag
	private boolean loopFlag = false;
	private Handler handler;
	private String connectIp = "192.168.0.137";
	private int connectPort = 7777;
	// Client 대화명
	private String clientName;

	///Constructor
	public ChatClientSocketThread(){
	}
	public ChatClientSocketThread(Handler handler , String clientName){
		this.handler = handler;
		this.clientName = clientName;
	}

	///Method
	public void run(){

		System.out.println("[Client Thread ] : "+getClass().getSimpleName()+".run()  START.................");

		try{
			// Host 접속 : Socket 생성만
			this.socket = new Socket( );

			// Timeout 3초::Stream read() 3초동안 Data 없으면 SocketTimeoutException 발생
			socket.setSoTimeout( timeOut );

			// Socket 종료시 대기 : Server 와 정상종료 용도
			socket.setSoLinger(true, timeOut);

			//Host 접속 : timeout 지정
			SocketAddress socketAddress = new InetSocketAddress(connectIp, connectPort);
			socket.connect(socketAddress, timeOut*10);

			// Host 통신을 위한 Stream 생성
			toServer 	= new PrintWriter(
					new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true );
			fromServer = new BufferedReader(
					new InputStreamReader (socket.getInputStream(),"UTF-8" ));

			// Stream 생성 과 동시에 대화명 전송하기
			// Application Protocol
			// 100 : 대화명입력 / 대화참여
			// 200 : 모든 대화상대에게 대화내용 보내기
			// 300 : 특정대상에개 대화내용 보내기
			// 400 : 대화중단 퇴실
			//////////////////////////////////////////////////////////////////////////////////
			// 주석 : 회원만 입장, WAS 확인 위해
			// toServer.println("100:"+clientName);
			// Socket / Stream 생성 OK

			// loopFlag = true;
			//////////////////////////////////////////////////////////////////////////////////


			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			// 회원 확인위해 추가된부분....
			RestHttpClient restHttpClient = new  RestHttpClient();

			//==> getJsonUser01() :  JsonSimple 3rd party lib 사용
			//==> getJsonUser02() : JsonSimple + codehaus 3rd party lib 사용
			//==> 주석처리하여 두 경우 다 사용해 보세요.
			User user = restHttpClient.getJsonUser01(clientName);
			//User user = restHttpClient.getJsonUser02(clientName);

			if(user == null){
				// 다른 Thread 의 UI 수정시 Thread 간 통신을 담당하는 Handler 사용
				// Handler 통해 전달 할 Data 는 Message Object 생성 전달
				Message message = new Message();
				// Application Protocol : 100 ==> 정상
				message.what = 100;
				// 전달할 Data 가 많으면 : Domain Object
				message.obj =" [ "+clientName +" ] 회원만 입장가능합니다.";
				// Hander 에게 Message 전달
				this.handler.sendMessage(message);
			}else{
				toServer.println("100:"+clientName);
				loopFlag = true;
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
		}catch(Exception e){
			e.printStackTrace();
		}


		while(loopFlag){

			try{

				System.out.println("[Client Thread ] : Server 와 data 수신,송신 무한 Loop Start ");
				//  Server 전송 Data Wait : IO Block
				// Timeout 3초::Stream read() 3초동안 Data 없으면 SocketTimeoutException 발생
				String fromHostData = fromServer.readLine();

				// 1. Server Application 강제 종료시
				// 	: RST(Reset) Packet 전송됨 ==> Client : InputStream.read() Exception 발생
				// 2. Server 에서 Scoket.close() 정상종료시
				// 	: FIN(Final) Packet 전송됨ㄴ ==> Client : InputStream.read() null 입력됨
				if( fromHostData == null){
					break;
				}

				System.out.println(":: Server 에서수신 Data : "+fromHostData);

				// 다른 Thread 의 UI 수정시 Thread 간 통신을 담당하는 Handler 사용
				// Handler 통해 전달 할 Data 는 Message Object 생성 전달
				Message message = new Message();
				// Application Protocol : 100 ==> 정상
				message.what = 100;
				// 전달할 Data 가 많으면 : Domain Object
				message.obj = fromHostData;
				// Hander 에게 Message 전달
				this.handler.sendMessage(message);

				// 대화명 중복인 경우 :  현 Thread 종료
				if(fromHostData.indexOf("대화명 중복") != -1 ){
					break;
				}

			} catch (SocketTimeoutException stoe) {
				System.out.println(stoe.toString());
				//stoe.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();

				//==> 서버가 강제종료 : Exception 발생시
				// 다른 Thread 의 UI 수정시 Thread 간 통신을 담당하는 Handler 사용
				// Handler 통해 전달 할 Data 는 Message Object 생성 전달
				Message message = new Message();
				// Application Protocol : 500 ==> 서버Error
				message.what = 500;
				// 전달할 Data 가 많으면 : Domain Object
				message.obj = "서버접속이 강제종료됨";
				// Hander 에게 Message 전달
				this.handler.sendMessage(message);

				//Server 와 data 수신,송신 무한 Loop 종료
				loopFlag = false;
			}
		}// end of while

		this.close();

		System.out.println("[Client Thread ] : "+getClass().getSimpleName()+".run() END.................");

	}//end of run()


	// 각종 객체 close()
	public void close(){

		System.out.println(":: close() start...");

		try{

			if( toServer != null){
				toServer.close();
				toServer = null;
			}

			if( fromServer != null){
				fromServer.close();
				fromServer = null;
			}

			if( socket != null){
				socket.close();
				socket = null;
			}

			//Server 에서 정상적으로 close 할때 까지 대기
			Thread.sleep(timeOut);

		}catch(Exception e){
			System.out.println( e.toString() );
			//e.printStackTrace();
		}
		System.out.println(":: close() end...");
	}


	// Activity Life Cycle 이해 / Activity.onDestiry() 에서 Call
	// Server 로부터 Data 받는 무한 Loop 종료
	public void onDestroy(){
		System.out.println(":: ChatClientSocketThread.onDestroy()");
		loopFlag = false;
	}

	// Server 로 Message 전송
	public void sendMessgeToServer(String message){
		if( toServer != null){
			toServer.println(message);
		}
	}

}//end of class