package asap20.com.sistempakarpepaya.models;

import com.google.gson.annotations.SerializedName;

public class Konsultasi {
    @SerializedName("id_konsultas")
    private String id_konsultas;
    @SerializedName("id_gejala")
    private String id_gejala;
    @SerializedName("id_petani")
    private String id_petani;
    @SerializedName("bobot_petani")
    private Double bobot_petani;
    @SerializedName("tgl_konsultasi")
    private String tgl_konsultasi;
    @SerializedName("hasil_konsultasi")
    private String hasil_konsultasi;

    public String getId_konsultas() {
        return id_konsultas;
    }

    public void setId_konsultas(String id_konsultas) {
        this.id_konsultas = id_konsultas;
    }

    public String getId_gejala() {
        return id_gejala;
    }

    public void setId_gejala(String id_gejala) {
        this.id_gejala = id_gejala;
    }

    public String getId_petani() {
        return id_petani;
    }

    public void setId_petani(String id_petani) {
        this.id_petani = id_petani;
    }

    public Double getBobot_petani() {
        return bobot_petani;
    }

    public void setBobot_petani(Double bobot_petani) {
        this.bobot_petani = bobot_petani;
    }

    public String getTgl_konsultasi() {
        return tgl_konsultasi;
    }

    public void setTgl_konsultasi(String tgl_konsultasi) {
        this.tgl_konsultasi = tgl_konsultasi;
    }

    public String getHasil_konsultasi() {
        return hasil_konsultasi;
    }

    public void setHasil_konsultasi(String hasil_konsultasi) {
        this.hasil_konsultasi = hasil_konsultasi;
    }
}
