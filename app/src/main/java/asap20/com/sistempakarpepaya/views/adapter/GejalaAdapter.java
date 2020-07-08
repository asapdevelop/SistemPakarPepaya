package asap20.com.sistempakarpepaya.views.adapter;

import android.content.Context;
import android.content.Intent;
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
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.Penyakit;
import asap20.com.sistempakarpepaya.views.activities.DetailPenyakitActivity;

public class GejalaAdapter extends RecyclerView.Adapter<GejalaAdapter.ViewHolder> {
    private static final String TAG = "GejalaAdapter";
    View mView;

    Context context;

    List<Gejala> listGejala;
    ArrayList<Gejala> Gejala;
    String id;
    public GejalaAdapter(Context context, List<Gejala> listGejala) {
        this.context = context;
        this.listGejala = listGejala;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemgejala, parent, false);
        return new GejalaAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.cv_gejala.setText(listGejala.get(position).getId_gejala());
        holder.cv_gejala.setText(listGejala.get(position).getNama_gejala());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + position+ listGejala.get(position).getNama_gejala());
                id=listGejala.get(position).getId_gejala();
//                penyakits.add(listPenyakit.get(position));
//                Intent intent = new Intent(context, DetailPenyakitActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("PENYAKIT", penyakits);
//                intent.putExtras(bundle);
//                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return listGejala.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout root;
        public TextView cv_gejala;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.layout_card_view2);

            cv_gejala = itemView.findViewById(R.id.cv_gejala);

            mView = itemView;

        }

    }
}

