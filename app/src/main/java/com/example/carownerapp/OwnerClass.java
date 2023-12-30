package com.example.carownerapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class OwnerClass {

    @PrimaryKey(autoGenerate = true)
    int ownerID;

    String ownerName;



}
