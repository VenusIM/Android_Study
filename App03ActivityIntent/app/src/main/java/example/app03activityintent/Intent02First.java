package example.app03activityintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Intent02First extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_first);
    }

    public void xmlOnClick(View v){

        Intent intent = new Intent(this, Intent02Second.class);

        startActivity(intent);
    }
}