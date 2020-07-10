package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.HasilKonsultasiUser;
import asap20.com.sistempakarpepaya.models.Konsultasi;
import asap20.com.sistempakarpepaya.models.KonsultasiCfUser;
import asap20.com.sistempakarpepaya.models.Penyakit;
import asap20.com.sistempakarpepaya.models.response.BaseResponse;
import asap20.com.sistempakarpepaya.models.response.KonsultasiResponse;
import asap20.com.sistempakarpepaya.models.response.PenyakitResponse;
import asap20.com.sistempakarpepaya.rest.ApiClient;
import asap20.com.sistempakarpepaya.rest.ApiInterface;
import asap20.com.sistempakarpepaya.views.adapter.HasilAdapter;
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
    ArrayList<HasilKonsultasiUser> hkUser = new ArrayList<>();
    RecyclerView rvHasilKonsultasi;
    HasilAdapter hasilAdapter;
    String penyakit, idpenyakit;
    Double m;
    View view;
    Button btnsolusi;
    TextView tvPenyakit, tvPercent;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);

        rvHasilKonsultasi = findViewById(R.id.rvHasil);
        view=findViewById(R.id.view);
        tvPenyakit = findViewById(R.id.tvPenyakit);
        tvPercent = findViewById(R.id.tvPercent);
        btnsolusi = findViewById(R.id.btn_hasildiagnosa);
        imgBack = findViewById(R.id.img_back);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        gejalaArrayList = bundle.getParcelableArrayList("GEJALA");
        konsultasiCfUsers = bundle.getParcelableArrayList("CFUSER");
        hasilKonsultasiUsers = bundle.getParcelableArrayList("HASILKONSULTASI");

        for (int a = 0; a < hasilKonsultasiUsers.size(); a++) {
            Log.d(TAG, "onCreate: " + hasilKonsultasiUsers.get(a).getIdPenyakit() + " " + hasilKonsultasiUsers.get(a).getNilaiCf());
            idpenyakit = hasilKonsultasiUsers.get(0).getIdPenyakit();
        }
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        initHasilKonsultasi();
        initDetail();
        btnsolusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HasilDiagnosaActivity.this, DetailPenyakitActivity.class);
                intent.putExtra("IDPENYAKIT", hasilKonsultasiUsers.get(0).getIdPenyakit());
                startActivity(intent);
            }
        });
    }


    private void initDetail() {
        Call<PenyakitResponse> call = apiInterface.getPenyakits();
        call.enqueue(new Callback<PenyakitResponse>() {
            @Override
            public void onResponse(Call<PenyakitResponse> call, Response<PenyakitResponse> response) {
                if (Boolean.valueOf(response.body().getError())) {
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                } else {
                    List<Penyakit> penyakits = response.body().getPenyakits();
                    for (int b=0; b<hasilKonsultasiUsers.size(); b++){
                        for (int a = 0; a < penyakits.size(); a++) {
                            if (b==0){
                                if (penyakits.get(a).getId_penyakit().trim().equals(hasilKonsultasiUsers.get(b).getIdPenyakit())) {
                                    Log.d(TAG, "onResponse: b==0 " + penyakits.get(a).getNama_penyakit());
                                    penyakit = penyakits.get(a).getNama_penyakit();
                                    m = Double.valueOf(hasilKonsultasiUsers.get(b).getNilaiCf());
                                    double hasil=m*100;

                                    tvPenyakit.setText(penyakit);
                                    tvPercent.setText(hasil + getResources().getString(R.string.percent));
                                }
                            } else {
                                if (penyakits.get(a).getId_penyakit().trim().equals(hasilKonsultasiUsers.get(b).getIdPenyakit())) {
                                    Log.d(TAG, "onResponse: b= "+ b + " " + penyakits.get(a).getNama_penyakit());
                                    penyakit = penyakits.get(a).getNama_penyakit();
                                    m = Double.valueOf(hasilKonsultasiUsers.get(b).getNilaiCf());
                                    hkUser.add(new HasilKonsultasiUser(
                                            hasilKonsultasiUsers.get(b).getIdPenyakit(),
                                            m));
                                }
                            }
                        }
                    }
                    hasilAdapter = new HasilAdapter(HasilDiagnosaActivity.this, penyakits, hkUser);
                    LinearLayoutManager linearLayoutManagers = new LinearLayoutManager(HasilDiagnosaActivity.this);
                    rvHasilKonsultasi.addItemDecoration(new DividerItemDecoration(HasilDiagnosaActivity.this, DividerItemDecoration.VERTICAL));
                    rvHasilKonsultasi.setLayoutManager(linearLayoutManagers);
                    rvHasilKonsultasi.setHasFixedSize(true);
                    rvHasilKonsultasi.setLayoutManager(linearLayoutManagers);
                }
                rvHasilKonsultasi.setAdapter(hasilAdapter);
                hasilAdapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<PenyakitResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
