package asap20.com.sistempakarpepaya.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap20.com.sistempakarpepaya.models.Penyakit;

public class PenyakitResponse {

    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("Penyakits")
    private List<Penyakit> penyakits;

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

    public List<Penyakit> getPenyakits() {
        return penyakits;
    }

    public void setPenyakits(List<Penyakit> penyakits) {
        this.penyakits = penyakits;
    }
}
