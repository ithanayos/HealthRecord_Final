package utcc.som.cken.tae.healthrecord;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private UserTABLE objUserTABLE;
    private String TAG ="Health", userString, passwordString;

    private MyDialog objMyDialog = new MyDialog();

    private EditText userEditText, passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Create & Connected Database
        createDatabase();

        //Test Add Value SQLite
        //testAddValue();

        deleteAllData();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

        bindWidget();



    } //OnCreate



    //Active When Restart
    @Override
    protected void onRestart() {
        super.onRestart();

        deleteAllData();

        synJSONtoSQLite();
    }

    private void deleteAllData() {

        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase
                ("Health.db", MODE_PRIVATE, null);
        objSqLiteDatabase.delete("userTABLE", null, null);

    } // deleteAllData

    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.edtUser);
        passwordEditText = (EditText) findViewById(R.id.edtPass);


    }

    private void synJSONtoSQLite() {

//Setup Policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);

        //1. Create InputStream
        InputStream objInputStream = null;
        String strJSON = null;
        HttpPost objHttpPost = null;

        try {

            HttpClient objHttpClient = new DefaultHttpClient();
            objHttpPost = new HttpPost("http://swiftcodingthai.com/tae/get_data_user_tae.php");
            HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
            HttpEntity objHttpEntity = objHttpResponse.getEntity();
            objInputStream = objHttpEntity.getContent();

        } catch (Exception e) {
            Log.d(TAG, "InputStream ==> " + e.toString());
        }

        //2. Create JSON String
        try {

            BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream, "UTF-8"));
            StringBuilder objStringBuilder = new StringBuilder();
            String strLine = null;

            while ((strLine = objBufferedReader.readLine()) != null) {
                objStringBuilder.append(strLine);
            }   // while

            objInputStream.close();
            strJSON = objStringBuilder.toString();

        } catch (Exception e) {
            Log.d(TAG, "JSON String ==> " + e.toString());
        }


        //3. Update to SQLite
        try {

            final JSONArray objJsonArray = new JSONArray(strJSON);

            for (int i = 0; i < objJsonArray.length(); i++) {

                JSONObject object = objJsonArray.getJSONObject(i);
                String strUser = object.getString("User");
                String strPassword = object.getString("Password");
                String strName = object.getString("Name");
                String strAge = object.getString("Age");
                String strSex = object.getString("Sex");
                String strWeight = object.getString("Weight");
                String strHeight = object.getString("Height");
                String strEmail = object.getString("Email");
                objUserTABLE.addNewUser(strUser, strPassword, strName,
                        strAge, strSex, strWeight, strHeight, strEmail);

            }   // for

        } catch (Exception e) {
            Log.d(TAG, "Update SQLite ==> " + e.toString());
        }

    }   // synJSONtoSQLite


    public void clickSignIn(View view) {
        String strUser = userEditText.getText().toString().trim();
        String strPassword = passwordEditText.getText().toString().trim();

        //Check Zero
        if (strUser.equals("") || strPassword.equals("") ) {

            //Have Space

            objMyDialog.errorDialog(MainActivity.this, "Have Space", "Please Fill All Every Blank");
        } else {

            //No Space
            checkUserPassword(strUser, strPassword);

        }



    } // clickSignIn

    private void checkUserPassword(String strUser, String strPassword) {
        try {
            String[] strMyResult = objUserTABLE.searchUser(strUser);
            if (strPassword.equals(strMyResult[2])) {
                //Password True
                Intent objIntent = new Intent(MainActivity.this, MyServiceActivity.class);
                objIntent.putExtra("Name", strMyResult[3]);
                startActivity(objIntent);
                finish();
                //wecomeDialog(strMyResult[3]);
            } else {
                //Password False

                objMyDialog.errorDialog(MainActivity.this,"Password False", "Please Try Again Password False");

            }

        } catch (Exception e) {
            MyDialog objMyDialog = new MyDialog();
            objMyDialog.errorDialog(MainActivity.this, "User False", "ไม่มี " + strUser + " ใน ฐานข้อมูลของเรา");

        }


    } //checkUserPassword

    private void wecomeDialog(final String strName) {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_question);
        objBuilder.setTitle("Welcome");
        objBuilder.setMessage("ยินดีต้อนรับ " + strName + "\n" + "สู่ระบบของเรา");
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent objIntent = new Intent(MainActivity.this, HomeActivity.class);
                objIntent.putExtra("Name", strName);    //Name เป็น Key ที้่ใช้ในการโยน  Data ไปอีกหน้า
                startActivity(objIntent);

                dialogInterface.dismiss();
                finish();   // เมื่อกด Undo ที่โทรศัพท์ ให้ออกจาก App เลย ต้อง Login ใหม่
            }
        });
        objBuilder.show();
    } //welcomeDialog


    public void clickSignUp(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    } // clickSignUp

    private void testAddValue() {

        objUserTABLE.addNewUser("User", "Password", "Name", "Age", "Sex", "Weight", "Height", "Email");

    }

    private void createDatabase() {
        objUserTABLE = new UserTABLE(this); // this การเรียกใช้ Constructor
    }

} //Main Class
