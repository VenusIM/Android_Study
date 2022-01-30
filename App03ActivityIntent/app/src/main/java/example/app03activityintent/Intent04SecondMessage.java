package example.app03activityintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Intent04SecondMessage extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent02_second);

        editText = findViewById(R.id.edittext);

        Intent intent = this.getIntent();

        String message = intent.getStringExtra("message01");
        editText.setText(message);
    }

    public void xmlOnClick(View v){

        if(((Button) v).getText().equals("Close")){
            this.finish();
            return;
        }

        Intent intent = new Intent(this, Intent04ThirdMessage.class);

        intent.putExtra("message02",editText.getText().toString());

        //startActivity(intent);
        startActivityForResult(intent,1004);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        System.out.println("==>request Code"+requestCode);
        System.out.println("==>result Code : "+resultCode);
        System.out.println("==>Data : "+data);

        switch (requestCode){
            case 1004:
                String message = "return information is not Exeist";

                if(data != null){
                    message = data.getStringExtra("returnMessage");
                }

                if(resultCode == Activity.RESULT_OK){
                    editText.setText("Activity.RESULT_OK = "+resultCode+":"+message);
                }else if(resultCode == Activity.RESULT_CANCELED){
                    editText.setText("Activity.RESULT_CANCELED = "+resultCode+":"+message);
                }
                break;

            case 1005:
                break;

        }
    }
}