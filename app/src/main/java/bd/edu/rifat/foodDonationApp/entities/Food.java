package bd.edu.rifat.foodDonationApp.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tbl_food")
public class Food implements Serializable {


    public static final String MALE = "INDIVIDUAL";
    public static final String FEMALE = "ORGANIZATION";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "age")
    private double age;
    @ColumnInfo(name = "phone")
    private String phone;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name = "foodGroup")
    private String foodGroup;

    @Ignore
    public Food(int id, String name, double age, String phone, String gender, String foodGroup) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
        this.foodGroup = foodGroup;
    }

    @Ignore
    public Food(String name, double age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public Food(String name, double age, String phone, String gender, String foodGroup) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.gender = gender;
        this.foodGroup = foodGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(String foodGroup) {
        this.foodGroup = foodGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
