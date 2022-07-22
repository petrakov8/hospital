package Hospital;


public class Appointment {
    private int idappointment;
    private String dateA;
    private String start_at;
    private String end_at;
    private int telephoneC;
    private int telephoneD;
    private int idservice;
    
    public Appointment(String dateA, String start_at, String end_at, int telephoneC, int telephoneD, int idservice) {
        this.dateA = dateA;
        this.start_at = start_at;
        this.end_at = end_at;
        this.telephoneC = telephoneC;
        this.telephoneD = telephoneD;
        this.idservice = idservice;
    }
    
    Appointment(){
    
    }
    
    public int getIdA() {
        return idappointment;
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

    public int getTelephoneD() {
        return telephoneD;
    }

    public void setTelephoneD(int telephoneD) {
        this.telephoneD = telephoneD;
    }

    public int getIdservice() {
        return idservice;
    }

    public void setIdservice(int idservice) {
        this.idservice = idservice;
    }
    
}




    
