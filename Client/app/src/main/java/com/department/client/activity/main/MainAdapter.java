package com.department.client.activity.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.department.client.R;
import com.department.client.model.Department;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {
    private Context context;
    private List<Department> departments;
    private ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Department> departments, ItemClickListener itemClickListener) {
        this.context = context;
        this.departments = departments;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_department, parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.RecyclerViewAdapter holder, int position) {
        Department department = departments.get(position);
        holder.tv_DepartmentName.setText(department.getDepartmentName());
        holder.tv_DepartmentDesk.setText(department.getDepartmentDesk());
        holder.card_item.setCardBackgroundColor(-8469267);
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_DepartmentName, tv_DepartmentDesk;
        CardView card_item;
        ItemClickListener itemClickListener;

        RecyclerViewAdapter(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            tv_DepartmentName = itemView.findViewById(R.id.DepartmentName);
            tv_DepartmentDesk = itemView.findViewById(R.id.DepartmentDesk);
            card_item = itemView.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this);;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
