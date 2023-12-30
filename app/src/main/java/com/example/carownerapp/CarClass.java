package com.example.carownerapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CarClass {

    @PrimaryKey(autoGenerate = true)
    int carID;
    int year;
    String Model;


    int car_ownerID;
}
