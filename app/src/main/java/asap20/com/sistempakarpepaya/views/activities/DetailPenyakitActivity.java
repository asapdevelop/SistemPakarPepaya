package asap20.com.sistempakarpepaya.views.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.HasilKonsultasiUser;
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
    String idPenyakits;
    TextView namaPenyakit, detailPenyakit, btnBack, pengendalianya;
    ImageView gambarPenyakit;
    ApiInterface apiInterface;
    ProgressDialog dialog;
    List<Penyakit> penyakits;
    ArrayList<HasilKonsultasiUser> hasilKonsultasiUsers = new ArrayList<>();
//    ArrayList<Penyakit> penyakits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_penyakit);

        // idPenyakit = getIntent().getIntExtra("IDPENYAKIT", 99);
        idPenyakits = getIntent().getStringExtra("IDPENYAKIT");
        Log.d(TAG, "onCreate: " + idPenyakit);

        namaPenyakit = findViewById(R.id.nama_penyakit);
        detailPenyakit = findViewById(R.id.detail_penyakit);
        gambarPenyakit = findViewById(R.id.gambar_penyakit);
        pengendalianya = findViewById(R.id.pengendalian_penyakit);
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
                if (Boolean.valueOf(response.body().getError())) {
                    Log.d(TAG, "onResponse: " + response.body().getMessage());
                    dialog.dismiss();
                } else {
                    List<Penyakit> penyakits = response.body().getPenyakits();

                    for (int a = 0; a < penyakits.size(); a++) {
                        if (penyakits.get(a).getId_penyakit().trim().equals(idPenyakits)) {

                            Log.d(TAG, "onResponse: " + a + penyakits.get(a).getNama_penyakit());
                            namaPenyakit.setText(penyakits.get(a).getNama_penyakit());
                            detailPenyakit.setText(penyakits.get(a).getDeskripsi_penyakit());
                            pengendalianya.setText(penyakits.get(a).getPengendalianya());
                            Log.d(TAG, "onResponse: " + penyakits.get(a).getGambar_penyakit());
                            Picasso.with(DetailPenyakitActivity.this)
                                    .load(penyakits.get(a).getGambar_penyakit())
                                    .fit()
                                    .into(gambarPenyakit);

                            dialog.dismiss();
                        }
                    }
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
