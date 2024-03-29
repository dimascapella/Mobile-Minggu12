package com.polinema.mobile_minggu12;

import android.app.Activity;
import android.app.Dialog;

import androidx.room.Room;
import android.content.Context;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import com.polinema.mobile_minggu12.R;
import com.polinema.mobile_minggu12.RoomCreateActivity;
import com.polinema.mobile_minggu12.Barang;
import com.polinema.mobile_minggu12.AppDatabase;
import com.polinema.mobile_minggu12.RoomReadSingleActivity;


public class AdapterBarangRecyclerView extends RecyclerView.Adapter<AdapterBarangRecyclerView.ViewHolder>{
    private ArrayList<Barang> daftarBarang;
    private Context context;
    private AppDatabase db;

    public AdapterBarangRecyclerView(ArrayList<Barang> barangs, Context ctx){
        daftarBarang = barangs;
        context = ctx;

        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "tbarang").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        CardView cvMain;

        ViewHolder(View v) {
            super(v);
            tvTitle = v.findViewById(R.id.tv_namabarang);
            cvMain = v.findViewById(R.id.cv_main);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final String name = daftarBarang.get(position).getNamaBarang();

        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Barang barang = db.barangDAO().selectBarangDetail(daftarBarang.get(position).getBarangId());
                context.startActivity(RoomReadSingleActivity.getActIntent((Activity) context).putExtra("data", barang));
            }
        });
        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_dialog);
                dialog.setTitle("Pilih Aksi");
                dialog.show();

                Button editButton = dialog.findViewById(R.id.bt_edit_data);
                Button delButton = dialog.findViewById(R.id.bt_delete_data);

                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onEditBarang(position);
                            }
                        }
                );

                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                                onDeteleBarang(position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    private void onDeteleBarang(int position){
        db.barangDAO().deleteBarang(daftarBarang.get(position));
        daftarBarang.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarBarang.size());
    }

    private void onEditBarang(int position){
        context.startActivity(RoomCreateActivity.getActIntent((Activity) context).putExtra("data", daftarBarang.get(position)));
    }

    @Override
    public int getItemCount() {
        return daftarBarang.size();
    }
}
