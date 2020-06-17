package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.HasilKonsultasiUser;
import asap20.com.sistempakarpepaya.models.Konsultasi;
import asap20.com.sistempakarpepaya.models.KonsultasiCfUser;
import asap20.com.sistempakarpepaya.models.Penyakit;
import asap20.com.sistempakarpepaya.models.response.BaseResponse;
import asap20.com.sistempakarpepaya.models.response.DetailKonsultasiResponse;
import asap20.com.sistempakarpepaya.models.response.KonsultasiResponse;
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
    ArrayList<Konsultasi> konsultasis = new ArrayList<>();
    RecyclerView rvHasilGejala;
    RecyclerView rvHasilKonsultasi;
    HasilGejalaAdapter hasilGejalaAdapter;
    HasilKonsultasiAdapter hasilKonsultasiAdapter;
    TextView hasilkonsultasi, view3;
    String nilai, penyakit, idpenyakit;
    Double m;
    Button btnsolusi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);

        rvHasilGejala = findViewById(R.id.rv_hasil_gejala);
        rvHasilKonsultasi = findViewById(R.id.rv_hasil_konsultasi);
        hasilkonsultasi = findViewById(R.id.hasilkonsultasi);
        view3 = findViewById(R.id.view3);
        btnsolusi = findViewById(R.id.btn_hasildiagnosa);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        view3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HasilDiagnosaActivity.this, MainActivity.class);
                //intent.putExtra("IDPENYAKIT",hasilKonsultasiUsers.get(0).getIdPenyakit());


                startActivity(intent);
            }
        });
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        gejalaArrayList = bundle.getParcelableArrayList("GEJALA");
        konsultasiCfUsers = bundle.getParcelableArrayList("CFUSER");
        hasilKonsultasiUsers = bundle.getParcelableArrayList("HASILKONSULTASI");
        konsultasis = bundle.getParcelableArrayList("KONSULTASI");

        for (int a = 0; a < hasilKonsultasiUsers.size(); a++) {
            Log.d(TAG, "onCreate: " + hasilKonsultasiUsers.get(a).getIdPenyakit() + " " + hasilKonsultasiUsers.get(a).getNilaiCf());
            idpenyakit = hasilKonsultasiUsers.get(0).getIdPenyakit();
        }

        initHasilGejala();
        initHasilKonsultasi();
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

    private void inputData() {

        Call<BaseResponse> calls = apiInterface.createKonsultasi(
                konsultasis.get(0).getNama_petani(),
                konsultasis.get(0).getNo_telpon() + " ",
                konsultasis.get(0).getAlamat_petani(),
                hasilkonsultasi.getText().toString(),
                konsultasis.get(0).getTanggal()
        );
        calls.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (Boolean.valueOf(response.body().getError())) {
                    Log.d(TAG, "onResponse: Input " + response.body().getMessage());
                } else {
                    Log.d(TAG, "onResponse: Input " + response.body().getMessage());
                    Call<KonsultasiResponse> call1 = apiInterface.getKonsultasis();
                    call1.enqueue(new Callback<KonsultasiResponse>() {
                        @Override
                        public void onResponse(Call<KonsultasiResponse> call, Response<KonsultasiResponse> response) {
                            if (Boolean.parseBoolean(response.body().getError())) {
                                Log.d(TAG, "onResponse: Select ");
                            } else {
                                List<Konsultasi> konsultasiList = response.body().getKonsultasis();
                                Log.d(TAG, "onResponse: Select " + konsultasiList.get(konsultasiList.size() - 1).getId_konsultasi());
                                for (int a=0; a<gejalaArrayList.size(); a++) {
                                    Log.d(TAG, "onResponse: Gejala \n" + 9
                                            + "\n" + gejalaArrayList.get(a).getId_gejala());
                                    Call<BaseResponse> call2 = apiInterface.createDetailKonsultasi(
                                            konsultasiList.get(konsultasiList.size()-1).getId_konsultasi(),
                                            gejalaArrayList.get(a).getId_gejala());
                                    call2.enqueue(new Callback<BaseResponse>() {
                                        @Override
                                        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                            if (Boolean.parseBoolean(response.body().getError())) {
                                                Log.d(TAG, "onResponse: Detail " + response.body().getMessage());
                                            } else {
                                                Log.d(TAG, "onResponse: Detail " + response.body().getMessage());
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<BaseResponse> call, Throwable t) {
                                            Log.d(TAG, "onFailure: " + t);
                                        }
                                    });
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<KonsultasiResponse> call, Throwable t) {
                            Log.d(TAG, "onFailure: " + t);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }


    private void initHasilGejala() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rvHasilGejala.setLayoutManager(linearLayoutManager);
        hasilGejalaAdapter = new HasilGejalaAdapter(HasilDiagnosaActivity.this, konsultasiCfUsers);
        rvHasilGejala.setAdapter(hasilGejalaAdapter);
        hasilGejalaAdapter.notifyDataSetChanged();
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
                    for (int a = 0; a < penyakits.size(); a++) {
                        if (penyakits.get(a).getId_penyakit().trim().equals(hasilKonsultasiUsers.get(0).getIdPenyakit())) {
                            penyakit = penyakits.get(a).getNama_penyakit();
                            m = Double.valueOf(hasilKonsultasiUsers.get(0).getNilaiCf());
                        }

                        Log.d(TAG, "onCreate: " + hasilKonsultasiUsers.get(0).getIdPenyakit() + " " + hasilKonsultasiUsers.get(0).getNilaiCf());
                    }
                    if (m <= 0.35) {
                        nilai = "Tidak Tahu";


                    } else if (m <= 0.55) {
                        nilai = "Mungkin";
                    } else if (m <= 0.75) {
                        nilai = "Kemungkinan Besar";
                    } else if (m <= 0.95) {
                        nilai = "Hampir Pasti";
                    } else if (m <= 1) {
                        nilai = "Pasti";
                    }
                    hasilkonsultasi.setText("Berdasarkan data diatas penyakit yang mungkin terjadi yaitu: \n\nPenyakit: " + penyakit
                            + " \nDengan Bobot :" + String.valueOf(m * 100) + "%" + " \n\nserta tingkat keyakinannya adalah  " + nilai);
                    Log.d(TAG, "onCreate: " + m + nilai);
                    inputData();

                }


            }


            @Override
            public void onFailure(Call<PenyakitResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    private void initHasilKonsultasi() {
        Call<PenyakitResponse> call = apiInterface.getPenyakits();
        call.enqueue(new Callback<PenyakitResponse>() {
            @Override
            public void onResponse(Call<PenyakitResponse> call, Response<PenyakitResponse> response) {
                if (Boolean.valueOf(response.body().getError())) {
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
