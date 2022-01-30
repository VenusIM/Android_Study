package example.app02event.listener;

import android.graphics.Color;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class OnTouchListenerImpl implements View.OnTouchListener {
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
