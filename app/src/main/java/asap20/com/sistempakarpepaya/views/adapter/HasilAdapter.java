package asap20.com.sistempakarpepaya.views.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.HasilKonsultasiUser;
import asap20.com.sistempakarpepaya.models.Penyakit;

public class HasilAdapter extends RecyclerView.Adapter<HasilAdapter.ViewHolder> {
    private static final String TAG = "HasilAdapter";
    View mView;
    Context context;
    List<Penyakit> penyakits;
    List<HasilKonsultasiUser> hasilKonsultasiUsers;

    public HasilAdapter(Context context, List<Penyakit> penyakits, List<HasilKonsultasiUser> hasilKonsultasiUsers) {
        this.context = context;
        this.penyakits = penyakits;
        this.hasilKonsultasiUsers = hasilKonsultasiUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hasil, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: hasilkonsultasi " + hasilKonsultasiUsers.get(position).getIdPenyakit());
        Log.d(TAG, "onBindViewHolder: position " + position);
        for (int a = 0; a < penyakits.size(); a++) {
            if (penyakits.get(a).getId_penyakit().trim().equals(hasilKonsultasiUsers.get(position).getIdPenyakit())) {
                holder.cv_penyakit.setText(penyakits.get(a).getNama_penyakit());
            }
        }
        holder.cv_persen.setText((hasilKonsultasiUsers.get(position).getNilaiCf() * 100) + context.getString(R.string.percent));
    }

    @Override
    public int getItemCount() {
        return hasilKonsultasiUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout root;
        public TextView cv_penyakit;
        public TextView cv_persen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.layout_card_view);

            cv_penyakit = itemView.findViewById(R.id.cv_penyakit);
            cv_persen = itemView.findViewById(R.id.cv_percent);

            mView = itemView;

        }

    }
}
