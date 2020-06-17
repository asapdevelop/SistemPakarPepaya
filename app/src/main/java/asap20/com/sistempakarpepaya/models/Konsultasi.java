package asap20.com.sistempakarpepaya.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Konsultasi implements Parcelable {
    @SerializedName("id_konsultasi")
    private int id_konsultasi;
    @SerializedName("nama_petani")
    private String nama_petani;
    @SerializedName("no_telpon")
    private String no_telpon;
    @SerializedName("alamat_petani")
    private String alamat_petani;
    @SerializedName("hasil_konsultasi")
    private String hasil_konsultasi;
    @SerializedName("tanggal")
    private String tanggal;

    public Konsultasi(Parcel parcel){
        id_konsultasi = parcel.readInt();
        nama_petani = parcel.readString();
        no_telpon = parcel.readString();
        alamat_petani = parcel.readString();
        hasil_konsultasi = parcel.readString();
        tanggal = parcel.readString();
    }

    public static Creator<Konsultasi> CREATOR = new Creator<Konsultasi>() {
        @Override
        public Konsultasi createFromParcel(Parcel parcel) {
            return new Konsultasi(parcel);
        }

        @Override
        public Konsultasi[] newArray(int i) {
            return new Konsultasi[i];
        }
    };

    public Konsultasi(int id_konsultasi, String nama_petani, String no_telpon, String alamat_petani, String hasil_konsultasi, String tanggal) {
        this.id_konsultasi = id_konsultasi;
        this.nama_petani = nama_petani;
        this.no_telpon = no_telpon;
        this.alamat_petani = alamat_petani;
        this.hasil_konsultasi = hasil_konsultasi;
        this.tanggal = tanggal;
    }

    public int getId_konsultasi() {
        return id_konsultasi;
    }

    public void setId_konsultasi(int id_konsultasi) {
        this.id_konsultasi = id_konsultasi;
    }

    public String getNama_petani() {
        return nama_petani;
    }

    public void setNama_petani(String nama_petani) {
        this.nama_petani = nama_petani;
    }

    public String getNo_telpon() {
        return no_telpon;
    }

    public void setNo_telpon(String no_telpon) {
        this.no_telpon = no_telpon;
    }

    public String getAlamat_petani() {
        return alamat_petani;
    }

    public void setAlamat_petani(String alamat_petani) {
        this.alamat_petani = alamat_petani;
    }

    public String getHasil_konsultasi() {
        return hasil_konsultasi;
    }

    public void setHasil_konsultasi(String hasil_konsultasi) {
        this.hasil_konsultasi = hasil_konsultasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id_konsultasi);
        parcel.writeString(nama_petani);
        parcel.writeString(no_telpon);
        parcel.writeString(alamat_petani);
        parcel.writeString(hasil_konsultasi);
        parcel.writeString(tanggal);

    }
}
