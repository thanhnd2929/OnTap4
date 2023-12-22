package com.example.ontapandroid2_4.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapandroid2_4.Adapter.DichVuAdapter;
import com.example.ontapandroid2_4.DAO.DichVuDAO;
import com.example.ontapandroid2_4.DTO.DichVuDTO;
import com.example.ontapandroid2_4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class fragment_dv extends Fragment {

    RecyclerView rv_dv;
    FloatingActionButton fb_add;
    DichVuDAO dichVuDAO;
    ArrayList<DichVuDTO> list;

    DichVuAdapter dichVuAdapter;

    LinearLayoutManager linearLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dv_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rv_dv = view.findViewById(R.id.rv_dv);
        fb_add = view.findViewById(R.id.fb_add);

        dichVuDAO = new DichVuDAO(getContext());
        list = dichVuDAO.getList();

        linearLayoutManager = new LinearLayoutManager(getContext());
        rv_dv.setLayoutManager(linearLayoutManager);

        dichVuAdapter = new DichVuAdapter(getContext(), list);
        rv_dv.setAdapter(dichVuAdapter);

        fb_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = getLayoutInflater().inflate(R.layout.dialog_them, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();

                EditText edt_thanhTien = view1.findViewById(R.id.edt_thanhTien);
                EditText edt_Ngay = view1.findViewById(R.id.edt_Ngay);
                EditText edt_noiDung = view1.findViewById(R.id.edt_noiDung);
                Button btn_Them = view1.findViewById(R.id.btn_Them);
                Button btn_HuyThem = view1.findViewById(R.id.btn_HuyThem);

                btn_Them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tien = edt_thanhTien.getText().toString().trim();
                        String ngay = edt_Ngay.getText().toString().trim();
                        String noidung = edt_noiDung.getText().toString().trim();

                        if (tien.isEmpty()||ngay.isEmpty()||noidung.isEmpty()) {
                            Toast.makeText(getContext(), "Khong bo trong du lieu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            int tien_int = Integer.parseInt(tien);
                            if (tien_int < 0) {
                                Toast.makeText(getContext(), "tien phai > 0", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            int result = dichVuDAO.addRow(new DichVuDTO(noidung, ngay, tien_int));
                            if (result > 0) {
                                list.clear();
                                list.addAll(dichVuDAO.getList());
                                dichVuAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        } catch (NumberFormatException  e) {
                            Toast.makeText(getContext(), "tien phai la so", Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }
        });



    }
}
