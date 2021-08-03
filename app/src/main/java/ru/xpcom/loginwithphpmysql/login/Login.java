package ru.xpcom.loginwithphpmysql.login;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("login")
    private String login;

    @SerializedName("password")
    private String password;

}
