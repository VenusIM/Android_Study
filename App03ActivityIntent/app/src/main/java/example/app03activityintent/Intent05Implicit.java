package example.app03activityintent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Intent05Implicit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intent05_implicit);
    }

    public void xmlOnClick(View v){
        Intent intent = null;

        switch (v.getId()){

            case R.id.button_web:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                this.startActivity(intent);
                break;

            case R.id.button_call:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel://010-0000-0000"));
                this.startActivity(intent);
                break;

            case R.id.button_share:
                intent = new Intent(Intent.ACTION_SEND);

                intent.addCategory(Intent.CATEGORY_DEFAULT);
                intent.putExtra(Intent.EXTRA_SUBJECT,"주제");
                intent.putExtra(Intent.EXTRA_TEXT,"내용");
                intent.putExtra(Intent.EXTRA_TITLE,"제목");
                intent.setType("text/plain");

                this.startActivity(intent);
                break;
        }
    }
}
