package com.polinema.mobile_minggu12;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.polinema.mobile_minggu12.Barang;
import com.polinema.mobile_minggu12.BarangDAO;

@Database(entities = {Barang.class}, version = 1)

public abstract class AppDatabase extends RoomDatabase{
    public abstract BarangDAO barangDAO();
}
