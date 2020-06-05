package asap20.com.sistempakarpepaya.views.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.HasilKonsultasiUser;
import asap20.com.sistempakarpepaya.models.Penyakit;

public class HasilKonsultasiAdapter extends RecyclerView.Adapter<HasilKonsultasiAdapter.ViewHolder>{
    private static final String TAG = "HasilKonsultasiAdapter";
    View mView;


    Context context;
    List<Penyakit> penyakits;
    List<HasilKonsultasiUser> hasilKonsultasiUsers;


    public HasilKonsultasiAdapter(Context context, List<Penyakit> penyakits, List<HasilKonsultasiUser> hasilKonsultasiUsers) {
        this.context = context;
        this.penyakits = penyakits;
        this.hasilKonsultasiUsers = hasilKonsultasiUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_hasil_penyakit, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int rowPos = holder.getAdapterPosition();
        if (rowPos == 0) {
            holder.cv_rank.setBackgroundResource(R.color.colorAccent);
            holder.cv_id_penyakit.setBackgroundResource(R.color.colorAccent);
            holder.cv_penyakit.setBackgroundResource(R.color.colorAccent);
            holder.cv_hasil_cf.setBackgroundResource(R.color.colorAccent);
            holder.cv_persen.setBackgroundResource(R.color.colorAccent);

            holder.cv_rank.setTypeface(Typeface.DEFAULT_BOLD);
            holder.cv_id_penyakit.setTypeface(Typeface.DEFAULT_BOLD);
            holder.cv_penyakit.setTypeface(Typeface.DEFAULT_BOLD);
            holder.cv_hasil_cf.setTypeface(Typeface.DEFAULT_BOLD);
            holder.cv_persen.setTypeface(Typeface.DEFAULT_BOLD);

            holder.cv_rank.setText("No");
            holder.cv_id_penyakit.setText("Kode");
            holder.cv_penyakit.setText("Penyakit");
            holder.cv_hasil_cf.setText("CF");
            holder.cv_persen.setText("%");
        } else {
            if (rowPos % 2 == 1) {
                holder.cv_rank.setBackgroundResource(R.drawable.bg_top);
                holder.cv_id_penyakit.setBackgroundResource(R.drawable.bg_top);
                holder.cv_penyakit.setBackgroundResource(R.drawable.bg_top);
                holder.cv_hasil_cf.setBackgroundResource(R.drawable.bg_top);
                holder.cv_persen.setBackgroundResource(R.drawable.bg_top);
            } else {
                holder.cv_rank.setBackgroundResource(R.drawable.bg_second);
                holder.cv_id_penyakit.setBackgroundResource(R.drawable.bg_second);
                holder.cv_penyakit.setBackgroundResource(R.drawable.bg_second);
                holder.cv_hasil_cf.setBackgroundResource(R.drawable.bg_second);
                holder.cv_persen.setBackgroundResource(R.drawable.bg_second);
            }
            HasilKonsultasiUser konsultasiCfUser = hasilKonsultasiUsers.get(rowPos - 1);
//            Double hasil = Double.valueOf(new DecimalFormat("#.##").format(konsultasiCfUser.getNilaiCf()));
//            Double persen = Double.valueOf(new DecimalFormat("##.##").format(konsultasiCfUser.getNilaiCf()*100));
            holder.cv_rank.setText(String.valueOf(rowPos));
            holder.cv_id_penyakit.setText(konsultasiCfUser.getIdPenyakit());
            Log.d(TAG, "onBindViewHolder: " + konsultasiCfUser.getNilaiCf() + " " + (konsultasiCfUser.getNilaiCf() * 100) + "%");
            holder.cv_hasil_cf.setText(String.valueOf(konsultasiCfUser.getNilaiCf()));
            holder.cv_persen.setText((konsultasiCfUser.getNilaiCf() * 100) + "%");
            for (int a = 0; a < hasilKonsultasiUsers.size(); a++) {
                if (penyakits.get(a).getId_penyakit().trim().equals(konsultasiCfUser.getIdPenyakit())) {
                    holder.cv_penyakit.setText(penyakits.get(a).getNama_penyakit());

                }


            }
        }

    }
    @Override
    public int getItemCount() {
        return hasilKonsultasiUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout root;
        public TextView cv_rank;
        public TextView cv_id_penyakit;
        public TextView cv_penyakit;
        public TextView cv_hasil_cf;
        public TextView cv_persen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.layout_card_view_hasil);

            cv_rank = itemView.findViewById(R.id.txt_rank);
            cv_id_penyakit = itemView.findViewById(R.id.txt_id_penyakit);
            cv_penyakit = itemView.findViewById(R.id.txt_penyakit);
            cv_hasil_cf = itemView.findViewById(R.id.txt_hasil_cf);
            cv_persen = itemView.findViewById(R.id.txt_persen);

            mView = itemView;

        }

    }
}
