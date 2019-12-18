package model;

public class Unicorn extends Model{
    private static int LATEST_ID;
    private String name;
    private String type;
    private double rate;
    private String color;
    private boolean available;
    private boolean healthCheck;
    
    public Unicorn(){
        super(++LATEST_ID);
    }

    public int getUnicornID(){
        return super.getUniqueID();
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the rate
     */
    public double getRate() {
        return rate;
    }

    /**
     * @param rate the rate to set
     */
    public void setRate(double rate) {
        this.rate = rate;
    }

    /**
     * @return Unicorn color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color Unicorn Color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the available
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * @param available the available to set
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * @return the healthCheck
     */
    public boolean isHealthCheck() {
        return healthCheck;
    }

    /**
     * @param healthCheck the healthCheck to set
     */
    public void setHealthCheck(boolean healthCheck) {
        this.healthCheck = healthCheck;
    }
}

