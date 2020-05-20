package asap20.com.sistempakarpepaya.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Penyakit implements Parcelable {
    @SerializedName("id_penyakit")
    private String id_penyakit;
    @SerializedName("nama_penyakit")
    private String nama_penyakit;
    @SerializedName("deskripsi_penyakit")
    private String deskripsi_penyakit;
    @SerializedName("gambar_penyakit")
    private String gambar_penyakit;

    public String getId_penyakit() {
        return id_penyakit;
    }

    public void setId_penyakit(String id_penyakit) {
        this.id_penyakit = id_penyakit;
    }

    public String getNama_penyakit() {
        return nama_penyakit;
    }

    public void setNama_penyakit(String nama_penyakit) {
        this.nama_penyakit = nama_penyakit;
    }

    public String getDeskripsi_penyakit() {
        return deskripsi_penyakit;
    }

    public void setDeskripsi_penyakit(String deskripsi_penyakit) {
        this.deskripsi_penyakit = deskripsi_penyakit;
    }

    public String getGambar_penyakit() {
        return gambar_penyakit;
    }

    public void setGambar_penyakit(String gambar_penyakit) {
        this.gambar_penyakit = gambar_penyakit;
    }

    public Penyakit(Parcel parcel) {
        id_penyakit = parcel.readString();
        nama_penyakit = parcel.readString();
        deskripsi_penyakit = parcel.readString();
        gambar_penyakit = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_penyakit);
        parcel.writeString(nama_penyakit);
        parcel.writeString(deskripsi_penyakit);
        parcel.writeString(gambar_penyakit);
    }

    public static final Parcelable.Creator<Penyakit> CREATOR = new Parcelable.Creator<Penyakit>(){

        @Override
        public Penyakit createFromParcel(Parcel parcel) {
            return new Penyakit(parcel);
        }

        @Override
        public Penyakit[] newArray(int i) {
            return new Penyakit[i];
        }
    };
}
