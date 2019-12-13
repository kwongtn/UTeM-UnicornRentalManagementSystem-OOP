package model;

public class Customer extends Model{
    private static int LATEST_ID;
    private String name;
    private String phoneNo;
    private int unicornLicenseID;

    // Getter & Setter for customerID
    public Customer(int customerID){
        super(++LATEST_ID);
    }
    public int getCustomerID(){
        return super.getUniqueID();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return the unicornLicenseID
     */
    public int getUnicornLicenseID() {
        return unicornLicenseID;
    }

    /**
     * @param unicornLicenseID the unicornLicenseID to set
     */
    public void setUnicornLicenseID(int unicornLicenseID) {
        this.unicornLicenseID = unicornLicenseID;
    }
    

}