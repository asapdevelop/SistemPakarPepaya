package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.HasilKonsultasiUser;
import asap20.com.sistempakarpepaya.models.KonsultasiCfUser;
import asap20.com.sistempakarpepaya.models.Penyakit;
import asap20.com.sistempakarpepaya.models.response.PenyakitResponse;
import asap20.com.sistempakarpepaya.rest.ApiClient;
import asap20.com.sistempakarpepaya.rest.ApiInterface;
import asap20.com.sistempakarpepaya.views.adapter.HasilGejalaAdapter;
import asap20.com.sistempakarpepaya.views.adapter.HasilKonsultasiAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilDiagnosaActivity extends AppCompatActivity {
    private static final String TAG = "HasilDiagnosaActivity";
    ApiInterface apiInterface;
    ArrayList<Gejala> gejalaArrayList = new ArrayList<>();
    ArrayList<KonsultasiCfUser> konsultasiCfUsers = new ArrayList<>();
    ArrayList<HasilKonsultasiUser> hasilKonsultasiUsers = new ArrayList<>();
    RecyclerView rvHasilGejala;
    RecyclerView rvHasilKonsultasi;
    HasilGejalaAdapter hasilGejalaAdapter;
    HasilKonsultasiAdapter hasilKonsultasiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);

        rvHasilGejala = findViewById(R.id.rv_hasil_gejala);
        rvHasilKonsultasi = findViewById(R.id.rv_hasil_konsultasi);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        gejalaArrayList = bundle.getParcelableArrayList("GEJALA");
        konsultasiCfUsers = bundle.getParcelableArrayList("CFUSER");
        hasilKonsultasiUsers = bundle.getParcelableArrayList("HASILKONSULTASI");

        for (int a = 0; a<hasilKonsultasiUsers.size(); a++){
            Log.d(TAG, "onCreate: " + hasilKonsultasiUsers.get(a).getIdPenyakit() + " " + hasilKonsultasiUsers.get(a).getNilaiCf());
        }

        initHasilGejala();
        initHasilKonsultasi();
    }

    private void initHasilGejala() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvHasilGejala.setLayoutManager(linearLayoutManager);
        hasilGejalaAdapter = new HasilGejalaAdapter(HasilDiagnosaActivity.this, konsultasiCfUsers);
        rvHasilGejala.setAdapter(hasilGejalaAdapter);
        hasilGejalaAdapter.notifyDataSetChanged();
    }

    private void initHasilKonsultasi() {
        Call<PenyakitResponse> call = apiInterface.getPenyakits();
        call.enqueue(new Callback<PenyakitResponse>() {
            @Override
            public void onResponse(Call<PenyakitResponse> call, Response<PenyakitResponse> response) {
                if (Boolean.valueOf(response.body().getError())){
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                } else {
                    List<Penyakit> penyakits = response.body().getPenyakits();
                    LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(HasilDiagnosaActivity.this);
                    rvHasilKonsultasi.setLayoutManager(linearLayoutManagers);
                    hasilKonsultasiAdapter = new HasilKonsultasiAdapter(HasilDiagnosaActivity.this, penyakits, hasilKonsultasiUsers);
                    rvHasilKonsultasi.setAdapter(hasilKonsultasiAdapter);
                    hasilKonsultasiAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<PenyakitResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }
}
