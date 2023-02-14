package pl.javastart.task;

import java.util.Objects;

public class Vehicle {
    private String type;
    private String brand;
    private String model;
    private int year;
    private double mileage;
    private String vin;

    public Vehicle(String type, String brand, String model, int year, double mileage, String vin) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.vin = vin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vehicle)) {
            return false;
        }
        Vehicle vehicle = (Vehicle) o;
        return vin.equals(vehicle.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    @Override
    public String toString() {
        return "typ: '" + type + '\'' +
                ", marka: '" + brand + '\'' +
                ", model: '" + model + '\'' +
                ", rocznik: " + year +
                ", przebieg: " + mileage +
                ", nr VIN: '" + vin + '\'';
    }

    public String toStringForFile() {
        return type + ';' +
                brand + ';' +
                model + ';' +
                year + ';' +
                mileage + ';' +
                vin + '\n';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }
}
