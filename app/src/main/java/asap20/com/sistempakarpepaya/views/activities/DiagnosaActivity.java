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
    ArrayList<Konsultasi> konsultasis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);

        gejalaArrayList.clear();
        konsultasiCfUsers.clear();
        pengetahuanArrayList.clear();

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
                if (diagnosaAdapter.gejalaArrayList.size()<2){
                    Toast.makeText(DiagnosaActivity.this, "Pilih Minimal 2 Gejala", Toast.LENGTH_SHORT).show();
                } else {
                    if (diagnosaAdapter.gejalaArrayList.size() != diagnosaAdapter.konsultasiCfUsers.size()) {
                        Toast.makeText(DiagnosaActivity.this, "Lengkapi Diagnosa Anda..", Toast.LENGTH_SHORT).show();
                    } else {
                        for (int a = 0; a < diagnosaAdapter.gejalaArrayList.size(); a++) {
                            gejalaArrayList.add(diagnosaAdapter.gejalaArrayList.get(a));
                            konsultasiCfUsers.add(diagnosaAdapter.konsultasiCfUsers.get(a));
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
                                return konsultasiCfUser.getId_gejala().compareTo(t1.getId_gejala());
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

//        dialogPetani();

        initRecyclerView();

    }

    private void hitungCf() {
        Log.d(TAG, "hitungCf: ");
        Call<PengetahuanResponse> call = apiInterface.getPengetahuans();
        call.enqueue(new Callback<PengetahuanResponse>() {
            @Override
            public void onResponse(Call<PengetahuanResponse> call, Response<PengetahuanResponse> response) {
                if (Boolean.valueOf(response.body().getError())) {
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                } else {
                    List<Pengetahuan> pengetahuans = response.body().getPengetahuans();
                    Log.d(TAG, "onResponse: " + pengetahuans.size());
                    for (int a = 0; a < konsultasiCfUsers.size(); a++) {
                        for (int b = 0; b < pengetahuans.size(); b++) {
                            if (konsultasiCfUsers.get(a).getId_gejala().equals(pengetahuans.get(b).getId_gejala())) {
                                pengetahuanArrayList.add(pengetahuans.get(b));
                            }
                        }
                    }
                    Log.d(TAG, "Seleksi: " + pengetahuanArrayList.size());
                    Collections.sort(pengetahuanArrayList, new Comparator<Pengetahuan>() {
                        @Override
                        public int compare(Pengetahuan pengetahuan, Pengetahuan t1) {
                            return pengetahuan.getId_penyakit().compareTo(t1.getId_penyakit());
                        }
                    });

                    for (int a = 0; a < pengetahuanArrayList.size(); a++) {
                        if (a == 0) {
                            for (int b = 0; b < konsultasiCfUsers.size(); b++) {
                                if (pengetahuanArrayList.get(a).getId_gejala().trim().equals(konsultasiCfUsers.get(b).getId_gejala())) {
                                    hasilKonsultasiUsers.add(new HasilKonsultasiUser(pengetahuanArrayList.get(a).getId_penyakit(), konsultasiCfUsers.get(b).getHasil_cf()));
                                }
                            }
                        } else {
                            Double v1=0d, v2=0d, hasil=0d;
                            if (pengetahuanArrayList.get(a).getId_penyakit().trim().equals(hasilKonsultasiUsers.get(hasilKonsultasiUsers.size()-1).getIdPenyakit())) {
                                for (int b=0; b<konsultasiCfUsers.size();b++){
                                    if (pengetahuanArrayList.get(a).getId_gejala().trim().equals(konsultasiCfUsers.get(b).getId_gejala())){
                                        v1 = konsultasiCfUsers.get(b).getHasil_cf();
                                    }
                                }
                                v2 = hasilKonsultasiUsers.get(hasilKonsultasiUsers.size()-1).getNilaiCf();
                                hasil = v1+(v2*(1-v1));
                                hasilKonsultasiUsers.remove(hasilKonsultasiUsers.size()-1);
                                hasilKonsultasiUsers.add(new HasilKonsultasiUser(pengetahuanArrayList.get(a).getId_penyakit(), hasil));
                            } else {
                                for (int b = 0; b < konsultasiCfUsers.size(); b++) {
                                    if (pengetahuanArrayList.get(a).getId_gejala().trim().equals(konsultasiCfUsers.get(b).getId_gejala())) {
                                        hasilKonsultasiUsers.add(new HasilKonsultasiUser(pengetahuanArrayList.get(a).getId_penyakit(), konsultasiCfUsers.get(b).getHasil_cf()));
                                    }
                                }
                            }
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
                    bundle.putParcelableArrayList("KONSULTASI", konsultasis);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    onPause();
                    for (int a = 0; a < pengetahuanArrayList.size(); a++) {
                        Log.d(TAG, "onResponse: " + pengetahuanArrayList.get(a).getId_penyakit() + " " + pengetahuanArrayList.get(a).getId_gejala());
                    }
                }
            }

            @Override
            public void onFailure(Call<PengetahuanResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    private void initRecyclerView() {

        Call<GejalaResponse> call = apiInterface.getGejalas();
        call.enqueue(new Callback<GejalaResponse>() {
            @Override
            public void onResponse(Call<GejalaResponse> call, Response<GejalaResponse> response) {
                if (Boolean.valueOf(response.body().getError())) {
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                } else {
                    List<Gejala> gejalas = response.body().getGejalas();
                    Log.d(TAG, "onResponse: Gejala Size " + gejalas.get(0).getNama_gejala());
                    diagnosaAdapter = new DiagnosaAdapter(DiagnosaActivity.this, gejalas);
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
                        if (boo){
                            dialog.setMessage("Memuat Data..." + position+"/"+diagnosaAdapter.getItemCount());
                        } else {
                            dialogPetani();
                            dialog.dismiss();
                        }
                    }
                });
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

    private void dialogPetani(){
        alertDialog = new AlertDialog.Builder(DiagnosaActivity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_diagnosa, null);
        alertDialog.setView(dialogView);
        alertDialog.setCancelable(true);
        alertDialog.setTitle("Tulis Komentar...");
        edtNama = dialogView.findViewById(R.id.petani_nama);
        edtHp = dialogView.findViewById(R.id.petani_hp);
        edtAlamat = dialogView.findViewById(R.id.petani_alamat);

        alertDialog.setPositiveButton("BATAL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(DiagnosaActivity.this, MainActivity.class));
            }
        });

        alertDialog.setNegativeButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String tanggal = dateFormat.format(new Date());
                if (edtNama.getText().toString().trim().equals("")){
                    dialogPetani();
                    edtNama.setError("Harus Diisi");
                    edtNama.setFocusable(true);
                } else if (edtHp.getText().toString().trim().equals("")){
                    dialogPetani();
                    edtHp.setError("Harus Diisi");
                    edtHp.setFocusable(true);
                } else if (edtAlamat.getText().toString().trim().equals("")){
                    dialogPetani();
                    edtAlamat.setError("Harus Diisi");
                    edtAlamat.setFocusable(true);
                } else {
                    nama = edtNama.getText().toString();
                    nohp = edtHp.getText().toString();
                    alamat = edtAlamat.getText().toString();
                    konsultasis.add(new Konsultasi(
                            0,
                            nama,
                            nohp,
                            alamat,
                            "",
                            tanggal
                    ));
                }
            }
        });

        AlertDialog ad = alertDialog.create();
        ad.show();
    }
}
