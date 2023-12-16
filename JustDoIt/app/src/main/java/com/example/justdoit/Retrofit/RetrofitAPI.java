package com.example.justdoit.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @GET("/todo/{user_id}")
    Call<List<TodoModel>> getTodo(
            @Path("user_id") int user_id
    );

    @GET("/dailytodo/{user_id}")
    Call<List<DailyTodoModel>> getDailyTodo(
            @Path("user_id") int user_id
    );

    @FormUrlEncoded
    @POST("/create/todo")
    Call<Result> createTodo(
            @Field("user_id") String user_id,
            @Field("todo_name") String name,
            @Field("deadline") String deadline
    );

    @FormUrlEncoded
    @POST("/edit/todo")
    Call<Result> editTodo(
            @Field("user_id") int user_id,
            @Field("todo_name") String name,
            @Field("deadline") String deadline
    );

    @POST("/dailytodo/")
    Call<TodoModel> createDailyTodo(
            int user_id,
            String name
    );
    @FormUrlEncoded
    @PUT("/todo/{todo_name}")
    Call<Result> EditTodo(
            @Field("user_id") String user_id,
            @Field("todo_name") String name,
            @Field("deadline") String deadline
    );
    @FormUrlEncoded
    @POST("/delete")
    Call<Result> DeleteTodo(
            @Field("todo_name") String todo_name
    );
}