package com.example.carownerapp;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class OwnerCarRelationship {

    @Embedded OwnerClass owner;// one - many relationship

    @Relation(
            parentColumn = "ownerID",
            entityColumn = "car_ownerID"
    )
    List<CarClass> allCars;

}
