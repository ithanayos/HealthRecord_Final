package utcc.som.cken.tae.healthrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CheckhealthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkhealth);
    }//onCreate

    public void clickCheckpress(View view) {
        startActivity(new Intent(this, CheckpressActivity.class));
    }
    public void clickCheckheart(View view) {
        startActivity(new Intent(this, CheckheartActivity.class));
    }
    public void clickCheckglucose(View view) {
        startActivity(new Intent(this, CheckglucoseActivity.class));
    }
    public void clickCheckbmi(View view) {
        startActivity(new Intent(this, CheckbmiActivity.class));
    }

}//Main Class
