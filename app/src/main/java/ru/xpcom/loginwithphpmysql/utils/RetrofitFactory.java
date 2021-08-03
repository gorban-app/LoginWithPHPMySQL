package ru.xpcom.loginwithphpmysql.utils;

import android.media.audiofx.DynamicsProcessing;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.xpcom.loginwithphpmysql.Configs;

public class RetrofitFactory {

    private static Retrofit retrofit = null;

    private RetrofitFactory() {}

    public static Retrofit getRetrofit() {

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Configs.URL_API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return  retrofit;
    }
}
