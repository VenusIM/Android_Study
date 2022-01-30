package example.app02event;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import example.app02event.custom.widget.EventTextView;
import example.app02event.listener.OnTouchListenerImpl;

public class Event11OnCllickListenerETC extends AppCompatActivity {
    private Button button01;
    private Button button02;
    private Button button03;
    private EventTextView eventTextView;

    private Button.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if( ( (Button)v ).getText().equals("1번 Button") ){
                eventTextView.setTextSize(20);
            }else if( v == button02){
                eventTextView.setTextSize(30);
            }
            eventTextView.setText( ( (Button)v ).getText()+" 가 OnClick 됨");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_ui_etc);

        button01 = findViewById(R.id.button01);
        button02 = findViewById(R.id.button02);
        button03 = findViewById(R.id.button03);

        eventTextView = findViewById(R.id.eventTextView);

        button01.setOnClickListener(onClickListener);
        button02.setOnClickListener(onClickListener);
        button03.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Toast.makeText(Event11OnCllickListenerETC.this,
                            "01: 화면을 눌렀습니다. / DOWN",
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Toast.makeText(v.getContext(),
                            "02: 화면에서 뗐습니다. / UP",
                            Toast.LENGTH_LONG).show();

                }
                System.out.println("false return");
                return false;
            }
        });
    }
    
    public void xmlOnClick(View v){

        eventTextView.setTextSize(40);
        eventTextView.setText( ( (Button)v ).getText()+" 가 OnClick됨" );
    }

}
