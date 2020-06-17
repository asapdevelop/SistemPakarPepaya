package asap20.com.sistempakarpepaya.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Konsultasi;
import asap20.com.sistempakarpepaya.views.activities.DetailRiwayatActivity;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder>{
    private static final String TAG = "RiwayatAdapter";
    View mView;

    Context context;
    List<Konsultasi> konsultasis;
    ArrayList<Konsultasi> konsultasiArrayList = new ArrayList<>();

    public RiwayatAdapter(Context context, List<Konsultasi> konsultasis) {
        this.context = context;
        this.konsultasis = konsultasis;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_riwayat, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.cv_penyakit.setText(konsultasis.get(position).getNama_petani());
        holder.cv_tanggal.setText(konsultasis.get(position).getTanggal());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + konsultasis.get(position).getId_konsultasi());
                konsultasiArrayList.clear();
                konsultasiArrayList.add(konsultasis.get(position));
                Intent intent = new Intent(context, DetailRiwayatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("KONSULTASI", konsultasiArrayList);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return konsultasis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout root;
        public TextView cv_penyakit;
        public TextView cv_tanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.layout_card_view);

            cv_penyakit = itemView.findViewById(R.id.cv_penyakit);
            cv_tanggal = itemView.findViewById(R.id.cv_tanggal);


            mView = itemView;

        }

    }
}
