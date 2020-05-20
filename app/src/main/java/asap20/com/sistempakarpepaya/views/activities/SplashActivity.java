package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;

import asap20.com.sistempakarpepaya.R;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = "SplashActivity";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);
        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long l) {
                progressBar.animate();
                progressBar.setVisibility(ProgressBar.VISIBLE);
            }

            @Override
            public void onFinish() {
                progressBar.setVisibility(ProgressBar.GONE);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        }.start();

    }
}
