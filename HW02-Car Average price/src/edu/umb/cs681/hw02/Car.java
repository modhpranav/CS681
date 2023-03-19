package edu.umb.cs681.hw02;

import java.util.*;

public class Car {
    private int price;
    private int year;
    private int mileage;
    private ArrayList<Car> cars;
    private String name;
    private int dominationCount = 0;

    public Car(String name,int year,int price,int mileage) {

        this.year=year;
        this.price=price;
        this.mileage=mileage;
        this.name=name;

    };
    public String toString(){
        return name;
    };
    public int getPrice(){
        return price;
    };
    public int getMileage(){

        return mileage;
    };
    public int getYear(){
        return year;
    };

    public void setCars(ArrayList<Car>cars){
        this.cars=cars;
    };

    public ArrayList<Car> getCars(){
        return this.cars;
    };

    public void dominationCount(List<Car> carList, Car car) {

        int year = car.getYear();
        int price = car.getPrice();
        float mileage = car.getMileage();
        int currdominationCount = 0;

        for (int i = 0; i < carList.size(); i++) {
            int current_year = carList.get(i).getYear();
            int current_price = carList.get(i).getPrice();
            float current_mileage = carList.get(i).getMileage();
            if (car != carList.get(i) && current_price >= price && current_year >= year && current_mileage >= mileage) {
                currdominationCount += 1;
            }
        }
        car.setDominationCount(currdominationCount);
        currdominationCount = 0;

    }

    public int getDominationCount(){
//        int domination_count = 0;
//        for(Car c:this.cars){
//            if(this.year==c.getYear() && this.price==c.getPrice() && this.mileage==c.getMileage()){
//                continue;
//            };
//            if(this.price>=c.getPrice()&&this.year<=c.getYear()&&this.mileage>=c.getMileage()){
//                domination_count ++;
//            };
//        }
        return dominationCount;

    }

    public void setDominationCount(int dominationCount) {
        this.dominationCount = dominationCount;
    }

    public String getName(){
        return this.name;
    };

    public static void main(String[] args){}
}

