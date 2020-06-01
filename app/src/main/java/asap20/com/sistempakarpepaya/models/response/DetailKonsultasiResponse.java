package asap20.com.sistempakarpepaya.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap20.com.sistempakarpepaya.models.DetailKonsultasi;

public class DetailKonsultasiResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("detailKonsultasis")
    private List<DetailKonsultasi> detailKonsultasis;

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

    public List<DetailKonsultasi> getDetailKonsultasis() {
        return detailKonsultasis;
    }

    public void setDetailKonsultasis(List<DetailKonsultasi> detailKonsultasis) {
        this.detailKonsultasis = detailKonsultasis;
    }
}
