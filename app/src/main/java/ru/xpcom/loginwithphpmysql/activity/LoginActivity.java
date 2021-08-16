package ru.xpcom.loginwithphpmysql.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.xpcom.loginwithphpmysql.R;
import ru.xpcom.loginwithphpmysql.login.Login;
import ru.xpcom.loginwithphpmysql.login.LoginAPI;
import ru.xpcom.loginwithphpmysql.utils.NetworkState;
import ru.xpcom.loginwithphpmysql.utils.RetrofitFactory;
import ru.xpcom.loginwithphpmysql.view.DialogView;

public class LoginActivity extends AppCompatActivity {

    private EditText editLogin, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uiComponent();
    }

    public void btnAuthentication(View view) {

        String stringLogin = editLogin.getText().toString();
        String stringPassword = editPassword.getText().toString();
        if (TextUtils.isEmpty(stringLogin)) {
            DialogView.showDialogError(LoginActivity.this, R.string.error_login);
        } else if (TextUtils.isEmpty(stringPassword)) {
            DialogView.showDialogError(LoginActivity.this, R.string.error_password);
        } else {
            if (NetworkState.isOnline(this))
                loginCheck(stringLogin, stringPassword);
            else DialogView.showDialogError(LoginActivity.this, R.string.error_internet);
        }
    }

    private void loginCheck(String login, String password) {
        Retrofit retrofit = RetrofitFactory.getRetrofit();
        LoginAPI loginAPI = retrofit.create(LoginAPI.class);
        Call<Login> call = loginAPI.login(login, password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Login result = response.body();
                if (result != null) {
                    if (response.code() == 202) {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        Log.d("HTTP status code", "202 Accepted");
                    }
                } else {
                    if (response.code() == 401) {
                        DialogView.showDialogError(LoginActivity.this, R.string.error_login_password);
                        Log.d("HTTP status code", "401 Unauthorized");


                    } else {
                        Log.e("HTTP status code", "" + response.code());
                        Toast.makeText(getApplicationContext(),
                                R.string.error_server, Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                // on Failure
                Log.e("onFailure", t.getMessage());
            }
        });

    }

    private void uiComponent() {
        editLogin = (EditText) findViewById(R.id.edit_login);
        editPassword = (EditText) findViewById(R.id.edit_password);
        FloatingActionButton btn_auth = (FloatingActionButton) findViewById(R.id.btn_auth);
    }

}