/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gymp;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 * @author Dr. Kamruddin Nur
 */
public class DBConnect {

    private Connection con;
    private Statement st;
    private ResultSet rs;

    //constructors
    public DBConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Step-1: get a connection to the db
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/login", 
                    "root", "");
            System.out.println("DB Connect Successful");
            //Step-2: create a statement
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }

    //parameterized constructor
    public DBConnect(String url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //Step-1: get a connection to the db
            con = DriverManager.getConnection(url, user, password);
            //Step-2: create a statement
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
  
    public ResultSet getResultSet() {
        try {
            String query = "select member, employee, equipment from login;";
            //Step-3: Execute SQL query
            rs = st.executeQuery(query);
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        return rs;
    }

    public void printDataInConole(ResultSet rs) {
        try {
            while (rs.next()) {
                String member = rs.getString("member");
                String employee = rs.getString("employee");
                float equipment = rs.getFloat("equipment");
               
                System.out.println("\nMember: " + member + "\n"
                        + "Employee: " + employee + "\n"
                        + "Equipment: " + equipment);
            }//end of while
        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
    
    public void insertIntoDB(ArrayList row) {
        try {
            String query = "insert into login(member, employee, equipment) values('" +
                    row.get(0) + "','" + row.get(1) + "','" + row.get(2) +"');";
            System.out.println(query);
            //Step-3: Execute SQL query
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Gym Info Added Successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Insert into Database Exception: " + e);
            System.out.println(e);
        }
         
    }
      public void insertMemberDB(ArrayList row) {
        try {
            String query = "insert into memberinfo(id, name, contact info,fee) values('" +
                    row.get(0) + "','" + row.get(1) + "','" + row.get(2) +"','"+ row.get(3)+"');";
            System.out.println(query);
            //Step-3: Execute SQL query
            st.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Member Info Added Successfully.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Insert into Database Exception: " + e);
            System.out.println(e);
        }
         
    }
    
}
