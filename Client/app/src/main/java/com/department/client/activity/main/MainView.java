package com.department.client.activity.main;

import com.department.client.model.Department;

import java.util.List;

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Department> departments);
    void onErrorLoading(String message);
}
