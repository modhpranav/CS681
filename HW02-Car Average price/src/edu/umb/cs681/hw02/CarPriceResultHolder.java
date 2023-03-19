package edu.umb.cs681.hw02;

public class CarPriceResultHolder {

    private int numCarExamined;
    private double average;

    public int getNumCarExamined() {
        return numCarExamined;
    }

    public void setNumCarExamined(int numCarExamined) {
        this.numCarExamined = numCarExamined;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public CarPriceResultHolder() {
        this.numCarExamined = 0;
        this.average = 0.0;
    }

    public void accumulate(double price) {
        this.numCarExamined++;
        this.average = ((this.average * (this.numCarExamined - 1)) + price) / this.numCarExamined;
    }

    public void combine(CarPriceResultHolder other) {
        double totalAverage = (this.average * this.numCarExamined + other.average * other.numCarExamined) /
                (this.numCarExamined + other.numCarExamined);
        this.numCarExamined += other.numCarExamined;
        this.average = totalAverage;
    }

    public double getAverage() {
        return average;
    }
}
