package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Penyakit;
import asap20.com.sistempakarpepaya.models.response.PenyakitResponse;
import asap20.com.sistempakarpepaya.rest.ApiClient;
import asap20.com.sistempakarpepaya.rest.ApiInterface;
import asap20.com.sistempakarpepaya.views.adapter.PenyakitAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenyakitActivity extends AppCompatActivity {
    private static final String TAG = "PenyakitActivity";

    TextView btnBack;
    RecyclerView rvPenyakit;
    ApiInterface apiInterface;
    LinearLayoutManager linearLayoutManager;
    PenyakitAdapter penyakitAdapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyakit);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        btnBack = findViewById(R.id.btn_back);
        rvPenyakit = findViewById(R.id.rv_penyakit);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        dialog = new ProgressDialog(PenyakitActivity.this);

        rvPenyakit.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        rvPenyakit.addItemDecoration(new DividerItemDecoration(this.getApplicationContext(), DividerItemDecoration.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(PenyakitActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPenyakit.setLayoutManager(linearLayoutManager);
        rvPenyakit.setHasFixedSize(true);
        tampilkanPenyakit();
    }

    private void tampilkanPenyakit() {
        dialog.setMessage("Memuat Data...");
        dialog.show();
        Call<PenyakitResponse> call = apiInterface.getPenyakits();
        call.enqueue(new Callback<PenyakitResponse>() {
            @Override
            public void onResponse(Call<PenyakitResponse> call, Response<PenyakitResponse> response) {
                if (Boolean.valueOf(response.body().getError())){
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                    dialog.dismiss();
                } else {
                    List<Penyakit> penyakits = response.body().getPenyakits();

                    penyakitAdapter = new PenyakitAdapter(PenyakitActivity.this, penyakits);
                    rvPenyakit.setAdapter(penyakitAdapter);
                    penyakitAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<PenyakitResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
