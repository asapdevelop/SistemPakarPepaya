package asap20.com.sistempakarpepaya.models;

import com.google.gson.annotations.SerializedName;

public class DetailKonsultasi {
    @SerializedName("id_detail")
    private int id_detail;
    @SerializedName("id_konsultas")
    private int id_konsultas;
    @SerializedName("nama_petani")
    private String nama_petani;

    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public int getId_konsultas() {
        return id_konsultas;
    }

    public void setId_konsultas(int id_konsultas) {
        this.id_konsultas = id_konsultas;
    }

    public String getNama_petani() {
        return nama_petani;
    }

    public void setNama_petani(String nama_petani) {
        this.nama_petani = nama_petani;
    }
}
