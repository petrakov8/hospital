package Hospital;


public class Services {
    private int idservice;
    private String nameservice;
    private float price;
    
    public Services(int idservice, String nameservice, float price) {
        this.idservice = idservice;
        this.nameservice = nameservice;
        this.price = price;
    }
    
    Services(){
    
    }

    public int getIdservice() {
        return idservice;
    }

    public void setIdservice(int idservice) {
        this.idservice = idservice;
    }

    public String getNameservice() {
        return nameservice;
    }

    public void setNameservice(String nameservice) {
        this.nameservice = nameservice;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
}
