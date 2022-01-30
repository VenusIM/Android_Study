package example.app02event;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import example.app02event.custom.widget.EventTextView;
import example.app02event.listener.OnTouchListenerImpl;

public class Event10OnClickListenerETC extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_ui_etc);

        Button button01 = this.findViewById(R.id.button01);
        Button button02 = this.findViewById(R.id.button02);

        final EventTextView eventTextView = (EventTextView) findViewById(R.id.eventTextView);

        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventTextView.setTextSize(20);
                eventTextView.setText(((Button) v).getText() + " 가 OnClick 됨");
            }
        });

        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventTextView.setTextSize(20);
                eventTextView.setText(((Button) v).getText() + " 가 OnClick 됨");
            }
        });
    }

        public void xmlOnClick(View v){
            EventTextView eventTextView = (EventTextView) findViewById(R.id.eventTextView);
            eventTextView.setTextSize(20);
            eventTextView.setText(((Button) v).getText() + " 가 OnClick 됨");
    }
}
