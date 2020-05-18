package asap20.com.sistempakarpepaya.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap20.com.sistempakarpepaya.models.Gejala;

public class GejalaResponse {

    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("gejalas")
    private List<Gejala> gejalas;

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

    public List<Gejala> getGejalas() {
        return gejalas;
    }

    public void setGejalas(List<Gejala> gejalas) {
        this.gejalas = gejalas;
    }
}
