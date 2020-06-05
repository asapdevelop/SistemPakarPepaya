package asap20.com.sistempakarpepaya.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Penyakit;
import asap20.com.sistempakarpepaya.views.activities.DetailPenyakitActivity;

public class PenyakitAdapter extends RecyclerView.Adapter<PenyakitAdapter.ViewHolder>{
    private static final String TAG = "PenyakitAdapter";
    View mView;

    Context context;

    List<Penyakit> listPenyakit;
    ArrayList<Penyakit> penyakits;
    String id;

    public PenyakitAdapter(Context context, List<Penyakit> listPenyakit) {
        this.context = context;
        this.listPenyakit = listPenyakit;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_penyakit, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.cv_penyakit.setText(listPenyakit.get(position).getNama_penyakit());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + position+ listPenyakit.get(position).getNama_penyakit());
                id=listPenyakit.get(position).getId_penyakit();
//                penyakits.add(listPenyakit.get(position));
//                Intent intent = new Intent(context, DetailPenyakitActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putParcelableArrayList("PENYAKIT", penyakits);
//                intent.putExtras(bundle);
//                context.startActivity(intent);

                Intent intent = new Intent(context, DetailPenyakitActivity.class);
                intent.putExtra("IDPENYAKIT", id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, "getItemCount: " + listPenyakit.size());
        return listPenyakit.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public ConstraintLayout root;
        public TextView cv_penyakit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.layout_card_view);

            cv_penyakit = itemView.findViewById(R.id.cv_penyakit);

            mView = itemView;

        }

    }
}
