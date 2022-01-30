package example.app02event.custom.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class EventTextView extends TextView {

    public EventTextView(Context context) {
        super(context);
    }

    public EventTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public EventTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
