package asap20.com.sistempakarpepaya.models;

import com.google.gson.annotations.SerializedName;

public class Pengetahuan {
    @SerializedName("id_pengetahuan")
    private String id_pengetahuan;
    @SerializedName("id_penyakit")
    private String id_penyakit;
    @SerializedName("id_gejala")
    private String id_gejala;

    public String getId_pengetahuan() {
        return id_pengetahuan;
    }

    public void setId_pengetahuan(String id_pengetahuan) {
        this.id_pengetahuan = id_pengetahuan;
    }

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

}
