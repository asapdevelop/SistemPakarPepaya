package asap20.com.sistempakarpepaya.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap20.com.sistempakarpepaya.models.Konsultasi;

public class KonsultasiResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("konsultasis")
    private List<Konsultasi> konsultasis;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Konsultasi> getKonsultasis() {
        return konsultasis;
    }

    public void setKonsultasis(List<Konsultasi> konsultasis) {
        this.konsultasis = konsultasis;
    }
}
