package example.app15chatting;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import example.app15chatting.util.EndToast;

public class LoginActivity extends AppCompatActivity {

	///Field
	private Button buttonEnter;
	private EditText editTextName;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		this.buttonEnter = (Button)findViewById(R.id.button_enter);
		this.editTextName = (EditText)findViewById(R.id.editText_name);

		// 대화참여하기 Button Event : 입력 ChatActivity 로 대화명 전달,이동
		buttonEnter.setOnClickListener( new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// :: 다른 컴포넌트로 이동 위해
				//    컴포넌트를 생산할수 있는 Class 정보를
				//    Intent 생성자를 통해전달 intent instance 생성
				Intent intent = new Intent(LoginActivity.this, ChatActivity.class);

				// :: Activity 이동시 전달할 정보(Message) 가 있다면 저장
				//	  API 확인 : putExtra(name, value)
				intent.putExtra("clientName",editTextName.getText().toString());

				// :: intent 객체가 갖는 정보의 컴포넌트 호출
				startActivity(intent);
			}
		});
	}

	@Override
	// Activity Life Cycle 의 이해
	protected void onPause() {
		super.onPause();
		System.out.println(getClass().getSimpleName()+"LogonActivity.onPause()");
		//Activity 종료
		finish();
	}

	////////////////////////////////////////////////////////////////////////////////////
	// 종료 EndToast Bean 사용
	EndToast endToast = new EndToast(this);

	// Call Back Method 이용 취소버튼이용 App. 종료
	@Override
	public void onBackPressed() {

		// 종료 EndToast Bean 사용
		endToast.showEndToast("'취소' 버튼 한번더 누르시면 종료합니다. ");

	}
	////////////////////////////////////////////////////////////////////////////////////

}// end of Activity