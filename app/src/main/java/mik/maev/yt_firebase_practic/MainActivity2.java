package mik.maev.yt_firebase_practic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;



public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }


    public void next_reg(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void next_activity_news(View view) {
        Intent intent = new Intent(this, News.class);
        startActivity(intent);
    }

    public void next_activity(View view) {
        Intent intent = new Intent(this, MapsAir.class);
        startActivity(intent);
    }

    public void next_activity_reis(View view) {
        Intent intent = new Intent(this, Reis.class);
        startActivity(intent);
    }
}
