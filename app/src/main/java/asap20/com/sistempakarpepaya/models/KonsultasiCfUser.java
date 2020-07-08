package asap20.com.sistempakarpepaya.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class KonsultasiCfUser implements Parcelable {
    @SerializedName("id_penyakit")
    private String id_penyakit;
    @SerializedName("id_gejala")
    private String id_gejala;
    @SerializedName("nama_gejala")
    private String nama_gejala;
    @SerializedName("cf_user")
    private Double cf_user;
    @SerializedName("hasil_cf")
    private Double hasil_cf;

    public KonsultasiCfUser(String id_penyakit, String id_gejala, String nama_gejala, Double cf_user, Double hasil_cf) {
        this.id_penyakit = id_penyakit;
        this.id_gejala = id_gejala;
        this.nama_gejala = nama_gejala;
        this.cf_user = cf_user;
        this.hasil_cf = hasil_cf;
    }

    public KonsultasiCfUser(Parcel parcel){
        id_penyakit = parcel.readString();
        id_gejala = parcel.readString();
        nama_gejala = parcel.readString();
        cf_user = parcel.readDouble();
        hasil_cf = parcel.readDouble();
    }

    public static Creator<KonsultasiCfUser> CREATOR = new Creator<KonsultasiCfUser>() {
        @Override
        public KonsultasiCfUser createFromParcel(Parcel parcel) {
            return new KonsultasiCfUser(parcel);
        }

        @Override
        public KonsultasiCfUser[] newArray(int i) {
            return new KonsultasiCfUser[i];
        }
    };

    public String getId_penyakit() {
        return id_penyakit;
    }

    public void setId_penyakit(String id_penyakit) {
        this.id_penyakit = id_penyakit;
    }

    public String getId_gejala() {
        return id_gejala;
    }

    public void setId_gejala(String id_gejala) {
        this.id_gejala = id_gejala;
    }

    public String getNama_gejala() {
        return nama_gejala;
    }

    public void setNama_gejala(String nama_gejala) {
        this.nama_gejala = nama_gejala;
    }

    public Double getCf_user() {
        return cf_user;
    }

    public void setCf_user(Double cf_user) {
        this.cf_user = cf_user;
    }

    public Double getHasil_cf() {
        return hasil_cf;
    }

    public void setHasil_cf(Double hasil_cf) {
        this.hasil_cf = hasil_cf;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_penyakit);
        parcel.writeString(id_gejala);
        parcel.writeString(nama_gejala);
        parcel.writeDouble(cf_user);
        parcel.writeDouble(hasil_cf);
    }
}
