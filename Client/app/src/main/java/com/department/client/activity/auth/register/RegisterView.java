package com.department.client.activity.auth.register;

public interface RegisterView {
    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    void onRequestError(String message);
}
