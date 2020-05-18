package asap20.com.sistempakarpepaya.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import asap20.com.sistempakarpepaya.models.Petani;

public class PetaniResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;
    @SerializedName("Petanis")
    private List<Petani> petanis;

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

    public List<Petani> getPetanis() {
        return petanis;
    }

    public void setPetanis(List<Petani> petanis) {
        this.petanis = petanis;
    }
}
