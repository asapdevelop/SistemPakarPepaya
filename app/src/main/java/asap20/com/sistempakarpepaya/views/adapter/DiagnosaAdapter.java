package asap20.com.sistempakarpepaya.views.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.KonsultasiCfUser;

public class DiagnosaAdapter extends RecyclerView.Adapter<DiagnosaAdapter.ViewHolder>{
    private static final String TAG = "DiagnosaAdapter";
    View mView;

    Context context;
    List<Gejala> gejalas;

    public DiagnosaAdapter(Context context, List<Gejala> gejalas) {
        this.context = context;
        this.gejalas = gejalas;
    }

    public ArrayList<Gejala> gejalaArrayList = new ArrayList<>();
    public ArrayList<KonsultasiCfUser> konsultasiCfUsers = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_diagnosa, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.cv_gejala.setText("Apakah " + gejalas.get(position).getNama_gejala() + "?");
        holder.cv_gejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.cv_gejala.isChecked()){
                    gejalaArrayList.add(gejalas.get(position));
                    holder.radioGroup.setVisibility(View.VISIBLE);
                } else {
                    gejalaArrayList.remove(gejalas.get(position));
                    for (int a=0; a<konsultasiCfUsers.size();a++){
                        if (konsultasiCfUsers.get(a).getId_gejala().trim().equals(gejalas.get(position).getId_gejala())){
                            konsultasiCfUsers.remove(a);
                        }
                    }
                    holder.radioGroup.setVisibility(View.GONE);
                }
            }
        });
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                for (int a=0; a<konsultasiCfUsers.size();a++){
                    if (konsultasiCfUsers.get(a).getId_gejala().trim().equals(gejalas.get(position).getId_gejala())){
                        konsultasiCfUsers.remove(a);
                    }
                }
                switch (i){
                    case 2131230879:
                        konsultasiCfUsers.add(
                                new KonsultasiCfUser(gejalas.get(position).getId_gejala(),
                                        Double.valueOf(0))
                        );
                        break;
                    case 2131230880:
                        konsultasiCfUsers.add(
                                new KonsultasiCfUser(gejalas.get(position).getId_gejala(),
                                        Double.valueOf(0.4))
                        );
                        break;
                    case 2131230881:
                        konsultasiCfUsers.add(
                                new KonsultasiCfUser(gejalas.get(position).getId_gejala(),
                                        Double.valueOf(0.6))
                        );
                        break;
                    case 2131230882:
                        konsultasiCfUsers.add(
                                new KonsultasiCfUser(gejalas.get(position).getId_gejala(),
                                        Double.valueOf(0.8))
                        );
                        break;
                    case 2131230883:
                        konsultasiCfUsers.add(
                                new KonsultasiCfUser(gejalas.get(position).getId_gejala(),
                                        Double.valueOf(1))
                        );
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gejalas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout root;
        public CheckBox cv_gejala;
        public RadioGroup radioGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.layout_card_view);

            cv_gejala = itemView.findViewById(R.id.cv_gejala);
            radioGroup = itemView.findViewById(R.id.rb_group);

            mView = itemView;

        }

    }
}
