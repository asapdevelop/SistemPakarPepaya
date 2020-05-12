package asap20.com.sistempakarpepaya.models;

import com.google.gson.annotations.SerializedName;

public class Admin {
    @SerializedName("id_admin")
    private Integer id_admin;
    @SerializedName("username_admin")
    private String username_admin;
    @SerializedName("password_admin")
    private String password_admin;
    @SerializedName("nama_admin")
    private String nama_admin;

    public Integer getId_admin() {
        return id_admin;
    }

    public void setId_admin(Integer id_admin) {
        this.id_admin = id_admin;
    }

    public String getUsername_admin() {
        return username_admin;
    }

    public void setUsername_admin(String username_admin) {
        this.username_admin = username_admin;
    }

    public String getPassword_admin() {
        return password_admin;
    }

    public void setPassword_admin(String password_admin) {
        this.password_admin = password_admin;
    }

    public String getNama_admin() {
        return nama_admin;
    }

    public void setNama_admin(String nama_admin) {
        this.nama_admin = nama_admin;
    }
}
