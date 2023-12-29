package com.example.ktra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private listAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DAO sanPhamDAO = new DAO(this);
        ArrayList<sanPham> listSanPham = sanPhamDAO.getListSanPham();
        sanPhamAdapter = new listAdapter(this, listSanPham);
        recyclerView.setAdapter(sanPhamAdapter);
    }

    public void refreshRecyclerView() {
        DAO sanPhamDAO = new DAO(MainActivity.this);
        ArrayList<sanPham> list = sanPhamDAO.getListSanPham();
        listAdapter sanPhamAdapter = new listAdapter(MainActivity.this, list);
        recyclerView.setAdapter(sanPhamAdapter);
    }
}
