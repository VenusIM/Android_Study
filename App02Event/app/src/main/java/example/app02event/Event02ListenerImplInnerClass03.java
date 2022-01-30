package example.app02event;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Event02ListenerImplInnerClass03 extends AppCompatActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.event_ui);

        Button button = (Button) this.findViewById(R.id.button03);

        button.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        Button button = (Button) v;

                        if(event.getAction() == MotionEvent.ACTION_DOWN){

                            if(button.getText().length() != 0){
                                button.setText("");
                                button.setBackgroundColor(Color.YELLOW);
                            }else{
                                button.setBackgroundColor(Color.GREEN);
                                button.setText("다시 Touch 해보세요.");
                                button.setGravity(Gravity.CENTER);
                            }
                            return true;
                        }

                        return false;
                    }
                }
        );
    }
}
