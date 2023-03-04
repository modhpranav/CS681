package edu.umb.cs681.hw03;

import java.util.Date;
public class Summary {
    private double Open;
    private double Close;
    private double High;
    private double Low;
    private Date date;

    public Summary(Date date, double Open, double Close, double High, double Low){
        this.date = date;
        this.Open = Open;
        this.Close = Close;
        this.High = High;
        this.Low = Low;
    }

    public Date getDate(){
        return this.date;
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
