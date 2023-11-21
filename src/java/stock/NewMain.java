/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import Connection.Connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author Idealisoa
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
            
            Connect con = new Connect();
            Connection co = con.dbConnect();
            /*List lst = new List();
            Article[] temp = lst.getArticles();
            for(int i=0 ; i<temp.length ; i++){
                System.out.println(temp[i].getNom());
            }*/
            
           String stamp =  "2023-11-04T08:29";
           LocalDateTime localDateTime = LocalDateTime.parse(stamp);
           Timestamp time = Timestamp.valueOf(localDateTime);
           
           //Mouvement mvt = new Mouvement(stamp,"A001","10",id_magasin,null);
           System.out.println(time.toString());
            
           /* Article art = new Article("A002", "Article 2",1,1,co);
            art.setEstdisponible(co);
            System.out.println(art.isEstdisponible());
            
            /*for(int i=0 ; i<stock_dispo.length ; i++){
                System.out.println(stock_dispo[i].getDate());
            }
                   
            /*Magasin[] rep = null;
            ArrayList<Magasin> lists = new ArrayList<Magasin>();
            Str
            ing sql = "select * from magasin ";
            
            PreparedStatement stmt = co.prepareStatement(sql);
            
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                Magasin temp = new Magasin(res.getString("id"),res.getString("nom"));
                lists.add(temp);
            } 
            
            rep = new Magasin[lists.size()];
            for(int i = 0 ; i < lists.size() ; i ++){
                rep[i] = lists.get(i);
            }
            
            for(int i = 0 ; i<rep.length ; i++){
                System.out.println(rep[i].getId());
            }*/
    }
    
}
