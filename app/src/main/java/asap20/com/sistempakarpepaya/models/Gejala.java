package asap20.com.sistempakarpepaya.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Gejala implements Parcelable {
    @SerializedName("id_gejala")
    private String id_gejala;
    @SerializedName("nama_gejala")
    private String nama_gejala;
    @SerializedName("bobot_pakar")
    private Double bobot_pakar;

    public Gejala(Parcel parcel){
        id_gejala = parcel.readString();
        nama_gejala = parcel.readString();
        bobot_pakar = parcel.readDouble();
    }

    public static Creator<Gejala> CREATOR = new Creator<Gejala>() {
        @Override
        public Gejala createFromParcel(Parcel parcel) {
            return new Gejala(parcel);
        }

        @Override
        public Gejala[] newArray(int i) {
            return new Gejala[i];
        }
    };

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

    public Double getBobot_pakar() {
        return bobot_pakar;
    }

    public void setBobot_pakar(Double bobot_pakar) {
        this.bobot_pakar = bobot_pakar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id_gejala);
        parcel.writeString(nama_gejala);
        parcel.writeDouble(bobot_pakar);
    }
}
