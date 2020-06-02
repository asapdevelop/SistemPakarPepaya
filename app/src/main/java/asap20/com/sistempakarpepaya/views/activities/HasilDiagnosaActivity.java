package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.HasilKonsultasiUser;
import asap20.com.sistempakarpepaya.models.KonsultasiCfUser;
import asap20.com.sistempakarpepaya.views.adapter.HasilGejalaAdapter;

public class HasilDiagnosaActivity extends AppCompatActivity {
    private static final String TAG = "HasilDiagnosaActivity";
    ArrayList<Gejala> gejalaArrayList = new ArrayList<>();
    ArrayList<KonsultasiCfUser> konsultasiCfUsers = new ArrayList<>();
    ArrayList<HasilKonsultasiUser> hasilKonsultasiUsers = new ArrayList<>();
    RecyclerView rvHasilGejala;
    HasilGejalaAdapter hasilGejalaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);

        rvHasilGejala = findViewById(R.id.rv_hasil_gejala);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        gejalaArrayList = bundle.getParcelableArrayList("GEJALA");
        konsultasiCfUsers = bundle.getParcelableArrayList("CFUSER");
        hasilKonsultasiUsers = bundle.getParcelableArrayList("HASILKONSULTASI");

        for (int a = 0; a<hasilKonsultasiUsers.size(); a++){
            Log.d(TAG, "onCreate: " + hasilKonsultasiUsers.get(a).getIdPenyakit() + " " + hasilKonsultasiUsers.get(a).getNilaiCf());
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvHasilGejala.setLayoutManager(linearLayoutManager);
        hasilGejalaAdapter = new HasilGejalaAdapter(HasilDiagnosaActivity.this, konsultasiCfUsers);
        rvHasilGejala.setAdapter(hasilGejalaAdapter);
        hasilGejalaAdapter.notifyDataSetChanged();
    }
}
