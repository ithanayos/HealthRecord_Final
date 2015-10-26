package utcc.som.cken.tae.healthrecord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class SignUpActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText, passwordEditText, nameEditText,
            emailEditText, weightEditText, heightEditText; // ใช้ในการกรอก
    private RadioGroup sexRadioGroup;
    private Spinner ageSpinner;

    private String userString, passwordString, nameString,
            emailString, weightString, heightString, sexString, ageString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Bind Widget

        bindWidget();

        //createSpinner();

    } // OnCreate

    private void createSpinner() {

    } // createSpinner

    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.edtUserSign);
        passwordEditText = (EditText)findViewById(R.id.edtPassSign);
        nameEditText = (EditText) findViewById(R.id.edtNameSign);
        emailEditText = (EditText) findViewById(R.id.edtEmailSign);
        //ageSpinner = (Spinner) findViewById(R.id.spnAge);
        weightEditText = (EditText) findViewById(R.id.edtWeight);
        heightEditText = (EditText) findViewById(R.id.edtHeight);


    }

    public void clickSave(View view){

        //Get Value From Edit text
        userString = userEditText.getText().toString().trim(); //trim ตัดช่องว่างข้างหน้าหลังอัตโนมัติ
        passwordString = passwordEditText.getText().toString().trim();
        nameString = nameEditText.getText().toString().trim();
        emailString = emailEditText.getText().toString().trim();
        weightString = weightEditText.getText().toString().trim();
        heightString = heightEditText.getText().toString().trim();

        //Check Zero
        if (userString.equals("") || passwordString.equals("") || nameString.equals("") || emailString.equals("") || weightString.equals("") || heightString.equals("") ) {

            //Have Space
            MyDialog objMyDialog = new MyDialog();
            objMyDialog.errorDialog(SignUpActivity.this, "มีช่องว่าง", "กรุณากรอกให้ครบ");

        } else {

            //No Space
        }





    } // clickSave



} //Main Class
