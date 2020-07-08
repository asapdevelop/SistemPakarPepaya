package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Penyakit;

public class LihatData extends AppCompatActivity implements View.OnClickListener {
    LinearLayout btngejala, btnPenyakit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);
        btngejala = findViewById(R.id.btngejala);
        btnPenyakit = findViewById(R.id.btnPenyakit);
        btngejala.setOnClickListener(this);
        btnPenyakit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btngejala:
                Intent intent = new Intent(LihatData.this, Gejala2Activity.class);
                startActivity(intent);
                break;
            case R.id.btnPenyakit:
                Intent intent1 = new Intent(LihatData.this, PenyakitActivity.class);
                startActivity(intent1);
                break;

    }}}