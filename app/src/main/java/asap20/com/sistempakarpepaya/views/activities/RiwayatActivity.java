package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Konsultasi;
import asap20.com.sistempakarpepaya.models.response.KonsultasiResponse;
import asap20.com.sistempakarpepaya.rest.ApiClient;
import asap20.com.sistempakarpepaya.rest.ApiInterface;
import asap20.com.sistempakarpepaya.views.adapter.RiwayatAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatActivity extends AppCompatActivity {
    private static final String TAG = "RiwayatActivity";
    private RecyclerView rvRiwayat;
    private RiwayatAdapter riwayatAdapter;
    ApiInterface apiInterface;
    ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        rvRiwayat = findViewById(R.id.rv_riwayat);

        initRecyclerview();
    }

    private void initRecyclerview() {
        Call<KonsultasiResponse> call = apiInterface.getKonsultasis();
        call.enqueue(new Callback<KonsultasiResponse>() {
            @Override
            public void onResponse(Call<KonsultasiResponse> call, Response<KonsultasiResponse> response) {
                if (Boolean.valueOf(response.body().getError())) {
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                } else {
                    List<Konsultasi> konsultasiList = response.body().getKonsultasis();
                    riwayatAdapter = new RiwayatAdapter(RiwayatActivity.this, konsultasiList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RiwayatActivity.this, LinearLayoutManager.VERTICAL, false);
                    rvRiwayat.addItemDecoration(new DividerItemDecoration(RiwayatActivity.this, DividerItemDecoration.VERTICAL));
                    rvRiwayat.setLayoutManager(linearLayoutManager);
                    rvRiwayat.setHasFixedSize(true);
                }
                rvRiwayat.setAdapter(riwayatAdapter);
                riwayatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<KonsultasiResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(RiwayatActivity.this, MainActivity.class));
    }
}
