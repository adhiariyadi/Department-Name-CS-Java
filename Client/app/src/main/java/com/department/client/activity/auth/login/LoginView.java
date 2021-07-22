package com.department.client.activity.auth.login;

public interface LoginView {
    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
