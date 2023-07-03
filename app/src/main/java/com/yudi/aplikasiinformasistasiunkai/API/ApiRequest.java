package com.yudi.aplikasiinformasistasiunkai.API;

import com.yudi.aplikasiinformasistasiunkai.Model.StasiunKAIResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRequest {
    @GET("read.php")
    Call<StasiunKAIResponse> stasiunkaiRead();

    @FormUrlEncoded
    @POST("create.php")
    Call<StasiunKAIResponse> stasiunkasiCreate(
            @Field("nama") String nama,
            @Field("foto") String foto,
            @Field("sejarah") String sejarah,
            @Field("kota") String kota,
            @Field("luas") Integer luas
    );

    @FormUrlEncoded
    @POST("update.php")
    Call<StasiunKAIResponse> stasiunkasiUpdate(
            @Field("id") Integer id,
            @Field("nama") String nama,
            @Field("foto") String foto,
            @Field("sejarah") String sejarah,
            @Field("kota") String kota,
            @Field("luas") Integer luas
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<StasiunKAIResponse> stasiunkasiDelete(
            @Field("id") Integer id
    );
}
