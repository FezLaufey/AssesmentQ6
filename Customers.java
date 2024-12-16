public class Customers {
    private int customer_id;
    private String short_name;
    private String full_name;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String postalCode;

    // Constructors, Getters, and Setters
     // Default Constructor
     public Customers() {
    }

    // Parameterized Constructor
    public Customers(int customer_id, String short_name, String full_name, String address1, 
                     String address2, String address3, String city, String postalCode) {
        this.customer_id = customer_id;
        this.short_name = short_name;
        this.full_name = full_name;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.city = city;
        this.postalCode = postalCode;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customer_id;
    }

    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getShortName() {
        return short_name;
    }

    public void setShortName(String short_name) {
        this.short_name = short_name;
    }

    public String getFullName() {
        return full_name;
    }

    public void setFullName(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    // toString method for debugging or display purposes
    @Override
    public String toString() {
        return "Customers{" +
                "customer_id=" + customer_id +
                ", short_name='" + short_name + '\'' +
                ", full_name='" + full_name + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", address3='" + address3 + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}