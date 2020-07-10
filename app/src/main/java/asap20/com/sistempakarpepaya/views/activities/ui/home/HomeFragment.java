package asap20.com.sistempakarpepaya.views.activities.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import asap20.com.sistempakarpepaya.R;


public class HomeFragment extends Fragment {
    Button btnKonsultasi, btnLihatData;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btnKonsultasi = root.findViewById(R.id.btnKonsultasi);
        btnLihatData = root.findViewById(R.id.btnLihatData);

        btnKonsultasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_diagnosaActivity);
            }
        });

        btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_lihatdata);
            }
        });

        return root;
    }
}