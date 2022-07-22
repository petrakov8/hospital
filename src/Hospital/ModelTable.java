package Hospital;


public class ModelTable {
    int idappointment;
    String dateA;
    String start_at;
    String end_at;
    int telephoneC;
    String firstnameC;
    String lastnameC;
    int telephoneD;
    String lastnameD;
    String nameservice;
    int idservice;
     
    public ModelTable(int idappointment, String dateA, String start_at, String end_at, int telephoneC, String firstnameC, String lastnameC, int telephoneD, String lastnameD, String nameservice) {
        this.idappointment = idappointment;
        this.dateA = dateA;
        this.start_at = start_at;
        this.end_at = end_at;
        this.telephoneC = telephoneC;
        this.firstnameC = firstnameC;
        this.lastnameC = lastnameC;
        this.telephoneD = telephoneD;
        this.lastnameD = lastnameD;
        this.nameservice = nameservice;
    }
    
    public int getIdappointment() {
        return idappointment;
    }

    public void setIdappointment(int idappointment) {
        this.idappointment = idappointment;
    }

    public String getDateA() {
        return dateA;
    }

    public void setDateA(String dateA) {
        this.dateA = dateA;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public int getTelephoneC() {
        return telephoneC;
    }

    public void setTelephoneC(int telephoneC) {
        this.telephoneC = telephoneC;
    }

    public String getFirstnameC() {
        return firstnameC;
    }

    public void setFirstnameC(String firstnameC) {
        this.firstnameC = firstnameC;
    }

    public String getLastnameC() {
        return lastnameC;
    }

    public void setLastnameC(String lastnameC) {
        this.lastnameC = lastnameC;
    }

    public int getTelephoneD() {
        return telephoneD;
    }

    public void setTelephoneD(int telephoneD) {
        this.telephoneD = telephoneD;
    }

    public String getLastnameD() {
        return lastnameD;
    }

    public void setLastnameD(String lastnameD) {
        this.lastnameD = lastnameD;
    }

    public String getNameservice() {
        return nameservice;
    }

    public void setNameservice(String nameservice) {
        this.nameservice = nameservice;
    }
    
    public int getIdservice() {
        return idservice;
    }

    public void setIdservice(int idservice) {
        this.idservice = idservice;
    }
    
}
