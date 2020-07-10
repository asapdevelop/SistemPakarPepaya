package asap20.com.sistempakarpepaya.views.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import asap20.com.sistempakarpepaya.R;
import asap20.com.sistempakarpepaya.models.Gejala;
import asap20.com.sistempakarpepaya.models.KonsultasiCfUser;
import asap20.com.sistempakarpepaya.models.Pengetahuan;

public class DiagnosaAdapter extends RecyclerView.Adapter<DiagnosaAdapter.ViewHolder>{
    private static final String TAG = "DiagnosaAdapter";
    View mView;

    Context context;
    List<Gejala> gejalas;
    List<Pengetahuan> pengetahuans;
    int jumlahKonsul=0;

    public interface OnProgress{
        void onProgress(Boolean boo, int position);
    }

    private OnProgress mListener;

    public void setOnProgressListener(OnProgress listener){
        mListener = listener;
    }

    public int getJumlahKonsul(){
        return jumlahKonsul;
    }

    public DiagnosaAdapter(Context context, List<Gejala> gejalas, List<Pengetahuan> pengetahuans) {
        this.context = context;
        this.gejalas = gejalas;
        this.pengetahuans = pengetahuans;
    }

    public ArrayList<KonsultasiCfUser> getKonsultasiCfUser(){
        return konsultasiCfUsers;
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
                    jumlahKonsul = jumlahKonsul-1;
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
                    case R.id.radio02:
                        inputKonsultasi(position, (double) 0.1);
                        break;
                    case R.id.radio03:
                        inputKonsultasi(position, (double) 0.2);
                        break;
                    case R.id.radio031:
                        inputKonsultasi(position, (double) 0.3);
                        break;
                    case R.id.radio04:
                        inputKonsultasi(position, (double) 0.4);
                        break;
                    case R.id.radio041:
                        inputKonsultasi(position, (double) 0.5);
                        break;
                    case R.id.radio06:
                        inputKonsultasi(position, (double) 0.6);
                        break;
                    case R.id.radio061:
                        inputKonsultasi(position, (double) 0.7);
                        break;
                    case R.id.radio08:
                        inputKonsultasi(position, (double) 0.8);
                        break;
                    case R.id.radio1:
                        inputKonsultasi(position, (double) 1);
                        break;
                }
            }
        });

        if (position==gejalas.size()-1){
            Log.d(TAG, "onBindViewHolder: false" + position + " " + gejalas.size());
            mListener.onProgress(false, position);
        } else {
            Log.d(TAG, "onBindViewHolder: true" + position + " " + gejalas.size());
            mListener.onProgress(true, position);
        }
    }

    private void inputKonsultasi(int position, double cf){
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>Iki lho Mbang<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        jumlahKonsul = jumlahKonsul+1;
        for (int a=0; a<pengetahuans.size(); a++){
            if (gejalas.get(position).getId_gejala().equals(pengetahuans.get(a).getId_gejala())){
                Log.d(TAG, "inputKonsultasi: " + pengetahuans.get(a).getId_gejala());
                konsultasiCfUsers.add(
                        new KonsultasiCfUser(
                                pengetahuans.get(a).getId_penyakit(),
                                gejalas.get(position).getId_gejala(),
                                gejalas.get(position).getNama_gejala(),
                                cf,
                                (pengetahuans.get(a).getBobot_pakar()*cf))
                );
            }
        }
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
