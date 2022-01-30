package example.app04thread.daemon;

import android.os.Handler;
import android.os.Message;


public class DaemonThread04 extends Thread{

    private int threadValue;
    private Handler handler;
    private boolean threadStop = false;

    public DaemonThread04(){
        super();
    }

    public DaemonThread04(Handler handler){
        this.handler = handler;
    }

    public void run(){
        while(! threadStop){
            threadValue++;
            System.out.println(getClass().getSimpleName()+".threadValue : "+threadValue);

//            Message message = new Message();
//
//            message.what = 100;
//
//            message.arg1 = this.threadValue;
//
//            message.obj = new Integer(threadValue);

            Message message = Message.obtain(handler, 100, threadValue, 0, new Integer(threadValue));

            handler.sendMessage(message);

            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public int getThreadValue(){
        return threadValue;
    }

    public void setThreadStop(boolean threadStop){
        this.threadStop = threadStop;
    }
}
