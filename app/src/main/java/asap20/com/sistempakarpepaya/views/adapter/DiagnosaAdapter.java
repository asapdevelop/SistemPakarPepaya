package asap20.com.sistempakarpepaya.views.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import asap20.com.sistempakarpepaya.R;

public class DiagnosaAdapter {

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;


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
