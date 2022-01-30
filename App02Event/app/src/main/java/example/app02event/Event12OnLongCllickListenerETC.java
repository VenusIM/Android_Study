package example.app02event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import example.app02event.custom.widget.EventTextView;

public class Event12OnLongCllickListenerETC extends AppCompatActivity {
    private Button button01;
    private Button button02;
    private Button button03;
    private EventTextView eventTextView;
    private android.os.Vibrator vibrator;

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
                    Toast.makeText(Event12OnLongCllickListenerETC.this,
                            "01: 화면을 눌렀습니다. / DOWN",
                            Toast.LENGTH_SHORT).show();
                    return true;
                }
                if(event.getAction() == MotionEvent.ACTION_UP){
                    Toast.makeText(v.getContext(),
                            "02: 화면에서 뗐습니다. / UP",
                            Toast.LENGTH_LONG).show();
                    return true;
                }
                System.out.println("false return");
                return false;
            }
        });
        eventTextView.setOnLongClickListener(new View.OnLongClickListener(){
            @SuppressLint("MissingPermission")
            @Override
            public boolean onLongClick(View v){
                Event12OnLongCllickListenerETC.this.vibrator
                        = (Vibrator)Event12OnLongCllickListenerETC.this.
                        getSystemService(android.content.Context.VIBRATOR_SERVICE);

                vibrator.vibrate(1000);
                return true;
            }
        });
    }

    @SuppressLint("MissingPermission")
    protected void onDestroy(){
        super.onDestroy();
        vibrator.cancel();
    }
    
    public void xmlOnClick(View v){

        eventTextView.setTextSize(40);
        eventTextView.setText( ( (Button)v ).getText()+" 가 OnClick됨" );
    }

}
