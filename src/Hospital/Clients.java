package Hospital;

public class Clients {
    private int telephoneC;
    private String fristname;
    private String lastname;
    private String thirdname;
    
    public Clients(int telephoneC, String fristname, String lastname, String thirdname) {
        this.telephoneC = telephoneC;
        this.fristname = fristname;
        this.lastname = lastname;
        this.thirdname = thirdname;
    }
    
    Clients(){
    
    }
    
    public int getTelephoneC() {
        return telephoneC;
    }

    public void setTelephoneC(int telephoneC) {
        this.telephoneC = telephoneC;
    }

    public String getFristname() {
        return fristname;
    }

    public void setFristname(String fristname) {
        this.fristname = fristname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getThirdname() {
        return thirdname;
    }

    public void setThirdname(String thirdname) {
        this.thirdname = thirdname;
    }
}
