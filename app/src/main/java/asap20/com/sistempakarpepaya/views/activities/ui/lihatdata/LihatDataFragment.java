package asap20.com.sistempakarpepaya.views.activities.ui.lihatdata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import asap20.com.sistempakarpepaya.R;


public class LihatDataFragment extends Fragment {
    LinearLayout btnGejala, btnPenyakit;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lihatdata, container, false);
        btnGejala = root.findViewById(R.id.btngejala);
        btnPenyakit = root.findViewById(R.id.btnPenyakit);
        btnGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_lihatdata_to_gejala2Activity);
            }
        });
        btnPenyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_lihatdata_to_penyakitActivity);
            }
        });
        return root;
    }
}