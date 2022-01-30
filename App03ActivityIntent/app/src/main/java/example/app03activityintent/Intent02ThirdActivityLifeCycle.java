package example.app03activityintent;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Intent02ThirdActivityLifeCycle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_third);
        System.out.println("==> "+getClass().getSimpleName()+".onCreate");
    }

    public void xmlOnClick(View v){
        this.finish();
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