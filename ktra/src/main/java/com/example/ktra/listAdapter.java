package com.example.ktra;


import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.SanPhamViewHolder> {
    private Context context;
    private List<sanPham> sanPhamList;

    public listAdapter(Context context, List<sanPham> sanPhamList) {
        this.context = context;
        this.sanPhamList = sanPhamList;
    }

    @Override
    public SanPhamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_sp, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SanPhamViewHolder holder, int position) {
        sanPham sanPham = this.sanPhamList.get(position);
        holder.txtName.setText(sanPham.getTenSP());
        holder.txtPrice.setText(sanPham.getGiaTien().toString());
            Picasso picasso = Picasso.get();
            picasso.load(sanPham.getImage())
                .into(holder.productImage);
        holder.deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Bạn có chắc chắn muốn xóa không?")
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DAO sanPhamDAO = new DAO(context);
                                sanPhamDAO.deleteSP(sanPham.getMaSP());
                                refreshRecyclerView();
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });
    }
    private void refreshRecyclerView() {
        DAO sanPhamDAO = new DAO(context);
        sanPhamList = sanPhamDAO.getListSanPham();
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }
    public static  class SanPhamViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice;
        ImageView productImage, deleteImage ;
        public SanPhamViewHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            productImage = itemView.findViewById(R.id.productImage);
            deleteImage = itemView.findViewById(R.id.deleteImage);
        }
    }
}
