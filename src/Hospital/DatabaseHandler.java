package Hospital;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseHandler extends Config {
    
    Connection dbConnection;
    
    public Connection getDbConnection() 
            throws ClassNotFoundException, SQLException{
        
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName;
        
        Class.forName("com.mysql.jdbc.Driver");
        
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        
        return dbConnection;  
    }
    
    public void signUpDoctor (Doctors doctor){
        String insert = "INSERT INTO " + Const.DOCTOR_TABLE + "(" +
                Const.DOCTOR_TELEPHONE + Const.DOCTOR_FIRSTNAME + 
                Const.DOCTOR_LASTNAME + Const.DOCTOR_LASTNAME + 
                Const.DOCTOR_PASSWORD + Const.DOCTOR_ID_SPECIALITY +
                "VALUES(?,?,?,?,?,?)";
        
        try {
        PreparedStatement prST = getDbConnection().prepareStatement(insert);
        prST.setInt(1,doctor.getTelephoneD());
        prST.setString(2, doctor.getFirstname());
        prST.setString(3, doctor.getLastname());
        prST.setString(4, doctor.getThirdname());
        prST.setString(5, doctor.getPassword());
        prST.setInt(6, doctor.getIdspeciality());
       
            prST.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }
    
    public ObservableList<ModelTable> getDatainfo(){
        //Connection conn = getDbConnection();
        ObservableList<ModelTable> list = FXCollections.observableArrayList();
        String select = "SELECT appointment.idappointment, appointment.dateA, appointment.start_at, appointment.end_at, appointment.telephoneC, clients.firstname, clients.lastname, appointment.telephoneD ,doctors.lastnameD, services.nameservice" +
                " FROM appointment, doctors, services, clients" +
                " WHERE appointment.telephoneC = clients.telephoneC AND appointment.telephoneD = doctors.telephoneD AND appointment.idservice = services.idservice";
        try{
            
            PreparedStatement prST = getDbConnection().prepareStatement(select);  
            ResultSet rs = prST.executeQuery();
            
            while(rs.next()){
                list.add(new ModelTable(Integer.parseInt(rs.getString("idappointment")),rs.getString("dateA"), rs.getString("start_at"), rs.getString("end_at"), 
                Integer.parseInt(rs.getString("telephoneC")),rs.getString("firstname"),rs.getString("lastname"),Integer.parseInt(rs.getString("telephoneD")),
                rs.getString("lastnameD"),rs.getString("nameservice")));            
            }         
        }catch(Exception e){  
        }
        return list;
    }
    
    public ResultSet getClient(String s){
        ResultSet resSet = null;
        
        String select = "SELECT " + Const.CLIENT_TELEPHONE +" FROM " + Const.CLIENT_TABLE + 
                " WHERE " + Const.CLIENT_TELEPHONE + " = " + "'"+s+"'";
        
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(select);
            resSet = prST.executeQuery();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resSet;        
    }
    
    public ResultSet getDoctor(Doctors doctor){
        ResultSet resSet = null;    
        
        String select ="SELECT * FROM " + Const.DOCTOR_TABLE + " WHERE " + 
                Const.DOCTOR_TELEPHONE + "=? AND " + Const.DOCTOR_PASSWORD + "=?";
        
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(select);
            prST.setInt(1,doctor.getTelephoneD());
            prST.setString(2, doctor.getPassword());
       
            resSet = prST.executeQuery();//получить с БД и присваиваем переменной resSet
        } catch (SQLException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return resSet; //возвращаем пользователя
    }
    
    public void createApp (Appointment app){
        String insert = "INSERT INTO " + Const.APPOINTMENT_TABLE + "(" +
                Const.APPOINTMENT_DATE + "," + Const.APPOINTMENT_START + "," +
                Const.APPOINTMENT_END  + "," + Const.APPOINTMENT_TELEPHONE_CLIENT + "," + 
                Const.APPOINTMENT_TELEPHONE_DOCTOR + "," + Const.APPOINTMENT_SERVICE_ID + ")" +
                "VALUES(?,?,?,?,?,?)";
        
        try {
        PreparedStatement prST = getDbConnection().prepareStatement(insert);
        prST.setString(1, app.getDateA());
        prST.setString(2, app.getStart_at());
        prST.setString(3, app.getEnd_at());
        prST.setInt(4, app.getTelephoneC());
        prST.setInt(5, app.getTelephoneD());
        prST.setInt(6, app.getIdservice());
       
            prST.executeUpdate();
        } catch (SQLException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
    }
    
    public void CreateClients (Clients cl){
        String insert = "INSERT INTO " + Const.CLIENT_TABLE + "(" +
                Const.CLIENT_TELEPHONE + "," + Const.CLIENT_FIRSTNAME + "," +
                Const.CLIENT_LASTNAME + "," + Const.CLIENT_THIRDNAME + ")" +
                "VALUES(?,?,?,?)";
        
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(insert);
            prST.setInt(1, cl.getTelephoneC());
            prST.setString(2, cl.getFristname());
            prST.setString(3, cl.getLastname());
            prST.setString(4, cl.getThirdname());
            
            prST.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
    
    public ResultSet getApp(Appointment app){
        ResultSet resSet = null;    
        
        String select ="SELECT * FROM " + Const.APPOINTMENT_TABLE + " WHERE " + 
                Const.APPOINTMENT_ID + "=?";
        
        try {
            PreparedStatement prST = getDbConnection().prepareStatement(select);
            prST.setInt(1, app.getIdA());
       
            resSet = prST.executeQuery();//получить с БД и присваиваем переменной resSet
        } catch (SQLException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return resSet; //возвращаем пользователя
    }
    
    public ResultSet fillCombo_ser(Services service){
        ResultSet resSet = null;    
        
        String query = "SELECT " + Const.SERVICES_NAME + 
                " FROM " + Const.SERVICES_TABLE;
        try{
        PreparedStatement prST = getDbConnection().prepareStatement(query);
        resSet = prST.executeQuery();
        
        
        
        //prST.close();
        //resSet.close();
        }catch (SQLException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        
        return resSet;
    }
    
    public ResultSet fillCombo_doc1(String s){
        ResultSet resSet1 = null;
        //ResultSet resSet2 = null;
        //ResultSet resSet3 = null;
        
        // String q1 = null;
        //String q2;
        
        String query1 = "SELECT " + Const.SERVICES_ID +
                " FROM " + Const.SERVICES_TABLE +
                " WHERE " + Const.SERVICES_NAME + " = " + "'"+s+"'";
        
        try{
        PreparedStatement prST1 = getDbConnection().prepareStatement(query1);
        resSet1 = prST1.executeQuery();
        
        //prST.close();
        //resSet.close();
        }catch (SQLException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
   
        return resSet1;
        
    }
    
    public ResultSet fillCombo_doc2(String s){
        ResultSet resSet1 = null;
        //ResultSet resSet2 = null;
        //ResultSet resSet3 = null;
        
        // String q1 = null;
        //String q2;
        
        String query1 = "SELECT " + Const.SPECIALITY_ID +
                " FROM " + Const.SPSV_TABLE +
                " WHERE " + Const.SPSV_ID_SV + " = " + "'"+s+"'";
        
        try{
        PreparedStatement prST1 = getDbConnection().prepareStatement(query1);
        resSet1 = prST1.executeQuery();
        
        //prST.close();
        //resSet.close();
        }catch (SQLException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
   
        return resSet1;
        
    }
    
    public ResultSet fillCombo_doc3(String s){
        ResultSet resSet1 = null;
        //ResultSet resSet2 = null;
        //ResultSet resSet3 = null;
        
        // String q1 = null;
        //String q2;
        
        String query1 = "SELECT " + Const.DOCTOR_FIRSTNAME + ", "+ Const.DOCTOR_LASTNAME +
                " FROM " + Const.DOCTOR_TABLE +
                " WHERE " + Const.DOCTOR_ID_SPECIALITY+ " = " + "'"+s+"'";
        
        try{
        PreparedStatement prST1 = getDbConnection().prepareStatement(query1);
        resSet1 = prST1.executeQuery();
        
        //prST.close();
        //resSet.close();
        }catch (SQLException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
   
        return resSet1;
        
    }
    
    public ResultSet find_doc(String s){
        ResultSet resSet1 = null;
        //ResultSet resSet2 = null;
        //ResultSet resSet3 = null;
        
        // String q1 = null;
        //String q2;
        
        String query1 = "SELECT " + Const.DOCTOR_TELEPHONE +
                " FROM " + Const.DOCTOR_TABLE +
                " WHERE " + Const.DOCTOR_ID_SPECIALITY+ " = " + "'"+s+"'";
        
        try{
        PreparedStatement prST1 = getDbConnection().prepareStatement(query1);
        resSet1 = prST1.executeQuery();
        
        //prST.close();
        //resSet.close();
        }catch (SQLException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
   
        return resSet1;
        
    }
    
    public void deleteApp(int id){
        String query = "DELETE FROM " + Const.APPOINTMENT_TABLE +
                " WHERE " + Const.APPOINTMENT_ID + " = " +id;
        
        try {
            PreparedStatement prST1 = getDbConnection().prepareStatement(query);
            prST1.execute();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
