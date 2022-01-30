package example.app01uibase;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class UIBaseLayoutCode13 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagelayout13);
    }

    public void xmlOnClick(View v){

        RelativeLayout relativeLayout
                = (RelativeLayout) View.inflate(this,R.layout.new_message_module13,null);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.messagelayout);

        if(linearLayout.getChildCount() %2 == 0 ){
            relativeLayout.setBackgroundColor(Color.GRAY);
        }else{
            relativeLayout.setBackgroundColor(Color.LTGRAY);
        }

        linearLayout.addView(relativeLayout);


    }
}
