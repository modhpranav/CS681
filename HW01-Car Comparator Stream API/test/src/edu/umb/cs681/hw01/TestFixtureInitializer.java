package edu.umb.cs681.hw01;

import java.util.ArrayList;

public class TestFixtureInitializer {

    public ArrayList<Car> createCarData(){
        ArrayList<Car> cardata = new ArrayList<>();
        cardata.add(new Car("Hyundai-1",2000, 8000, 53));
        cardata.add(new Car("Honda-2", 2001, 9000, 67));
        cardata.add(new Car("Maruti-3", 2002, 10000, 98));
        cardata.add(new Car("Mercedes-4",2004,20000,72));
        return cardata;
    };
}
