package example.app02event.custom.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;

/*
 * 1. Custom View(Widget) : API  제공되는 표준 View 확장, 기능 추가
 * 2. Event 발생시 호출되는 Call Back Method 추가
 * 		: Call Back Method : 특정상황(?)에 호출되는 Method
 * 3. Call Back Method API 확인 :android.view.View
 */

public class EventTextView extends AppCompatTextView {
    ///Field
    ///Constructor
    public EventTextView(Context context) {
        super(context);
    }

    public EventTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EventTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    ///Method
    //  :: View 을 Touch 하면 호출되는 Call Back Method O/R
    //  :: Touch Event 를 처리완료 true : 미 완료 false return
    //  :: 전달 Message  API 확인
    //  :: 전달 Message : MotionEvent 는 Touch 순간을 추상화,캡술화한 Object
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        // TextView 화면이 눌리면(ACTION_DOWN) : API 확인
        if( event.getAction() == MotionEvent.ACTION_DOWN ){

            if(this.getText().length() != 0){
                setText("");
                setBackgroundColor(Color.YELLOW);
            }else{
                setBackgroundColor(Color.GREEN);
                setText("다시 Touch 해보세요.");
                setGravity(Gravity.CENTER);
            }
            return true;
        }
        return false;
    }
}