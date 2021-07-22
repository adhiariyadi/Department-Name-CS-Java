package com.department.client.activity.editor;

import androidx.annotation.NonNull;

import com.department.client.api.ApiClient;
import com.department.client.api.ApiInterface;
import com.department.client.model.Department;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

    void saveDepartment(final String DepartmentName, final String DepartmentDesk) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Department> call = apiInterface.saveDepartment(DepartmentName, DepartmentDesk);

        call.enqueue(new Callback<Department>() {
            @Override
            public void onResponse(@NonNull Call<Department> call, @NonNull Response<Department> response) {
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
            public void onFailure(@NonNull Call<Department> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestSuccess(t.getLocalizedMessage());
            }
        });
    }

    void updateDepartment(int DepartmentId, String DepartmentName, String DepartmentDesk) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Department> call = apiInterface.updateDepartment(DepartmentId, DepartmentName, DepartmentDesk);

        call.enqueue(new Callback<Department>() {
            @Override
            public void onResponse(@NonNull Call<Department> call, @NonNull Response<Department> response) {
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
            public void onFailure(@NonNull Call<Department> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    void deleteDepartment(int DepartmentId) {
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Department> call = apiInterface.deleteDepartment(DepartmentId);

        call.enqueue(new Callback<Department>() {
            @Override
            public void onResponse(@NonNull Call<Department> call, @NonNull Response<Department> response) {
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
            public void onFailure(@NonNull Call<Department> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

}
