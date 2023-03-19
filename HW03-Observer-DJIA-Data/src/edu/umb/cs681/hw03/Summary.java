package edu.umb.cs681.hw03;

import java.util.Date;
public class Summary {
    private double Open;
    private double Close;
    private double High;
    private double Low;

    public Summary(double Open, double High, double Low, double Close){
        this.Open = Open;
        this.Close = Close;
        this.High = High;
        this.Low = Low;
    }

    public double getOpen(){
        return this.Open;
    }

    public double getClose(){
        return this.Close;
    }

    public double getHigh(){
        return this.High;
    }

    public double getLow(){
        return this.Low;
    }
}
