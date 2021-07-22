package com.department.client.activity.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Toast;

import com.department.client.R;
import com.department.client.activity.auth.login.LoginActivity;
import com.department.client.activity.editor.EditorActivity;
import com.department.client.model.Department;
import com.department.client.session.SessionManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    SessionManager sessionManager;

    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;

    List<Department> department;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.sessionManager = new SessionManager(this);

        if (this.sessionManager.getLogin() == false) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        fab = findViewById(R.id.add);
        fab.setOnClickListener(view -> {
            startActivityForResult(
                new Intent(this, EditorActivity.class),
                INTENT_ADD
            );
        });

        presenter = new MainPresenter(this);
        presenter.getData();

        swipeRefresh.setOnRefreshListener(
            () -> presenter.getData()
        );

        itemClickListener = ((view, position) -> {
            int DepartmentId = department.get(position).getDepartmentId();
            String DepartmentName = department.get(position).getDepartmentName();
            String DepartmentDesk = department.get(position).getDepartmentDesk();

            Intent intent = new Intent(this, EditorActivity.class);
            intent.putExtra("DepartmentId", DepartmentId);
            intent.putExtra("DepartmentName", DepartmentName);
            intent.putExtra("DepartmentDesk", DepartmentDesk);
            startActivityForResult(intent, INTENT_EDIT);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == INTENT_ADD && requestCode == RESULT_OK) {
            presenter.getData();
        } else if (requestCode == INTENT_EDIT && requestCode == RESULT_OK) {
            presenter.getData();
        }
    }

    @Override
    public void showLoading() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<Department> departments) {
        adapter = new MainAdapter(this, departments, itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        department = departments;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}