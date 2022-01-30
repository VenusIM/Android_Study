package example.app04thread.daemon;

import android.widget.TextView;

public class DaemonThread02 extends Thread{
    private TextView textViewThread;
    private int threadValue;
    private boolean threadStop = false;

    public DaemonThread02(){
        super();
    }

    public DaemonThread02(TextView textViewThread, int threadValue){
        super();
        this.textViewThread = textViewThread;
        this.threadValue = threadValue;
    }

    public void run(){

        while(! threadStop){

            threadValue++;
            System.out.println(this.getClass().getSimpleName()+".threadValue : "+threadValue);

            textViewThread.setText(Integer.toString(threadValue));

            try{
                Thread.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public int getThreadValue() {
        return threadValue;
    }

    public void setThreadStop(boolean threadStop) {
        this.threadStop = threadStop;
    }
}
