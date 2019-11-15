package com.polinema.mobile_minggu12;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RoomReadSingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        EditText etNama = findViewById(R.id.et_namabarang);
        EditText etMerk = findViewById(R.id.et_merkbarang);
        EditText etHarga = findViewById(R.id.et_hargabarang);
        Button btSubmit = findViewById(R.id.bt_submit);

        etNama.setEnabled(false);
        etMerk.setEnabled(false);
        etHarga.setEnabled(false);
        btSubmit.setVisibility(View.GONE);

        Barang barang = (Barang) getIntent().getSerializableExtra("data");
        if(barang!=null){
            etNama.setText(barang.getNamaBarang());
            etMerk.setText(barang.getMerkBarang());
            etHarga.setText(barang.getHargaBarang());
        }

    }

    public static Intent getActIntent(Activity activity) {
        return new Intent(activity, RoomReadSingleActivity.class);
    }
}
