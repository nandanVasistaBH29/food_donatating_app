package bd.edu.rifat.foodDonationApp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import bd.edu.rifat.foodDonationApp.daos.FoodDao;
import bd.edu.rifat.foodDonationApp.entities.Food;

@Database(entities = {Food.class}, version = 1)
public abstract class FoodDonarDb extends RoomDatabase {

    public abstract FoodDao getStudentDao();

    private static FoodDonarDb db;


    public static FoodDonarDb getDb(Context context){

        if (db ==null){
            db= Room.databaseBuilder(context,FoodDonarDb.class,"food_db")
                    .allowMainThreadQueries()
                    .build();
            return db;
        }
        return db;
    }


}
