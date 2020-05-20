package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Penyakit;
import asap20.com.sistempakarpepaya.models.response.PenyakitResponse;
import asap20.com.sistempakarpepaya.rest.ApiClient;
import asap20.com.sistempakarpepaya.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPenyakitActivity extends AppCompatActivity {
    private static final String TAG = "DetailPenyakitActivity";

    int idPenyakit;
    TextView namaPenyakit, detailPenyakit, btnBack;
    ImageView gambarPenyakit;
    ApiInterface apiInterface;
    ProgressDialog dialog;
//    ArrayList<Penyakit> penyakits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penyakit);

        idPenyakit = getIntent().getIntExtra("IDPENYAKIT", 99);
        Log.d(TAG, "onCreate: " + idPenyakit);

        namaPenyakit = findViewById(R.id.nama_penyakit);
        detailPenyakit = findViewById(R.id.detail_penyakit);
        gambarPenyakit = findViewById(R.id.gambar_penyakit);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        penyakits = new ArrayList<>();
//        Bundle bundle = getIntent().getExtras();
//        penyakits = bundle.getParcelableArrayList("PENYAKIT");

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        dialog = new ProgressDialog(DetailPenyakitActivity.this);
        dialog.setMessage("Loading...");
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
                    Log.d(TAG, "onResponse: " + idPenyakit + penyakits.get(idPenyakit).getNama_penyakit());
                    namaPenyakit.setText(penyakits.get(idPenyakit).getNama_penyakit());
                    detailPenyakit.setText(penyakits.get(idPenyakit).getDeskripsi_penyakit());
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<PenyakitResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t);
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
