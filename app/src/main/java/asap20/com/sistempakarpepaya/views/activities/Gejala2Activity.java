package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.Penyakit;
import asap20.com.sistempakarpepaya.models.response.GejalaResponse;
import asap20.com.sistempakarpepaya.models.response.PenyakitResponse;
import asap20.com.sistempakarpepaya.rest.ApiClient;
import asap20.com.sistempakarpepaya.rest.ApiInterface;
import asap20.com.sistempakarpepaya.views.adapter.DiagnosaAdapter;
import asap20.com.sistempakarpepaya.views.adapter.GejalaAdapter;
import asap20.com.sistempakarpepaya.views.adapter.PenyakitAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Gejala2Activity extends AppCompatActivity {
    private static final String TAG = "Gejala2Activity";
    TextView btnBack;
    RecyclerView rvgejala;
    ApiInterface apiInterface;
    LinearLayoutManager linearLayoutManager;
   GejalaAdapter diagnosaAdapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gejala2);
            apiInterface = ApiClient.getClient().create(ApiInterface.class);
            btnBack = findViewById(R.id.btn_back);
            rvgejala = findViewById(R.id.rv_gejala);
            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });

            dialog = new ProgressDialog(Gejala2Activity.this);

            rvgejala.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
            rvgejala.addItemDecoration(new DividerItemDecoration(this.getApplicationContext(), DividerItemDecoration.VERTICAL));
            linearLayoutManager = new LinearLayoutManager(Gejala2Activity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvgejala.setLayoutManager(linearLayoutManager);
            rvgejala.setHasFixedSize(true);
            tampilkanGejala();
        }

        private void tampilkanGejala() {
            dialog.setMessage("Memuat Data...");
            dialog.show();
            Call<GejalaResponse> call = apiInterface.getGejalas();
            call.enqueue(new Callback<GejalaResponse>() {
                @Override
                public void onResponse(Call<GejalaResponse> call, Response<GejalaResponse> response) {
                    if (Boolean.valueOf(response.body().getError())){
                        Log.d(TAG, "onResponse: " + response.body().getMessage());
                        dialog.dismiss();
                    } else {
                        List<Gejala> Gejala = response.body().getGejalas();

                        diagnosaAdapter = new GejalaAdapter(Gejala2Activity.this, Gejala);
                        rvgejala.setAdapter(diagnosaAdapter);
                        diagnosaAdapter.notifyDataSetChanged();
                        dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<GejalaResponse> call, Throwable t) {
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