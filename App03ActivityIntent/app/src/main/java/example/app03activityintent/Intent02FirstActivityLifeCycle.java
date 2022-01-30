package example.app03activityintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Intent02FirstActivityLifeCycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_first);

        System.out.println("==> "+getClass().getSimpleName()+".onCreate");
    }

    public void xmlOnClick(View v){

        Intent intent = new Intent(this, Intent02SecondActivityLifeCycle.class);

        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("==> "+getClass().getSimpleName()+".onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("==> "+getClass().getSimpleName()+".onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("==> "+getClass().getSimpleName()+".onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("==> "+getClass().getSimpleName()+".onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("==> "+getClass().getSimpleName()+".onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("==> "+getClass().getSimpleName()+".onRestart");
    }
}