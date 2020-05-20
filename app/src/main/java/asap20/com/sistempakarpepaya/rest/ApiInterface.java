package asap20.com.sistempakarpepaya.rest;

import asap20.com.sistempakarpepaya.models.response.AdminResponse;
import asap20.com.sistempakarpepaya.models.response.BaseResponse;
import asap20.com.sistempakarpepaya.models.response.GejalaResponse;
import asap20.com.sistempakarpepaya.models.response.KonsultasiResponse;
import asap20.com.sistempakarpepaya.models.response.PengetahuanResponse;
import asap20.com.sistempakarpepaya.models.response.PenyakitResponse;
import asap20.com.sistempakarpepaya.models.response.PetaniResponse;
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

    @POST("Gejala.php?mode=createGejala")
    @FormUrlEncoded
    Call<BaseResponse> createGejala(@Field("id_gejala") String id_gejala,
                                    @Field("nama_gejala") String nama_admin,
                                    @Field("bobot_gejala") Double bobot_gejala);

    @GET("Gejala.php?mode=getGejalas")
    Call<GejalaResponse> getGejalas();

    @POST("Gejala.php?mode=getGejala")
    @FormUrlEncoded
    Call<GejalaResponse> getGejala(@Field("id_gejala") String id_gejala);

    @POST("Gejala.php?mode=updateGejala")
    @FormUrlEncoded
    Call<BaseResponse> updateGejala(@Field("id_gejala") String id_gejala,
                                    @Field("nama_gejala") String nama_admin,
                                    @Field("bobot_gejala") Double bobot_gejala);

    //Konsultasi

    @POST("Konsultasi.php?mode=createKonsultasi")
    @FormUrlEncoded
    Call<BaseResponse> createKonsultasi(@Field("id_konsultasi") String id_konsultasi,
                                        @Field("id_gejala") String id_gejala,
                                        @Field("id_petani") String id_petani,
                                        @Field("bobot_petani") Double bobot_petani,
                                        @Field("tgl_konsultasi") String tgl_konsultasi,
                                        @Field("hasil_konsultasi") String hasil_konsultasi);

    @POST("Konsultasi.php?mode=getKonsultasi")
    @FormUrlEncoded
    Call<KonsultasiResponse> getKonsultasi(@Field("id_konsultasi") String id_konsultasi);

    @POST("Konsultasi.php?mode=getKonsultasibyPetani")
    @FormUrlEncoded
    Call<KonsultasiResponse> getKonsultasibyPetani(@Field("id_petani") String id_petani);

    @POST("Konsultasi.php?mode=getKonsultasibyTanggal")
    @FormUrlEncoded
    Call<KonsultasiResponse> getKonsultasibyTanggal(@Field("tgl_konsultasi") String tgl_konsultasi);

    @POST("Konsultasi.php?mode=getKonsultasibyGejala")
    @FormUrlEncoded
    Call<KonsultasiResponse> getKonsultasibyGejala(@Field("id_gejala") String id_gejala);

    @GET("Konsultasi.php?mode=getKonsultasis")
    Call<KonsultasiResponse> getKonsultasis();

    @POST("Konsultasi.php?mode=updateKonsultasi")
    @FormUrlEncoded
    Call<BaseResponse> updateKonsultasi(@Field("id_konsultasi") String id_konsultasi,
                                        @Field("id_gejala") String id_gejala,
                                        @Field("id_petani") String id_petani,
                                        @Field("bobot_petani") Double bobot_petani,
                                        @Field("tgl_konsultasi") String tgl_konsultasi,
                                        @Field("hasil_konsultasi") String hasil_konsultasi);

    //Pengetahuan

    @POST("Pengetahuan.php?mode=createKonsultasi")
    @FormUrlEncoded
    Call<BaseResponse> createPengetahuan(@Field("id_pengetahuan") String id_konsultasi,
                                         @Field("id_penyakit") String id_gejala,
                                         @Field("id_gejala") String id_petani);

    @POST("Pengetahuan.php?mode=getPengetahuan")
    @FormUrlEncoded
    Call<PengetahuanResponse> getPengetahuan(@Field("id_pengetahuan") String id_pengetahuan);

    @POST("Pengetahuan.php?mode=getPengetahuanbyPenyakit")
    @FormUrlEncoded
    Call<PengetahuanResponse> getPengetahuanbyPenyakit(@Field("id_penyakit") String id_penyakit);

    @POST("Pengetahuan.php?mode=getPengetahuanbyGejala")
    @FormUrlEncoded
    Call<PengetahuanResponse> getPengetahuanbyGejala(@Field("id_gejala") String id_gejala);

    @GET("Pengetahuan.php?mode=getPengetahuans")
    Call<PengetahuanResponse> getPengetahuans();

    @POST("Pengetahuan.php?mode=updatePengetahuan")
    @FormUrlEncoded
    Call<BaseResponse> updatePengetahuan(@Field("id_pengetahuan") String id_konsultasi,
                                         @Field("id_penyakit") String id_gejala,
                                         @Field("id_gejala") String id_petani);

    //Penyakit

    @POST("Penyakit.php?mode=createPenyakit")
    @FormUrlEncoded
    Call<BaseResponse> createPenyakit(@Field("id_penyakit") String id_penyakit,
                                      @Field("nama_penyakit") String nama_penyakit,
                                      @Field("deskripsi_penyakit") String deskripsi_penyakit,
                                      @Field("gambar_penyakit") String gambar_penyakit);

    @POST("Penyakit.php?mode=getPenyakit")
    @FormUrlEncoded
    Call<PenyakitResponse> getPenyakit(@Field("id_penyakit") String id_penyakit);

    @GET("Penyakit.php?mode=getPenyakits")
    Call<PenyakitResponse> getPenyakits();

    @POST("Penyakit.php?mode=updatePenyakit")
    @FormUrlEncoded
    Call<BaseResponse> updatePenyakit(@Field("id_penyakit") String id_penyakit,
                                      @Field("nama_penyakit") String nama_penyakit,
                                      @Field("deskripsi_penyakit") String deskripsi_penyakit,
                                      @Field("gambar_penyakit") String gambar_penyakit);

    //Petani

    @POST("Petani.php?mode=createPetani")
    @FormUrlEncoded
    Call<BaseResponse> createPetani(@Field("id_petani") String id_petani,
                                    @Field("nama_petani") String nama_petani,
                                    @Field("no_telpon") String no_telpon,
                                    @Field("alamat_petani") String alamat_petani,
                                    @Field("jenis_kelamin") String jenis_kelamin,
                                    @Field("username_petani") String username_petani,
                                    @Field("password_petani") String password_petani);

    @POST("Petani.php?mode=getPetani")
    @FormUrlEncoded
    Call<PetaniResponse> getPetani(@Field("id_petani") String id_petani);

    @GET("Petani.php?mode=getPetanis")
    Call<PetaniResponse> getPetanis();

    @POST("Petani.php?mode=updatePetani")
    @FormUrlEncoded
    Call<BaseResponse> updatePetani(@Field("id_petani") String id_petani,
                                    @Field("nama_petani") String nama_petani,
                                    @Field("no_telpon") String no_telpon,
                                    @Field("alamat_petani") String alamat_petani,
                                    @Field("jenis_kelamin") String jenis_kelamin,
                                    @Field("username_petani") String username_petani,
                                    @Field("password_petani") String password_petani);

    @POST("Petani.php?mode=loginPetani")
    @FormUrlEncoded
    Call<PetaniResponse> loginPetani(@Field("username_petani") String username_petani,
                                     @Field("password_petani") String password_petani);

}
