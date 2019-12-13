package model;

public class Customer extends Model{
    private String name;
    private String licenseNo;
    private String phoneNo;
    private boolean verified;
    private String userID;
    private String password;

    // Getter & Setter for customerID
    public Customer(int customerID){
        super(customerID);
    }
    public int getCustomerID(){
        return uniqueID;
    }

    // Getter & Setter for name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Getter & Setter for licenseNo
    public String getLicenseNo() {
        return licenseNo;
    }
    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    // Getter & Setter for phoneNo
    public String getPhoneNo() {
        return phoneNo;
    }
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    // Getter & Setter for verified
    public boolean isVerified() {
        return verified;
    }
    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    // Getter & Setter for userID
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

    // Getter & Setter for password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}