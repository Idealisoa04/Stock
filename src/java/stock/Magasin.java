/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock;

import Connection.Connect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 *
 * @author Idealisoa
 */
public class Magasin {
    String id;
    String nom;
    Stock[] stock_dispo;

    public Magasin(String id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Magasin() {
    }
    
    /*public Stock[] getStockDispo(Connection co) throws Exception{
        Stock[] rep = null;
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            
            String sql = "select * from unite where id=?";
            
            PreparedStatement stmt = co.prepareStatement(sql);
            stmt.setInt(1,this.getIdunite());
            ResultSet res = stmt.executeQuery();
            Unite temp = null;
            if (res.next()) {
                temp = new Unite(res.getInt("id"),res.getString("nom"));
            } 
            this.setUnite(temp);
            
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(estouvert == true){
                co.close();
            }
        }
        return rep;
    }*/
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Stock[] getStock_dispo() {
        return stock_dispo;
    }

    public void setStock_dispo(Stock[] stock_dispo) {
        this.stock_dispo = stock_dispo;
    }
    
}
