package utcc.som.cken.tae.healthrecord;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AboutmeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutme);
    }//onCreate

    public void clickBackHome(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }
}//main class
