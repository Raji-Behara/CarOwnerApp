package com.example.carownerapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.carownerapp.CarClass;
import com.example.carownerapp.OwnerClass;

@Database(version = 1, entities = {CarClass.class, OwnerClass.class})
public abstract class OwnerCarDatabase extends RoomDatabase {
    public abstract OwnerCarDAO getDao();
}
