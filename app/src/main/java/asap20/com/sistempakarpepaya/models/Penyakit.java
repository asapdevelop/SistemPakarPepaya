package asap20.com.sistempakarpepaya.models;

import com.google.gson.annotations.SerializedName;

public class Penyakit {
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
}
