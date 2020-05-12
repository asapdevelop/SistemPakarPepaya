package asap20.com.sistempakarpepaya.rest;

import asap20.com.sistempakarpepaya.models.response.AdminResponse;
import asap20.com.sistempakarpepaya.models.response.BaseResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    //Admin
    @POST("Admin.php?mode=createAdmin")
    @FormUrlEncoded
    Call<BaseResponse> createAdmin(@Field("username_admin") String username_admin,
                                   @Field("password_admin") String password_admin,
                                   @Field("nama_admin") String nama_admin);

    @GET("Admin.php?mode=getAllAdmins")
    Call<AdminResponse> getAllAdmins();

    @POST("Admin.php?mode=getAdmin")
    @FormUrlEncoded
    Call<AdminResponse> getAdmin(@Field("id_admin") int id_admin);

    @POST("Admin.php?mode=updateAdmin")
    @FormUrlEncoded
    Call<AdminResponse> updateAdmin(@Field("id_admin") int id_admin,
                                    @Field("username_admin") String username_admin,
                                    @Field("password_admin") String password_admin,
                                    @Field("nama_admin") String nama_admin);

    @POST("Admin.php?mode=loginAdmin")
    @FormUrlEncoded
    Call<AdminResponse> loginAdmin(@Field("username_admin") String username_admin,
                                   @Field("password_admin") String password_admin);



    //Gejala
    //Konsultasi
    //Pengetahuan
    //Penyakit
    //Petani
}
