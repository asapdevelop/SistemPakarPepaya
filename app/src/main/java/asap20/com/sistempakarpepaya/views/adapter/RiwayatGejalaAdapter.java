package asap20.com.sistempakarpepaya.views.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;

public class RiwayatGejalaAdapter extends RecyclerView.Adapter<RiwayatGejalaAdapter.ViewHolder>{
    View mView;
    Context context;
    List<Gejala> gejalas;

    public RiwayatGejalaAdapter(Context context, List<Gejala> gejalas) {
        this.context = context;
        this.gejalas = gejalas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int rowPos = holder.getAdapterPosition();
        if (rowPos==0){
            holder.cv_no.setBackgroundResource(R.color.colorAccent);
            holder.cv_id_gejala.setBackgroundResource(R.color.colorAccent);
            holder.cv_gejala.setBackgroundResource(R.color.colorAccent);
            holder.cv_cf_user.setBackgroundResource(R.color.colorAccent);

            holder.cv_no.setTypeface(Typeface.DEFAULT_BOLD);
            holder.cv_id_gejala.setTypeface(Typeface.DEFAULT_BOLD);
            holder.cv_gejala.setTypeface(Typeface.DEFAULT_BOLD);
            holder.cv_cf_user.setTypeface(Typeface.DEFAULT_BOLD);

            holder.cv_no.setText("No");
            holder.cv_id_gejala.setText("Kode");
            holder.cv_gejala.setText("Gejala");
            holder.cv_cf_user.setText("Nilai Cf");
        } else {
            if (rowPos%2 == 1){
                holder.cv_no.setBackgroundResource(R.drawable.bg_top);
                holder.cv_id_gejala.setBackgroundResource(R.drawable.bg_top);
                holder.cv_gejala.setBackgroundResource(R.drawable.bg_top);
                holder.cv_cf_user.setBackgroundResource(R.drawable.bg_top);
            } else {
                holder.cv_no.setBackgroundResource(R.drawable.bg_second);
                holder.cv_id_gejala.setBackgroundResource(R.drawable.bg_second);
                holder.cv_gejala.setBackgroundResource(R.drawable.bg_second);
                holder.cv_cf_user.setBackgroundResource(R.drawable.bg_second);
            }
            Gejala gejala = gejalas.get(rowPos-1);
            holder.cv_no.setText(String.valueOf(rowPos));
            holder.cv_id_gejala.setText(gejala.getId_gejala());
            holder.cv_gejala.setText(gejala.getNama_gejala());
            holder.cv_cf_user.setText(String.valueOf(gejala.getBobot_pakar()));
        }
    }

    @Override
    public int getItemCount() {
        return gejalas.size()+1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public LinearLayout root;
        public TextView cv_no;
        public TextView cv_id_gejala;
        public TextView cv_gejala;
        public TextView cv_cf_user;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.layout_card_view);

            cv_no = itemView.findViewById(R.id.txt_no);
            cv_id_gejala = itemView.findViewById(R.id.txt_id_gejala);
            cv_gejala = itemView.findViewById(R.id.txt_nama);
            cv_cf_user = itemView.findViewById(R.id.txt_cf_user);

            mView = itemView;

        }

    }
}
