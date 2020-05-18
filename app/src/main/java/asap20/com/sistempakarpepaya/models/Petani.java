package asap20.com.sistempakarpepaya.models;

import com.google.gson.annotations.SerializedName;

public class Petani {
    @SerializedName("id_petani")
    private String id_petani;
    @SerializedName("nama_petani")
    private String nama_petani;
    @SerializedName("no_telpon")
    private String no_telpon;
    @SerializedName("alamat_petani")
    private String alamat_petani;
    @SerializedName("jenis_kelamin")
    private String jenis_kelamin;
    @SerializedName("username_petani")
    private String username_petani;
    @SerializedName("password_petani")
    private String password_petani;

    public String getId_petani() {
        return id_petani;
    }

    public void setId_petani(String id_petani) {
        this.id_petani = id_petani;
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

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getUsername_petani() {
        return username_petani;
    }

    public void setUsername_petani(String username_petani) {
        this.username_petani = username_petani;
    }

    public String getPassword_petani() {
        return password_petani;
    }

    public void setPassword_petani(String password_petani) {
        this.password_petani = password_petani;
    }
}
