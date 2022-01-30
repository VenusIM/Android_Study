package example.app11chatting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import example.app11chatting.thread.ChatClientSocketThread;

public class ChatActivity extends AppCompatActivity {

    private TextView textViewContent;
    private android.widget.Button buttonSend;
    private EditText editTextMessage;
    private ScrollView scrollView;
    private ChatClientSocketThread chatClientSocketThread;

    private Handler handler = new Handler(){
        public void handleMessage(Message message){

            if(message.what == 200){
                String fromHostMessage = (String)message.obj;

                editTextMessage.setText("");

                textViewContent.append(fromHostMessage+"\n");

                scrollView.fullScroll(ScrollView.FOCUS_DOWN);

            }

            if(message.what == 500) {
                String endMessage = (String) message.obj;

                textViewContent.append(endMessage + "\n");

//              scrollView.scrollTo(0, scrollView.getHeight());
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);

                buttonSend.setEnabled(false);
                editTextMessage.setEnabled(false);
            }
        }
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        textViewContent = (TextView) findViewById(R.id.textView_content);
        scrollView = (ScrollView) findViewById(R.id.scrollview);
        buttonSend = (Button) findViewById(R.id.button_send);
        editTextMessage = (EditText) findViewById(R.id.edittext_message);

        chatClientSocketThread = new ChatClientSocketThread(handler);
        chatClientSocketThread.start();
        System.out.println("start되었음");

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        chatClientSocketThread.sendMessagetoServer(editTextMessage.getText()+"");
                    }
                }.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println(getClass().getSimpleName()+".onDestroy()");

        if(chatClientSocketThread != null){
            chatClientSocketThread.onDestroy();
        }
    }
}