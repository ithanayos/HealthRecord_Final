package utcc.som.cken.tae.healthrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    //Explicit
    private String recordString, recorddiseaseString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /*Button button = (Button) findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent objIntent = new Intent(HomeActivity.this, RecordActivity.class);
                startActivity(objIntent);

                finish();
            }
        });*/

    }//onCreate

    public void clickRecord(View view) {
        startActivity(new Intent(this, RecordActivity.class));
    }

    public void clickDisease(View view) {
        startActivity(new Intent(this, DiseaseActivity.class));
    }

    public void clickCheckhealth(View view) {
        startActivity(new Intent(this, CheckhealthActivity.class));
    }

    public void clickAboutme(View view) {
        startActivity(new Intent(this, AboutmeActivity.class));
    }


} //Main Class
