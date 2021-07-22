package com.department.client.activity.auth.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.department.client.R;
import com.department.client.activity.auth.register.RegisterActivity;
import com.department.client.activity.main.MainActivity;
import com.department.client.api.ApiClient;
import com.department.client.api.ApiInterface;
import com.department.client.reponse.AuthReponse;
import com.department.client.model.User;
import com.department.client.session.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et_Username, et_Password;
    Button login, btn_register;
    ProgressDialog progressDialog;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_Username = findViewById(R.id.Username);
        et_Password = findViewById(R.id.Password);
        login = findViewById(R.id.login);
        btn_register = findViewById(R.id.btn_register);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        sessionManager = new SessionManager(this);

        login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == login) {
            String Username = et_Username.getText().toString();
            String Password = et_Password.getText().toString();

            if (Username.isEmpty()) {
                et_Username.setError("Please entry username");
            } else if (Password.isEmpty()) {
                et_Password.setError("Please entry password");
            } else {
                progressDialog.show();

                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
                Call<AuthReponse> call = apiInterface.login(Username, Password);

                call.enqueue(new Callback<AuthReponse>() {
                    @Override
                    public void onResponse(Call<AuthReponse> call, Response<AuthReponse> response) {
                        progressDialog.dismiss();
                        if (response.isSuccessful() && response.body() != null) {
                            Boolean success = response.body().getSuccess();
                            if (success) {
                                List<User> user = response.body().getUserList();
                                sessionManager.setLogin(success);
                                sessionManager.setUserId(user.get(0).getUserId());
                                sessionManager.setFullName(user.get(0).getFullName());

                                Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AuthReponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } else if (v == btn_register) {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }
    }
}