package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.KonsultasiCfUser;

public class HasilDiagnosaActivity extends AppCompatActivity {
    private static final String TAG = "HasilDiagnosaActivity";
    ArrayList<Gejala> gejalaArrayList = new ArrayList<>();
    ArrayList<KonsultasiCfUser> konsultasiCfUsers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        gejalaArrayList = bundle.getParcelableArrayList("GEJALA");
        konsultasiCfUsers = bundle.getParcelableArrayList("CFUSER");


    }
}
