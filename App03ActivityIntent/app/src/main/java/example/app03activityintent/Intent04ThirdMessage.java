package example.app03activityintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Intent04ThirdMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_third);

        Intent intent = this.getIntent();

        String message = intent.getStringExtra("message02");
        if(message != null){
            System.out.println("==>"+getClass().getSimpleName()+":"+message);
        }
    }

    public void xmlOnClick(View v){

        Intent intent = new Intent();

        intent.putExtra("returnMessage","return information: Hello");
        this.setResult(Activity.RESULT_OK,intent);
        this.finish();
    }
}