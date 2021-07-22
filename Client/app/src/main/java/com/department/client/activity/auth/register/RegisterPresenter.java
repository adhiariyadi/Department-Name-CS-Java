package com.department.client.activity.auth.register;

import com.department.client.api.ApiClient;
import com.department.client.api.ApiInterface;
import com.department.client.reponse.AuthReponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    private RegisterView view;

    public RegisterPresenter(RegisterView view) {
        this.view = view;
    }

    void register(String FullName, String Username, String Password) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<AuthReponse> call = apiInterface.register(FullName, Username, Password);

        call.enqueue(new Callback<AuthReponse>() {
            @Override
            public void onResponse(Call<AuthReponse> call, Response<AuthReponse> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthReponse> call, Throwable t) {
                view.hideProgress();
                view.onRequestSuccess(t.getLocalizedMessage());
            }
        });
    }
}
