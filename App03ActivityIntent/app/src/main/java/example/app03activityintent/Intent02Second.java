package example.app03activityintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Intent02Second extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_second);
    }

    public void xmlOnClick(View v){

        if(((Button) v).getText().equals("Close")){
            this.finish();
            return;
        }

        Intent intent = new Intent(this, Intent02Third.class);

        startActivity(intent);
    }
}