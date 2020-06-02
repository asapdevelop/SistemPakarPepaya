package asap20.com.sistempakarpepaya.models;

import android.os.Parcel;
import android.os.Parcelable;

public class HasilKonsultasiUser implements Parcelable {
    private String idPenyakit;
    private Double nilaiCf;

    public HasilKonsultasiUser(String idPenyakit, Double nilaiCf) {
        this.idPenyakit = idPenyakit;
        this.nilaiCf = nilaiCf;
    }

    public HasilKonsultasiUser(Parcel parcel){
        idPenyakit = parcel.readString();
        nilaiCf = parcel.readDouble();
    }

    public static Creator<HasilKonsultasiUser> CREATOR = new Creator<HasilKonsultasiUser>() {
        @Override
        public HasilKonsultasiUser createFromParcel(Parcel parcel) {
            return new HasilKonsultasiUser(parcel);
        }

        @Override
        public HasilKonsultasiUser[] newArray(int i) {
            return new HasilKonsultasiUser[i];
        }
    };

    public String getIdPenyakit() {
        return idPenyakit;
    }

    public void setIdPenyakit(String idPenyakit) {
        this.idPenyakit = idPenyakit;
    }

    public Double getNilaiCf() {
        return nilaiCf;
    }

    public void setNilaiCf(Double nilaiCf) {
        this.nilaiCf = nilaiCf;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(idPenyakit);
        parcel.writeDouble(nilaiCf);
    }
}
