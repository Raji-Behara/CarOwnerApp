package com.example.carownerapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface OwnerCarDAO {

    @Query("select * from OwnerClass")
    List<OwnerClass> getAllOwners();

    @Insert
    void addNewOwner(OwnerClass owner);

    // deleting one owner means deleting all cars for that owner.
    @Query("select * from CarClass")
    List<CarClass> getAllCars();

    @Delete
    void deleteOneCar(CarClass c);

    @Query("delete from OwnerClass")
    void deleteAllOwner();

    @Query("delete from CarClass")
    void deleteAllCars();



    @Delete
    void deleteOneOwner(OwnerClass o);

    @Query("delete from CarClass where car_ownerID = :ownerID")
        void deleteCarsForOwner(int ownerID);


    @Insert
    void addNewCarForOneOwner(CarClass car);

    // get all cars for one Owner.

    @Transaction
    @Query("select * from OwnerClass o, CarClass c where o.ownerID = :ownerID and o.ownerID = c.car_ownerID ")
   OwnerCarRelationship getAllCarsForOneOwnerID(int ownerID);


    @Transaction
    @Query("select * from OwnerClass o, CarClass c where o.ownerID == c.car_ownerID and o.ownerID like :ownerName")
    OwnerCarRelationship getAllCarsForOneOwnerName(String ownerName);


}
