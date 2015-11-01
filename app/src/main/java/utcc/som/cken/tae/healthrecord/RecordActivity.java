package utcc.som.cken.tae.healthrecord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordActivity extends AppCompatActivity {

    //Explicit
    private TextView showTimeTextView;
    private String currentTimeString, sleepString,
    breakfastString = null, lunchString = null, dinnerString = null, timeExerciseString = null, typeExerciseString = null, drinkWaterString = null;
    private Spinner sleepSpinner, exerciseSpinner, drinkWaterSpinner;
    private EditText breakfastEditText, lunchEditText, dinnerEditText, timeExerciseEditText ,typeExerciseEditText;


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


    } //onCreate

    private void createDrinkWaterSpinner() {

        final String[] strDrinkWater = {"0 - 3 แก้ว", "3 - 6 แก้ว", "6 - 9 แก้ว", "9 - 12 แก้ว", "12 - 15 แก้ว"};

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
        final String[] strExercise = new String[3];
        strExercise[0] = "เบา";
        strExercise[1] = "ปานกลาง";
        strExercise[2] = "หนัก";

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


    }

    private void createSleepSpinner() {

        final String[] strSleep = {"0 - 3 ชั่วโมง", "3 - 6 ชั่วโมง", "6 - 9 ชั่วโมง", "9 - 12 ชั่วโมง", "12 - 15 ชั่วโมง"};

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

    }

} // Main Class
