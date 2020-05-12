package asap20.com.sistempakarpepaya.models.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("error")
    private String error;
    @SerializedName("message")
    private String message;

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
}
