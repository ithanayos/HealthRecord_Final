package utcc.som.cken.tae.healthrecord;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecordActivity extends AppCompatActivity {

    //Explicit
    private TextView showTimeTextView;
    private String idUserString,currentTimeString, sleepString = null,
    breakfastString = null, lunchString = null, dinnerString = null, timeExerciseString = null, typeExerciseString = null, drinkWaterString = null, weightString = null;
    private Spinner sleepSpinner, exerciseSpinner, drinkWaterSpinner;
    private EditText breakfastEditText, lunchEditText, dinnerEditText, timeExerciseEditText ,typeExerciseEditText ,weightEditText;
    private String strNameUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        //bindWidget();
        bindWidget();

        //show Time
        showTime();

        //Create Sleep Spinner
        createSleepSpinner();

        //Create execise Spinner
        createExerciseSpinner();

        createDrinkWaterSpinner();

        showUser();



    } //onCreate



    private void showUser() {
        strNameUser = getIntent().getStringExtra("NameUser");
        TextView userTextView = (TextView) findViewById(R.id.textView18);
        userTextView.setText(strNameUser);

    }


    private void createDrinkWaterSpinner() {

        final String[] strDrinkWater = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        ArrayAdapter<String> drinkwaterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strDrinkWater);
        drinkWaterSpinner.setAdapter(drinkwaterAdapter);

        drinkWaterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                drinkWaterString = strDrinkWater[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                drinkWaterString = strDrinkWater[0];
            }
        });
    }

    private void createExerciseSpinner() {
        final String[] strExercise = new String[5];
        strExercise[0] = "วิ่ง";
        strExercise[1] = "ปั่นจักรยาน";
        strExercise[2] = "ว่ายน้ำ";
        strExercise[3] = "โยคะ";
        strExercise[4] = "อื่นๆ";


        ArrayAdapter<String> exerciseAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strExercise);
        exerciseSpinner.setAdapter(exerciseAdapter);

        exerciseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                typeExerciseString = strExercise[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                typeExerciseString = strExercise[0];

            }
        });

    }

    public void clickSaveRecord(View view) {

        breakfastString = breakfastEditText.getText().toString().trim();
        lunchString = lunchEditText.getText().toString().trim();
        dinnerString = dinnerEditText.getText().toString().trim();
        timeExerciseString = timeExerciseEditText.getText().toString().trim();
        weightString = weightEditText.getText().toString().trim();


        confirmValue();


    }

    private void createSleepSpinner() {

        final String[] strSleep = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};

        ArrayAdapter<String> sleepAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strSleep);
        sleepSpinner.setAdapter(sleepAdapter);
        sleepSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sleepString = strSleep[1];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                sleepString = strSleep[0];

            }
        });

    }


    private void showTime() {

        DateFormat objDateFormat = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
        Date currentDate = new Date();
        currentTimeString = objDateFormat.format(currentDate);
        showTimeTextView.setText(currentTimeString);

    }

    private void bindWidget() {
        showTimeTextView = (TextView) findViewById(R.id.txtShowTime);
        sleepSpinner = (Spinner) findViewById(R.id.spinner);
        breakfastEditText = (EditText) findViewById(R.id.editText);
        lunchEditText = (EditText) findViewById(R.id.editText2);
        dinnerEditText = (EditText) findViewById(R.id.editText3);
        exerciseSpinner = (Spinner) findViewById(R.id.spinner3);
        timeExerciseEditText = (EditText) findViewById(R.id.editText4);
        drinkWaterSpinner = (Spinner) findViewById(R.id.spinner4);
        weightEditText = (EditText) findViewById(R.id.editText5);

    }


    private void confirmValue() {
        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setIcon(R.drawable.icon_question);
        objBuilder.setTitle("Confirm Value");
        objBuilder.setMessage("Sleep = " + sleepString
                + "\n" + "Breakfast = " + breakfastString
                + "\n" + "Lunch = " + lunchString
                + "\n" + "Dinner = " + dinnerString
                + "\n" + "TypeExercise = " + typeExerciseString
                + "\n" + "TimeExercise = " + timeExerciseString
                + "\n" + "DrinkWater = " + drinkWaterString
                + "\n" + "Weight = " + weightString);
        objBuilder.setCancelable(false); // Undo ไม่ได้
        objBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

            }
        });
        objBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Update to MySQL
                updateToMySQL();
                dialogInterface.dismiss();
            }
        });
        objBuilder.show();

    }

    private void updateToMySQL() {

        //Setup policy
        StrictMode.ThreadPolicy myPolicy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(myPolicy);


        //Update Value
        try {

            ArrayList<NameValuePair> objNameValuePairs = new ArrayList<NameValuePair>();
            objNameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            //objNameValuePairs.add(new BasicNameValuePair("idUser", strIDUser));
            objNameValuePairs.add(new BasicNameValuePair("Sleep", sleepString));
            objNameValuePairs.add(new BasicNameValuePair("Breakfast", breakfastString));
            objNameValuePairs.add(new BasicNameValuePair("Lunch", lunchString));
            objNameValuePairs.add(new BasicNameValuePair("Dinner", dinnerString));
            objNameValuePairs.add(new BasicNameValuePair("TypeExercise", typeExerciseString));
            objNameValuePairs.add(new BasicNameValuePair("TimeExercise", timeExerciseString));
            objNameValuePairs.add(new BasicNameValuePair("DrinkWater", drinkWaterString));
            objNameValuePairs.add(new BasicNameValuePair("Weight", weightString));
            objNameValuePairs.add(new BasicNameValuePair("NameUser", strNameUser));

            HttpClient objHttpClient = new DefaultHttpClient();
            HttpPost objHttpPost = new HttpPost("http://swiftcodingthai.com/tae/add_data_record_tae.php");
            objHttpPost.setEntity(new UrlEncodedFormEntity(objNameValuePairs, "UTF-8"));
            objHttpClient.execute(objHttpPost);

            Toast.makeText(RecordActivity.this, "Update New Value Successful", Toast.LENGTH_LONG);

            finish();

        } catch (Exception e) {
            Toast.makeText(RecordActivity.this, "Cannot Update To MySQL", Toast.LENGTH_LONG).show();

        }



    } // UpdateToMySQL


} // Main Class
