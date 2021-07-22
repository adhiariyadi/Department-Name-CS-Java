package com.department.client.activity.editor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.department.client.R;
import com.department.client.activity.main.MainActivity;
import com.department.client.activity.main.MainPresenter;
import com.department.client.api.ApiClient;
import com.department.client.api.ApiInterface;
import com.department.client.model.Department;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorActivity extends AppCompatActivity implements EditorView {
    EditText et_DepartmentName, et_DepartmentDesk;
    ProgressDialog progressDialog;

    EditorPresenter presenter;
    MainPresenter mainPresenter;

    int DepartmentId;
    String DepartmentName, DepartmentDesk;

    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        et_DepartmentName = findViewById(R.id.DepartmentName);
        et_DepartmentDesk = findViewById(R.id.DepartmentDesk);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        DepartmentId = intent.getIntExtra("DepartmentId", 0);
        DepartmentName = intent.getStringExtra("DepartmentName");
        DepartmentDesk = intent.getStringExtra("DepartmentDesk");
        
        setDataFromIntentExtra();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor, menu);
        actionMenu = menu;

        if (DepartmentId != 0) {
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        } else {
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String DepartmentName = et_DepartmentName.getText().toString();
        String DepartmentDesk = et_DepartmentDesk.getText().toString();

        switch (item.getItemId()) {
            case R.id.save:
                if (DepartmentName.isEmpty()) {
                    et_DepartmentName.setError("Please entry name");
                } else if (DepartmentDesk.isEmpty()) {
                    et_DepartmentDesk.setError("Please entry description");
                } else {
                    presenter.saveDepartment(DepartmentName, DepartmentDesk);
                }
                return true;
            case R.id.edit:
                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);
                return true;
            case R.id.update:
                if (DepartmentName.isEmpty()) {
                    et_DepartmentName.setError("Please entry name");
                } else if (DepartmentDesk.isEmpty()) {
                    et_DepartmentDesk.setError("Please entry description");
                } else {
                    presenter.updateDepartment(DepartmentId, DepartmentName, DepartmentDesk);
                }
                return true;
            case R.id.delete:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirm!");
                alertDialog.setMessage("Are you sure?");
                alertDialog.setPositiveButton("Cencel", ((dialog, which) -> dialog.dismiss()));
                alertDialog.setNegativeButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    presenter.deleteDepartment(DepartmentId);
                });
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void setDataFromIntentExtra() {
        if (DepartmentId != 0) {
            et_DepartmentName.setText(DepartmentName);
            et_DepartmentDesk.setText(DepartmentDesk);

            getSupportActionBar().setTitle("Update Data Department");
            readMode();
        } else {
            editMode();
        }
    }

    private void readMode() {
        et_DepartmentName.setFocusableInTouchMode(false);
        et_DepartmentDesk.setFocusableInTouchMode(false);
        et_DepartmentName.setFocusable(false);
        et_DepartmentDesk.setFocusable(false);
    }

    private void editMode() {
        et_DepartmentName.setFocusableInTouchMode(true);
        et_DepartmentDesk.setFocusableInTouchMode(true);
    }
}