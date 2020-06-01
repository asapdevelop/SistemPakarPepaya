package asap20.com.sistempakarpepaya.models;

import com.google.gson.annotations.SerializedName;

public class Konsultasi {
    @SerializedName("id_konsultasi")
    private int id_konsultasi;
    @SerializedName("nama_petani")
    private String nama_petani;
    @SerializedName("no_telpon")
    private String no_telpon;
    @SerializedName("hasil_konsultasi")
    private String hasil_konsultasi;
    @SerializedName("tanggal")
    private String tanggal;

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
}
