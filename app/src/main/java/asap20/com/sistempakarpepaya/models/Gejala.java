package asap20.com.sistempakarpepaya.models;

import com.google.gson.annotations.SerializedName;

public class Gejala {
    @SerializedName("id_gejala")
    private String id_gejala;
    @SerializedName("nama_gejala")
    private String nama_gejala;

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
}
