package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import asap20.com.sistempakarpepaya.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    LinearLayout btnDiagnosa, btnPenyakit, btnRiwayat, btnBantuan, btnAbout, btnKeluar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDiagnosa = findViewById(R.id.btnDiagnosa);
        btnPenyakit = findViewById(R.id.btnPenyakit);
        btnRiwayat = findViewById(R.id.btnRiwayat);
        btnBantuan = findViewById(R.id.btnBantuan);
        btnAbout = findViewById(R.id.btnAbout);
        btnKeluar = findViewById(R.id.btnKeluar);

        btnDiagnosa.setOnClickListener(this);
        btnPenyakit.setOnClickListener(this);
        btnRiwayat.setOnClickListener(this);
        btnBantuan.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
        btnKeluar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDiagnosa:

                break;
            case R.id.btnPenyakit:
                break;
            case R.id.btnRiwayat:
                break;
            case R.id.btnBantuan:
                break;
            case R.id.btnAbout:
                break;
            case R.id.btnKeluar:
                finishAffinity();
                System.exit(0);
                break;
        }

    }
}
