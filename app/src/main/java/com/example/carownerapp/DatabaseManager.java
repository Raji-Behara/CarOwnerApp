package com.example.carownerapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Delete;
import androidx.room.Room;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseManager {

    OwnerCarDatabase db;
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    Handler mainLooperHandler = new Handler(Looper.getMainLooper());

    interface DatabaseListener {
        void DBManagerGetAllOwners(List<OwnerClass> list);
        void UpdatingIsDone();
        void carsForOneOwner(OwnerCarRelationship r);
    }

    DatabaseListener listener;
    OwnerCarDatabase getDB (Context context){

        if (db == null)
             db = Room.databaseBuilder(context,
                OwnerCarDatabase.class, "database-name").build();
        return db;
    }

    void insertNewCar(CarClass car){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().addNewCarForOneOwner(car);
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.UpdatingIsDone();
                    }
                });
            }
        });
    }
    void ClearTheDB(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().deleteAllOwner();
                db.getDao().deleteAllCars();
            }
        });
    }
    void insertNewOwner(OwnerClass owner){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().addNewOwner(owner);
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.UpdatingIsDone();
                    }
                });
            }
        });
    }



    void getAllOwners(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<OwnerClass> list = db.getDao().getAllOwners();
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.DBManagerGetAllOwners(list);
                    }
                });
            }
        });
    }

    void getAllCars(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<CarClass> list =  db.getDao().getAllCars();
                list.size();
            }
        });
    }
    void deleteOneCar(CarClass c){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().deleteOneCar(c);
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.UpdatingIsDone();
                    }
                });
            }
        });
    }

    void deleteOneOwner(OwnerClass o){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                db.getDao().deleteCarsForOwner(o.ownerID);
                db.getDao().deleteOneOwner(o);
                mainLooperHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.UpdatingIsDone();
                    }
                });
            }
        });
    }


    void getAllCarsForOneOwner(int id){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
              OwnerCarRelationship r =  db.getDao().getAllCarsForOneOwnerID(id);
              mainLooperHandler.post(new Runnable() {
                  @Override
                  public void run() {
                      listener.carsForOneOwner(r);
                  }
              });
            }
        });
    }

}
