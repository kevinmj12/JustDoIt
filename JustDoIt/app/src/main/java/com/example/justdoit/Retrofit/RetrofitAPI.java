package com.example.justdoit.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

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
    @POST("/login/")
    Call<Result> login(
            @Field("id") String id,
            @Field("pw") String pw
    );
    public class PostTodo {
        @SerializedName("user_id")
        private int user_id;
        @SerializedName("name")
        private String name;
        @SerializedName("deadline")
        private String deadline;

        public PostTodo(int user_id, String name, String deadline) {
            this.user_id = user_id;
            this.name = name;
            this.deadline = deadline;
        }
    }
    @POST("/todo/")
    Call<List<TodoModel>> createTodo(
            @Body PostTodo postTodo
    );
    @POST("/daily todo/")
    Call<List<TodoModel>> createDailyTodo(
            int user_id,
            String name
    );
    @FormUrlEncoded
    @PUT("/todo/{todo_name}")
    Call<List<TodoModel>> EditTodo(
            @Field("user_id") int user_id,
            @Field("todo_name") String name,
            @Field("deadline") String deadline
    );
    @DELETE("/todo/{todo_name}")
    Call<List<TodoModel>> DeleteTodo(
            @Path("user_id") int user_id,
            @Path("todo_name") String name
    );
}
