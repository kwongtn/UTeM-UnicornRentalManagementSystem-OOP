package model;

public class Customer{
    private int customerID;
    private String name;
    private String phoneNo;
    private int unicornLicenseID;

    public void setCustomerID(int customerID){
        this.customerID = customerID;
    }
    public int getCustomerID(){
        return customerID;
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