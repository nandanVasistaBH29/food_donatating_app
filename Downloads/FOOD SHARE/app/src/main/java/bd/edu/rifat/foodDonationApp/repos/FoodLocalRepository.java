package bd.edu.rifat.foodDonationApp.repos;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import bd.edu.rifat.foodDonationApp.daos.FoodDao;
import bd.edu.rifat.foodDonationApp.db.FoodDonarDb;
import bd.edu.rifat.foodDonationApp.entities.Food;

public class FoodLocalRepository {

    private FoodDao foodDao;

    public FoodLocalRepository(Context context){

        foodDao= FoodDonarDb.getDb(context).getStudentDao();
    }

    public void addStudent(Food food){

        foodDao.insertFoodDonar(food);

    }


    public LiveData<List<Food>> getAllDonars(){
        return foodDao.getAllDonars();
    }

}
