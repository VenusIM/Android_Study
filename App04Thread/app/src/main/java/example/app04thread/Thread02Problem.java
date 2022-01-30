package example.app04thread;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import example.app04thread.daemon.DaemonThread02;

public class Thread02Problem extends AppCompatActivity {
    private TextView textViewMain;
    private TextView textViewThread;
    private int mainValue;
    private int threadValue;
    private DaemonThread02 daemoThread02;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.thread01ui);

        textViewMain = findViewById(R.id.textview_main);
        textViewThread = findViewById(R.id.textview_thread);

        daemoThread02 = new DaemonThread02(textViewThread, threadValue);
        daemoThread02.start();

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainValue++;
                textViewMain.setText(mainValue+"");

                threadValue = daemoThread02.getThreadValue();
                textViewThread.setText(threadValue+"");
            }
        });

        System.out.println("==>"+getClass().getSimpleName()+".onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.daemoThread02.setThreadStop(true);
        System.out.println("==>"+getClass().getSimpleName()+"onDestroy()");
    }
}
