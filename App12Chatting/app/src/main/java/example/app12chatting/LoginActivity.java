package example.app12chatting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button buttonEnter;
    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        buttonEnter = findViewById(R.id.button_enter);
        editTextName = findViewById(R.id.editText_name);

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginActivity.this, ChatActivity.class);

                intent.putExtra("clientName", editTextName.getText().toString());
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println(getClass().getSimpleName() + "LoginActivity.onPause()");
        this.finish();
    }

    private long limitTime = 2000;
    private long pressTime = 0;

    @Override
    public void onBackPressed(){

        long currentTime = System.currentTimeMillis();
        long intervalTime = currentTime - pressTime;

        if(0 <= intervalTime && limitTime >= intervalTime){
            super.onBackPressed();
        }else{
            pressTime = currentTime;
            Toast.makeText(this,"뒤로 버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT).show();
        }

    }

//    @Override
//    public void onBackPressed(){
//
//        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setCancelable(false);
//
//        alertDialog.setTitle("종료확인");
//        alertDialog.setMessage("종료하시겠습니까?");
//
//        alertDialog.setPositiveButton("Yes",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//
//        alertDialog.setNegativeButton("No",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        return;
//                    }
//                });
//        alertDialog.show();
//
//    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//            long currentTime = System.currentTimeMillis();
//            long intervalTime = currentTime - pressTime;
//
//            if (0 <= intervalTime && limitTime >= intervalTime) {
//                super.onBackPressed();
//            } else {
//                pressTime = currentTime;
//                Toast.makeText(this, "뒤로 버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT).show();
//            }
//
//        }
//        return true;
//    }


//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            new AlertDialog.Builder(this)
//                    .setCancelable(false)
//                    .setTitle("종료확인")
//                    .setMessage("종료하시겠습니까?")
//                    .setPositiveButton("Yes",
//                            new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
//                                }
//                            })
//                    .setNegativeButton("No", null).show();
//        }
//        return true;
//    }
}