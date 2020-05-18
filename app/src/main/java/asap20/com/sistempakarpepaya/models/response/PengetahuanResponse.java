package asap20.com.sistempakarpepaya.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap20.com.sistempakarpepaya.models.Pengetahuan;

public class PengetahuanResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("pengetahuans")
    private List<Pengetahuan> pengetahuans;

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

    public List<Pengetahuan> getPengetahuans() {
        return pengetahuans;
    }

    public void setPengetahuans(List<Pengetahuan> pengetahuans) {
        this.pengetahuans = pengetahuans;
    }
}
