package ru.xpcom.loginwithphpmysql.login;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginAPI {

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> login(@Field("login") String login, @Field("password") String password);

}
