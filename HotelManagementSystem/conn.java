package HotelManagementSystem;

import java.sql.*;

public class conn{
    Connection c;
    Statement s;
    public conn(){  
        try{  
            Class.forName("com.mysql.cj.jdbc.Driver");
            c =DriverManager.getConnection("jdbc:mysql:///hotelmanagementsystem","root","tks@1006@hsl");
            
            s =c.createStatement();
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  
}  

