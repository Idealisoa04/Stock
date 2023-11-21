/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Idealisoa
 */
public class Connect {
    
    public Connection dbConnect() throws Exception{
//         System.out.println("tafiditra");
        String user = "postgres";
        String mdp = "Mdpprom15";
        String nameDatabase = "stock";
        Connection temp = null;
        try {      

            Class.forName("org.postgresql.Driver");
            temp = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+nameDatabase, user, mdp);
            
            temp.setAutoCommit(false);
//             System.out.println("tafiditra");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Connection failed"+e.getLocalizedMessage());
        }
        return temp;
    }
}
