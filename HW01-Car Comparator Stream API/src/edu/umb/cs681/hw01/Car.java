package edu.umb.cs681.hw01;

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


    public int getDominationCount(){
        return dominationCount;
    }

    public void setDominationCount(ArrayList<Car> carList){
        this.dominationCount = 0;
        for (Car car : carList) {
            if(car.getYear() >= this.getYear() && car.getMileage() <= this.getMileage() && car.getPrice() <= this.getPrice()) {
                if(car.getYear() > this.getYear() || car.getMileage() < this.getMileage() || car.getPrice() < this.getPrice()) {
                    dominationCount++;
                }
            }
        }
    }

    public String getName(){
        return this.name;
    };

    public static void main(String[] args){}

}