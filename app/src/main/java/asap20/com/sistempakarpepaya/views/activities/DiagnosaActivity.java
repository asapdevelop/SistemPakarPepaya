package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.HasilKonsultasiUser;
import asap20.com.sistempakarpepaya.models.Konsultasi;
import asap20.com.sistempakarpepaya.models.KonsultasiCfUser;
import asap20.com.sistempakarpepaya.models.Pengetahuan;
import asap20.com.sistempakarpepaya.models.response.GejalaResponse;
import asap20.com.sistempakarpepaya.models.response.PengetahuanResponse;
import asap20.com.sistempakarpepaya.rest.ApiClient;
import asap20.com.sistempakarpepaya.rest.ApiInterface;
import asap20.com.sistempakarpepaya.views.adapter.DiagnosaAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiagnosaActivity extends AppCompatActivity {
    private static final String TAG = "DiagnosaActivity";

    ImageView imgBack;
    RecyclerView rvDiagnosa;
    Button btnDiagnosa;
    ApiInterface apiInterface;
    ProgressDialog dialog;
    DiagnosaAdapter diagnosaAdapter;
    ArrayList<Gejala> gejalaArrayList = new ArrayList<>();
    ArrayList<KonsultasiCfUser> konsultasiCfUsers = new ArrayList<>();
    ArrayList<Pengetahuan> pengetahuanArrayList = new ArrayList<>();
    ArrayList<HasilKonsultasiUser> hasilKonsultasiUsers = new ArrayList<>();
    String nama, nohp, alamat;
    private AlertDialog.Builder alertDialog;
    private LayoutInflater inflater;
    private View dialogView;
    private MaterialEditText edtNama, edtHp, edtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);

        gejalaArrayList.clear();
        konsultasiCfUsers.clear();
        pengetahuanArrayList.clear();
        hasilKonsultasiUsers = new ArrayList<>();

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        dialog = new ProgressDialog(DiagnosaActivity.this);
        dialog.setMessage("Memuat Data..");
        dialog.show();

        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        rvDiagnosa = findViewById(R.id.rv_diagnosa);
        btnDiagnosa = findViewById(R.id.btn_diagnosa);

        btnDiagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (diagnosaAdapter.gejalaArrayList.size() < 2) {
                    Toast.makeText(DiagnosaActivity.this, "Pilih Minimal 2 Gejala", Toast.LENGTH_SHORT).show();
                } else {
                    if (diagnosaAdapter.gejalaArrayList.size() != diagnosaAdapter.getJumlahKonsul()) {
                        Log.d(TAG, "onClick: Gejala " + diagnosaAdapter.gejalaArrayList.size() + "\nKonsultasi " + diagnosaAdapter.getJumlahKonsul());
                        Toast.makeText(DiagnosaActivity.this, "Lengkapi Diagnosa Anda..", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int a = 0; a < diagnosaAdapter.gejalaArrayList.size(); a++) {
                            gejalaArrayList.add(diagnosaAdapter.gejalaArrayList.get(a));
                        }
                        for (int b = 0; b < diagnosaAdapter.konsultasiCfUsers.size(); b++) {
                            konsultasiCfUsers.add(diagnosaAdapter.konsultasiCfUsers.get(b));
                        }
                        Collections.sort(gejalaArrayList,
                                new Comparator<Gejala>() {
                                    @Override
                                    public int compare(Gejala gejala, Gejala t1) {
                                        return gejala.getId_gejala().compareTo(t1.getId_gejala());
                                    }
                                });
                        Collections.sort(konsultasiCfUsers, new Comparator<KonsultasiCfUser>() {
                            @Override
                            public int compare(KonsultasiCfUser konsultasiCfUser, KonsultasiCfUser t1) {
                                return konsultasiCfUser.getId_penyakit().compareTo(t1.getId_penyakit());
                            }
                        });
                        for (int a = 0; a < konsultasiCfUsers.size(); a++) {
                            Log.d(TAG, "onResponse: " + konsultasiCfUsers.get(a).getId_gejala()
                                    + " " + konsultasiCfUsers.get(a).getCf_user()
                                    + " " + konsultasiCfUsers.get(a).getHasil_cf());
                        }
                        hitungCf();
                    }
                }
            }
        });
        initRecyclerView();
    }

    private void hitungCf() {
        String idPenyakit = "";
        Double v1 = 0d, v2 = 0d, hasil = 0d;
        for (int a = 0; a < konsultasiCfUsers.size(); a++) {
            if (!konsultasiCfUsers.get(a).getId_penyakit().equals(idPenyakit)) {
                idPenyakit = konsultasiCfUsers.get(a).getId_penyakit();
                v1 = konsultasiCfUsers.get(a).getHasil_cf();
                hasilKonsultasiUsers.add(new HasilKonsultasiUser(
                        idPenyakit,
                        v1
                ));
            } else {
                v2 = konsultasiCfUsers.get(a).getHasil_cf();
                hasil = v1 + (v2 * (1 - v1));
                for (int z = 0; z < hasilKonsultasiUsers.size(); z++) {
                    if (hasilKonsultasiUsers.get(z).getIdPenyakit().trim().equals(idPenyakit)) {
                        hasilKonsultasiUsers.remove(z);
                    }
                }
                hasilKonsultasiUsers.add(new HasilKonsultasiUser(
                        idPenyakit,
                        hasil
                ));
            }
        }
        Collections.sort(hasilKonsultasiUsers, new Comparator<HasilKonsultasiUser>() {
            @Override
            public int compare(HasilKonsultasiUser hasilKonsultasiUser, HasilKonsultasiUser t1) {
                return t1.getNilaiCf().compareTo(hasilKonsultasiUser.getNilaiCf());
            }
        });
        Intent intent = new Intent(DiagnosaActivity.this, HasilDiagnosaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("GEJALA", gejalaArrayList);
        bundle.putParcelableArrayList("CFUSER", konsultasiCfUsers);
        bundle.putParcelableArrayList("HASILKONSULTASI", hasilKonsultasiUsers);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void initRecyclerView() {

        Call<GejalaResponse> call = apiInterface.getGejalas();
        call.enqueue(new Callback<GejalaResponse>() {
            @Override
            public void onResponse(Call<GejalaResponse> call, Response<GejalaResponse> response) {
                Log.d(TAG, "onResponse: " + response.body().getError());
                if (response.body() == null) {
                    Log.d(TAG, "onResponse: wwwww");
                } else {
                    if (Boolean.valueOf(response.body().getError())) {
                        Log.d(TAG, "onResponse: " + response.body().getMessage());
                    } else {
                        final List<Gejala> gejalas = response.body().getGejalas();
                        Call<PengetahuanResponse> call1 = apiInterface.getPengetahuans();
                        call1.enqueue(new Callback<PengetahuanResponse>() {
                            @Override
                            public void onResponse(Call<PengetahuanResponse> call, Response<PengetahuanResponse> response) {
                                if (Boolean.valueOf(response.body().getError())) {
                                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                                } else {
                                    List<Pengetahuan> pengetahuans = response.body().getPengetahuans();
                                    diagnosaAdapter = new DiagnosaAdapter(DiagnosaActivity.this, gejalas, pengetahuans);
                                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DiagnosaActivity.this, LinearLayoutManager.VERTICAL, false);
                                    rvDiagnosa.addItemDecoration(new DividerItemDecoration(DiagnosaActivity.this, DividerItemDecoration.VERTICAL));
                                    rvDiagnosa.setLayoutManager(linearLayoutManager);
                                    rvDiagnosa.setHasFixedSize(true);
                                }
                                rvDiagnosa.setAdapter(diagnosaAdapter);
                                diagnosaAdapter.notifyDataSetChanged();
                                diagnosaAdapter.setOnProgressListener(new DiagnosaAdapter.OnProgress() {
                                    @Override
                                    public void onProgress(Boolean boo, final int position) {
                                        if (boo) {
                                            dialog.setMessage("Memuat Data..." + position + "/" + diagnosaAdapter.getItemCount());
                                        } else {
                                            dialog.dismiss();
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onFailure(Call<PengetahuanResponse> call, Throwable t) {
                                Log.d(TAG, "onFailure: " + t);
                            }
                        });
                        Log.d(TAG, "onResponse: Gejala Size " + gejalas.get(0).getNama_gejala());

                    }
                }
            }

            @Override
            public void onFailure(Call<GejalaResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gejalaArrayList.clear();
        konsultasiCfUsers.clear();
        pengetahuanArrayList.clear();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
