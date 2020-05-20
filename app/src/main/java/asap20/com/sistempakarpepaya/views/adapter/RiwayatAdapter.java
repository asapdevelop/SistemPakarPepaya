package asap20.com.sistempakarpepaya.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import asap20.com.sistempakarpepaya.R;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder>{
    View mView;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_riwayat, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
