package com.department.client.activity.auth.register;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.department.client.R;
import com.department.client.activity.auth.login.LoginActivity;
import com.department.client.activity.auth.login.LoginPresenter;
import com.department.client.activity.auth.login.LoginView;
import com.department.client.activity.main.MainActivity;
import com.department.client.activity.main.MainView;

public class RegisterActivity extends AppCompatActivity implements RegisterView, View.OnClickListener {
    EditText et_FullName, et_Username, et_Password, et_ConfirmPassword;
    Button register;
    ProgressDialog progressDialog;

    RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_FullName = findViewById(R.id.FullName);
        et_Username = findViewById(R.id.Username);
        et_Password = findViewById(R.id.Password);
        et_ConfirmPassword = findViewById(R.id.ConfirmPassword);
        register = findViewById(R.id.register);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        presenter = new RegisterPresenter(this);

        register.setOnClickListener(this);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v == register) {
            String FullName = et_FullName.getText().toString();
            String Username = et_Username.getText().toString();
            String Password = et_Password.getText().toString();
            String ConfirmPassword = et_ConfirmPassword.getText().toString();
            if (FullName.isEmpty()) {
                et_Username.setError("Please entry full name");
            } else if (Username.isEmpty()) {
                et_Username.setError("Please entry username");
            } else if (Password.isEmpty()) {
                et_Password.setError("Please entry password");
            } else {
                if (!Password.equals(ConfirmPassword)) {
                    et_ConfirmPassword.setError("Passwords are not the same");
                } else {
                    presenter.register(FullName, Username, Password);
                }
            }
        }
    }
}