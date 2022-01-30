package example.app02event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class EventBaseGetSet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_base_get_set);

        System.out.println("=========================");

        TextView textView = findViewById(R.id.textview);
        System.out.println("변경전 : "+textView.getText());
        textView.setText("TextView 내용 변경함");
        System.out.println("변경후 : "+textView.getText());

        System.out.println("=========================");

        EditText editText = findViewById(R.id.edittext);
        System.out.println("변경전 : "+editText.getText());
        editText.setText("EditText 내용 변경함");
        System.out.println("변경후 : "+editText.getText());

        System.out.println("=========================");

        CheckBox checkBox = findViewById(R.id.checkbox);
        System.out.println("변경전 CheckBox check 유무 : "+checkBox.isChecked() );
        checkBox.setChecked(true);
        System.out.println("변경후 CheckBox check 유무 : "+checkBox.isChecked() );

        System.out.println("=========================");

        RadioGroup radioGroup = findViewById(R.id.radiogroup);
        System.out.println("변경전 radioButton check id : "+radioGroup.getCheckedRadioButtonId());
        radioGroup.check(R.id.radioGroup_radiobutton01);
        System.out.println("변경후 RadioButton check id : "+radioGroup.getCheckedRadioButtonId());

        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        System.out.println("변경후 check된 Radio Button의 값 : "+radioButton.getText());
        System.out.println("변경후 check 유무 : "+radioButton.isChecked());

        System.out.println("=========================");

        Spinner spinner = findViewById(R.id.spinner);
        System.out.println("변경전");
        System.out.println("spiner 내부 item 수 : "+spinner.getCount());
        System.out.println("변경된 선택된 정보 : "+(String)spinner.getSelectedItem());

        String[] spinnerItems = {
                "스피너항목:국어",
                "스피너항목:영어",
                "스피너항목:수학",
                "스피너항목:기타"
        };

        ArrayAdapter<String> arrayAdapter = null;

//      [Case 1]
//      arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerItems);
        
//      [Case2]
//      arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//      [Case3]
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, spinnerItems);
        spinner.setAdapter(arrayAdapter);
        System.out.println("변경후");
        System.out.println("spiner 내부 item 수 : "+spinner.getCount());
        System.out.println("변경후 선택된 정보 : "+(String)spinner.getSelectedItem());
        System.out.println("=========================");
    }
}