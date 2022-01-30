package example.app04thread.daemon;

import android.os.Handler;
import android.os.Message;


public class DaemonThread03 extends Thread{

    private int threadValue;
    private Handler handler;
    private boolean threadStop = false;

    public DaemonThread03(){
        super();
    }

    public DaemonThread03(Handler handler){
        this.handler = handler;
    }

    public void run(){
        while(! threadStop){
            threadValue++;
            System.out.println(getClass().getSimpleName()+".threadValue : "+threadValue);

            Message message = new Message();

            message.what = 100;

            message.arg1 = this.threadValue;

            message.obj = new Integer(threadValue);

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
