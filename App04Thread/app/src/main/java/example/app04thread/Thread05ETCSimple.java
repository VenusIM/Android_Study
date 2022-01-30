package example.app04thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import example.app04thread.daemon.DaemonThread04;

public class Thread05ETCSimple extends AppCompatActivity {
    private TextView textViewMain;
    private TextView textViewThread;
    private int mainValue;
    private int threadValue;
    private DaemonThread05 daemonThread05;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {

            if (msg.what == 100) {
                //threadValue = msg.arg1;
                threadValue = ((Integer) msg.obj).intValue();
                textViewThread.setText(threadValue + "");
            }
        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.thread01ui);

        textViewMain = findViewById(R.id.textview_main);
        textViewThread = findViewById(R.id.textview_thread);

        daemonThread05 = new DaemonThread05();
        daemonThread05.start();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainValue++;
                textViewMain.setText(mainValue+"");

                threadValue = daemonThread05.getThreadValue();
                textViewThread.setText(threadValue+"");
            }
        });

        System.out.println("==>"+getClass().getSimpleName()+".onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.daemonThread05.setThreadStop(true);
        System.out.println("==>"+getClass().getSimpleName()+".onDestroy()");
    }

    public class DaemonThread05 extends Thread{

        private boolean threadStop = false;

        public DaemonThread05(){}

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
}
