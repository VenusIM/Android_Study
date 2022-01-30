package example.app04thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import example.app04thread.daemon.DaemonThread03;

public class Thread03Complete extends AppCompatActivity {
    private TextView textViewMain;
    private TextView textViewThread;
    private int mainValue;
    private int threadValue;
    private DaemonThread03 daemonThread03;

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

        daemonThread03 = new DaemonThread03(handler);
        daemonThread03.start();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mainValue++;
                textViewMain.setText(mainValue+"");

                threadValue = daemonThread03.getThreadValue();
                textViewThread.setText(threadValue+"");
            }
        });

        System.out.println("==>"+getClass().getSimpleName()+".onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.daemonThread03.setThreadStop(true);
        System.out.println("==>"+getClass().getSimpleName()+".onDestroy()");
    }
}
