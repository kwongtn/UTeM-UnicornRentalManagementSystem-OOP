package model;

public class Unicorn extends Model{
	private static int LATEST_ID;
    private String plateNo;
    private String model;
    private double price;
    private boolean auto;
    private boolean usable;
    private int capacity;

    // Getter & Setter for UnicornID
    public int getUnicornID(){
        return uniqueID;
    }
    public Unicorn(){
        super(++LATEST_ID);
    }

    // Getter & Setter for plateNo
    public String getPlateNo(){
        return plateNo;
    }
    public void setPlateNo(String plateNo){
        this.plateNo = plateNo;
    }

    // Getter & Setter for model
    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }

    // Getter & Setter for price
    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    // Getter & Setter for auto
    public boolean isAuto(){
        return auto;
    }
    public void setAuto(boolean auto){
        this.auto = auto;
    }

    // Getter & Setter for usable
    public boolean isUsable(){
        return usable;
    }
    public void setUsable(boolean usable){
        this.usable = usable;
    }

    // Getter & Setter for capacity
    public int getCapacity(){
        return capacity;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
}

