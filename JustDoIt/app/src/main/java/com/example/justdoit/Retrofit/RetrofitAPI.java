package com.example.justdoit.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/todo/{user_id}")
    Call<List<TodoModel>> getTodo(
            @Path("user_id") int user_id
    );

    @FormUrlEncoded
    @POST("/login/")
    Call<Result> login(
            @Field("id") String id,
            @Field("pw") String pw
    );
}
