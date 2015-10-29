package utcc.som.cken.tae.healthrecord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordActivity extends AppCompatActivity {

    //Explicit
    private TextView showTimeTextView;
    private String currentTimeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        //bindWidget();
        bindWidget();

        //show Time
        showTime();


    } //onCreate

    private void showTime() {

        DateFormat objDateFormat = new SimpleDateFormat("yyyy/MM/dd HH/mm/ss");
        Date currentDate = new Date();
        currentTimeString = objDateFormat.format(currentDate);
        showTimeTextView.setText(currentTimeString);

    }

    private void bindWidget() {
        showTimeTextView = (TextView) findViewById(R.id.txtShowTime);

    }

} // Main Class
