package model;

public class Rental extends Model{
    private Unicorn unicorn;
    private Customer customer;
    private long startDate;
    private long endDate;
    private double depositPaid;
    private double additionalCharges;
    private double incurredCharges;

    private static int LATEST_ID;

    public Rental(){
        super(++LATEST_ID);
    }
    public int getRentalID(){
        return super.getUniqueID();
    }

    // Getter & Setter for unicorn
    public Unicorn getunicorn(){
        return unicorn;
    }
    public void setunicorn(Unicorn unicorn) {
        this.unicorn = unicorn;
    }
    public int getUnicornID(){
        return unicorn.getUnicornID();
    }

    // Getter & Setter for customer
    public Customer getcustomer() {
        return customer;
    }
    public void setcustomer(Customer customer) {
        this.customer = customer;
    }
    public int getCustomerID(){
        return customer.getCustomerID();
    }

    // Getter & Setter for dates
    public long getStartDate() {
        return startDate;
    }
    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }
    public long getEndDate() {
        return endDate;
    }
    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    // Getter & setter for depositPaid
    public double getDepositPaid() {
        return depositPaid;
    }
    public void setDepositPaid(double depositPaid) {
        this.depositPaid = depositPaid;
    }

    // Getter & setter for additional and incurred charges
    public double getAdditionalCharges() {
        return additionalCharges;
    }
    public void setAdditionalCharges(double additionalCharges) {
        this.additionalCharges = additionalCharges;
    }
    public double getIncurredCharges() {
        return incurredCharges;
    }
    public void setIncurredCharges(double incurredCharges) {
        this.incurredCharges = incurredCharges;
    }
    

    public double currentIncurredCharges(){
        return ((this.endDate - this.startDate) * unicorn.getRate());
    }


}