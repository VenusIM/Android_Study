package example.app02event;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import example.app02event.listener.OnTouchListenerImpl;

public class Event02ListenerImpl extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_ui);

        OnTouchListenerImpl onTouchListenerImpl = new OnTouchListenerImpl();

        Button button = this.findViewById(R.id.button03);
        button.setOnTouchListener(onTouchListenerImpl);
    }
}
