package example.app01uibase;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class UIBase02 extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uibase02);

        EditText editText = this.findViewById(R.id.edit_text);
        System.out.println("입력된 내용 : "+editText.getText());

        Button buttonInputOk = this.findViewById(R.id.button_input_ok);
        System.out.println("버튼 이름 : "+buttonInputOk.getText());
    }
}
