package asap20.com.sistempakarpepaya.models;

import com.google.gson.annotations.SerializedName;

public class DetailKonsultasi {
    @SerializedName("id_detail")
    private int id_detail;
    @SerializedName("id_konsultas")
    private int id_konsultas;
    @SerializedName("id_gejala")
    private String id_gejala;

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

    public String getId_gejala() {
        return id_gejala;
    }

    public void setId_gejala(String id_gejala) {
        this.id_gejala = id_gejala;
    }
}
