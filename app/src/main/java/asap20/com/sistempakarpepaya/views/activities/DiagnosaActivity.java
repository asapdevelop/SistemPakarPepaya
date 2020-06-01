package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.KonsultasiCfUser;
import asap20.com.sistempakarpepaya.models.response.GejalaResponse;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosa);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        dialog = new ProgressDialog(DiagnosaActivity.this);

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
                if (diagnosaAdapter.gejalaArrayList.size() != diagnosaAdapter.konsultasiCfUsers.size()){
                    Toast.makeText(DiagnosaActivity.this, "Lengkapi Diagnosa Anda..", Toast.LENGTH_SHORT).show();
                } else {
                    for (int a=0; a<diagnosaAdapter.gejalaArrayList.size();a++){
                        gejalaArrayList.add(diagnosaAdapter.gejalaArrayList.get(a));
                        konsultasiCfUsers.add(diagnosaAdapter.konsultasiCfUsers.get(a));
                        if (a==diagnosaAdapter.gejalaArrayList.size()-1){
                            Intent intent = new Intent(DiagnosaActivity.this, HasilDiagnosaActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList("GEJALA", gejalaArrayList);
                            bundle.putParcelableArrayList("CFUSER", konsultasiCfUsers);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    }
                }
                Log.d(TAG, "onClick: " + diagnosaAdapter.gejalaArrayList.size());
                for (int a = 0; a<diagnosaAdapter.gejalaArrayList.size(); a++){
                    Log.d(TAG, "onClick: " + diagnosaAdapter.gejalaArrayList.get(a).getId_gejala() + " " +diagnosaAdapter.gejalaArrayList.get(a).getNama_gejala());
                }
                for (int b=0; b<diagnosaAdapter.konsultasiCfUsers.size(); b++){
                    Log.d(TAG, "onClick: " + diagnosaAdapter.konsultasiCfUsers.get(b).getId_gejala() + " " +diagnosaAdapter.konsultasiCfUsers.get(b).getCf_user());
                }
            }
        });

        initRecyclerView();

    }

    private void initRecyclerView() {
        dialog.setMessage("Memuat Data..");
        dialog.show();
        Call<GejalaResponse> call = apiInterface.getGejalas();
        call.enqueue(new Callback<GejalaResponse>() {
            @Override
            public void onResponse(Call<GejalaResponse> call, Response<GejalaResponse> response) {
                if (Boolean.valueOf(response.body().getError())){
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
                dialog.dismiss();
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
}
