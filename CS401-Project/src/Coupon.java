import java.util.Comparator;

public class Coupon {
    protected String provider;
    protected Product product;
    protected double discount;
    protected int expiration;
    protected boolean redeemed;
    protected double disPrice;

    // constructors
    public Coupon(){
        this.provider = "";
        this.product = null;
        this.discount = 0;
        this.expiration = 0;
        this.redeemed = false;
    }

    public Coupon(String provider, Product product, double discount, int expiration){
        this.provider = provider;
        this.product = product;
        this.discount = discount;
        this.expiration = expiration;
        this.redeemed = false;
    }

    // getters and setters
    public String getProvider(){ return this.provider; }
    public void setProvider(String provider){ this.provider = provider; }

    public Product getProduct(){ return this.product; }
    public void setProduct(Product product){ this.product = product; }

    public double getDiscount(){ return this.discount; }
    public void setDiscount(double discount){ this.discount = discount; }

    public int getExpiration(){ return this.expiration; }
    public void setExpiration(int expiration){ this.expiration = expiration; }

    public boolean getStatus(){ return this.redeemed; }
    public void setStatus(boolean redeemed){ this.redeemed = redeemed; }

    public double getDPrice(){ return this.getProduct().getPrice() - (this.getProduct().getPrice() * this.discount / 100); }
    public void setDPrice(double price, double discount){ this.disPrice = price - (price * discount / 100); }

    // toString
    public String toString(){
        String status;
        if (this.getStatus())
            status = "Redeemed";
        else
            status = "Unused";
        String output = String.format("%21s %s %10.2f %% %12d %11s   | %10.2f",
                getProvider(),getProduct(),getDiscount(),getExpiration(),status,getDPrice());
        return output;
    }

    // second toString method for exporting to text file
    public String toString(boolean dummy){
        String status;
        if (this.getStatus())
            status = "T";
        else
            status = "F";
        String output = String.format("%s,%s,%.2f,%.2f,%d,%s",
                getProvider(),getProduct().getName(),getProduct().getPrice(),getDiscount(),getExpiration(),status);
        return output;
    }
}
