package model;

public class Unicorn{
    private int unicornID;
    private String name;
    private String type;
    private double rate;
    private String color;
    private boolean available;
    private boolean healthCheck;
    
   
    public void setUnicornID(int unicornID){
        this.unicornID = unicornID;
    }
    public int getUnicornID(){
        return this.unicornID;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isHealthCheck() {
        return healthCheck;
    }

    public void setHealthCheck(boolean healthCheck) {
        this.healthCheck = healthCheck;
    }
}

