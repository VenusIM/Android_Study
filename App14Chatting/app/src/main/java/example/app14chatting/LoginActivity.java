package example.app14chatting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import example.app14chatting.util.EndToast;

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

                Intent intent = new Intent(LoginActivity.this, example.app14chatting.ChatActivity.class);

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

    EndToast endToast = new EndToast(this);

    @Override
    public void onBackPressed(){
        endToast.showEndToast("취소 버튼 한번더 누르시면 종료됩니다.");
    }
}