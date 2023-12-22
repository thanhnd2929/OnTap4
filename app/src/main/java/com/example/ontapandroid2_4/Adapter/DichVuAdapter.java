package com.example.ontapandroid2_4.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapandroid2_4.DAO.DichVuDAO;
import com.example.ontapandroid2_4.DTO.DichVuDTO;
import com.example.ontapandroid2_4.R;

import java.util.ArrayList;

public class DichVuAdapter extends RecyclerView.Adapter<DichVuAdapter.viewHolder> {

    Context context;
    ArrayList<DichVuDTO> list;

    public DichVuAdapter(Context context, ArrayList<DichVuDTO> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_dv, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DichVuDTO dto = list.get(position);
        DichVuDAO dichVuDAO = new DichVuDAO(context);
        holder.txt_madv.setText("Ma Dich Vu: " + dto.getMadv());
        holder.txt_noidung.setText("Noi Dung: " + dto.getNoidung());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view1 = inflater.inflate(R.layout.dialog_ct, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();

                TextView txt_Ngay = view1.findViewById(R.id.txt_Ngay);
                TextView txt_ThanhTien = view1.findViewById(R.id.txt_ThanhTien);

                txt_Ngay.setText("Ngày: " + dto.getNgay());
                txt_ThanhTien.setText("Thannh Tien: "+dto.getThanhtien());
                return false;
            }
        });

        holder.btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = dichVuDAO.deleteRow(dto);
                if (result>0) {
                    list.remove(dto);
                    notifyDataSetChanged();
                    Toast.makeText(context,"Xoa thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder.btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_sua, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                EditText edt_Ngay = view.findViewById(R.id.edt_Ngay);
                EditText edt_noiDung = view.findViewById(R.id.edt_noiDung);
                EditText edt_thanhTien = view.findViewById(R.id.edt_thanhTien);
                Button btn_SuaDV = view.findViewById(R.id.btn_SuaDV);
                Button btn_HuySua = view.findViewById(R.id.btn_HuySua);


                edt_Ngay.setText(dto.getNgay());
                edt_noiDung.setText(dto.getNoidung());
                edt_thanhTien.setText(dto.getThanhtien()+"");

                btn_SuaDV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ngay = edt_Ngay.getText().toString().trim();
                        String noidung = edt_noiDung.getText().toString().trim();
                        String thanhTien = edt_thanhTien.getText().toString().trim();


                        dto.setNgay(ngay);
                        dto.setNoidung(noidung);
                        dto.setThanhtien(Integer.parseInt(thanhTien));

                        if (ngay.isEmpty()||noidung.isEmpty()||thanhTien.isEmpty()) {
                            Toast.makeText(context,"Không bỏ trống dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            int tien = Integer.parseInt(thanhTien);
                            if (tien < 0) {
                                Toast.makeText(context, "Tiền phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                                return;
                            }



                            int result = dichVuDAO.updateRow(dto);
                            if (result > 0) {
                                notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(context, "Da chinh sua", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException e) {
                            Toast.makeText(context, "Tiền phải là số", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btn_HuySua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView txt_madv, txt_noidung;
        Button btn_sua, btn_xoa;

        DichVuAdapter dichVuAdapter;
        public viewHolder(@NonNull View itemView) {
            super(itemView);

            dichVuAdapter = new DichVuAdapter(context, list);

            txt_madv = itemView.findViewById(R.id.txt_maDv);
            txt_noidung = itemView.findViewById(R.id.txt_noiDung);
            btn_sua = itemView.findViewById(R.id.btn_sua);
            btn_xoa = itemView.findViewById(R.id.btn_xoa);

        }
    }
}
