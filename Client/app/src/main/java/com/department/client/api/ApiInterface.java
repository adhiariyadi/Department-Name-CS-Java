package com.department.client.api;

import com.department.client.reponse.AuthReponse;
import com.department.client.model.Department;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("Login")
    Call<AuthReponse> login(
            @Field("Username") String Username,
            @Field("Password") String Password
    );

    @FormUrlEncoded
    @POST("Register")
    Call<AuthReponse> register(
            @Field("FullName") String FullName,
            @Field("Username") String Username,
            @Field("Password") String Password
    );

    @GET("Department")
    Call<List<Department>> getDepartment();

    @FormUrlEncoded
    @POST("Department")
    Call<Department> saveDepartment(
        @Field("DepartmentName") String DepartmentName,
        @Field("DepartmentDesk") String DepartmentDesk
    );

    @FormUrlEncoded
    @PUT("Department/{DepartmentId}")
    Call<Department> updateDepartment(
            @Path("DepartmentId") int DepartmentId,
            @Field("DepartmentName") String DepartmentName,
            @Field("DepartmentDesk") String DepartmentDesk
    );

    @DELETE("Department/{DepartmentId}")
    Call<Department> deleteDepartment( @Path("DepartmentId") int DepartmentId );

}
