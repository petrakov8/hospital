package Hospital;

public class Doctors {
    private int telephoneD;
    private String firstname;
    private String lastname;
    private String thirdname;
    private String password;
    private int idspeciality;

    public Doctors(int telephoneD, String firstname, String lastname, String thirdname, String password, int idspeciality) {
        this.telephoneD = telephoneD;
        this.firstname = firstname;
        this.lastname = lastname;
        this.thirdname = thirdname;
        this.password = password;
        this.idspeciality = idspeciality;
    }

    Doctors() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public int getTelephoneD() {
        return telephoneD;
    }

    public void setTelephoneD(int telephoneD) {
        this.telephoneD = telephoneD;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdspeciality() {
        return idspeciality;
    }

    public void setIdspeciality(int idspeciality) {
        this.idspeciality = idspeciality;
    }
    
    
    
}
