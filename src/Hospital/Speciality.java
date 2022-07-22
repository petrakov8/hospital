package Hospital;


public class Speciality {
    private int idspeciality;
    private String namespeciality;
    
    public Speciality(int idspeciality, String namespeciality) {
        this.idspeciality = idspeciality;
        this.namespeciality = namespeciality;
    }
    
    Speciality(){
    
    }

    public int getIdspeciality() {
        return idspeciality;
    }

    public void setIdspeciality(int idspeciality) {
        this.idspeciality = idspeciality;
    }

    public String getNamespeciality() {
        return namespeciality;
    }

    public void setNamespeciality(String namespeciality) {
        this.namespeciality = namespeciality;
    }
    
}
