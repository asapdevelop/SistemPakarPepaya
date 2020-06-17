package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.DetailKonsultasi;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.Konsultasi;
import asap20.com.sistempakarpepaya.models.response.DetailKonsultasiResponse;
import asap20.com.sistempakarpepaya.models.response.GejalaResponse;
import asap20.com.sistempakarpepaya.rest.ApiClient;
import asap20.com.sistempakarpepaya.rest.ApiInterface;
import asap20.com.sistempakarpepaya.views.adapter.HasilGejalaAdapter;
import asap20.com.sistempakarpepaya.views.adapter.RiwayatGejalaAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailRiwayatActivity extends AppCompatActivity {
    private static final String TAG = "DetailRiwayatActivity";
    ArrayList<Konsultasi> konsultasis = new ArrayList<>();
    TextView tvNama, tvHp, tvAlamat, tvHasil;
    RecyclerView rvGejala;
    RiwayatGejalaAdapter gejalaAdapter;
    ApiInterface apiInterface;
    List<Gejala> gejalaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Bundle bundle = getIntent().getExtras();
        konsultasis = bundle.getParcelableArrayList("KONSULTASI");
        tvNama = findViewById(R.id.dr_nama);
        tvHp = findViewById(R.id.dr_hp);
        tvAlamat = findViewById(R.id.dr_alamat);
        tvHasil = findViewById(R.id.dr_hasil);
        tvNama.setText(": " + konsultasis.get(0).getNama_petani());
        tvHp.setText(": " + konsultasis.get(0).getNo_telpon());
        tvAlamat.setText(": " + konsultasis.get(0).getAlamat_petani());
        tvHasil.setText(konsultasis.get(0).getHasil_konsultasi());

        rvGejala = findViewById(R.id.rv_dr_gejala);

        initRecyclerview();

    }

    private void initRecyclerview() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvGejala.setLayoutManager(linearLayoutManager);
        Call<DetailKonsultasiResponse> call = apiInterface.getDetailKonsultasi(konsultasis.get(0).getId_konsultasi());
        call.enqueue(new Callback<DetailKonsultasiResponse>() {
            @Override
            public void onResponse(Call<DetailKonsultasiResponse> call, Response<DetailKonsultasiResponse> response) {
                if (Boolean.valueOf(response.body().getError())) {
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                } else {
                    final List<DetailKonsultasi> detailKonsultasis = response.body().getKonsultasis();
                    Log.d(TAG, "onResponse: Detail Konsultasi " + detailKonsultasis.size());
                    Call<GejalaResponse> call1 = apiInterface.getGejalas();
                    call1.enqueue(new Callback<GejalaResponse>() {
                        @Override
                        public void onResponse(Call<GejalaResponse> call, Response<GejalaResponse> response) {
                            if (Boolean.valueOf(response.body().getError())) {
                                Log.d(TAG, "onResponse: " + response.body().getMessage());
                            } else {
                                List<Gejala> gejalas = response.body().getGejalas();

                                for (int a=0; a<detailKonsultasis.size();a++){
                                    Log.d(TAG, "onResponse: idGejalaDetail " + detailKonsultasis.get(a).getId_gejala());
                                    for (int b=0; b<gejalas.size(); b++){
                                        if (detailKonsultasis.get(a).getId_gejala().equals(gejalas.get(b).getId_gejala())){
                                            Log.d(TAG, "onResponse: same " + gejalas.get(b).getId_gejala());
                                            gejalaList.add(gejalas.get(b));
                                        }
                                    }
                                }

                            }
                            Collections.sort(gejalaList,
                                    new Comparator<Gejala>() {
                                        @Override
                                        public int compare(Gejala gejala, Gejala t1) {
                                            return gejala.getId_gejala().compareTo(t1.getId_gejala());
                                        }
                                    });
                            gejalaAdapter = new RiwayatGejalaAdapter(DetailRiwayatActivity.this, gejalaList);
                            rvGejala.setAdapter(gejalaAdapter);
                            gejalaAdapter.notifyDataSetChanged();
                            Log.d(TAG, "onResponse: Gejala list " + gejalaList.size() + " " + gejalaAdapter.getItemCount());
                        }

                        @Override
                        public void onFailure(Call<GejalaResponse> call, Throwable t) {

                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<DetailKonsultasiResponse> call, Throwable t) {

            }
        });
    }
}
