package example.app00first;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        LinearLayout linearLayout = new LinearLayout(this);
//
//        TextView textView = new TextView(this);
//
//        String message = "안녕, Hello Android! HardCoding";
//        textView.setText(message);
//        linearLayout.addView(textView);
//        setContentView(linearLayout);
    }
}