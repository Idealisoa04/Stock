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

/**
 *
 * @author Idealisoa
 */
public class TypeStock {
    int id;
    String nom;
    String cnf;

    public TypeStock(int id, String nom) throws Exception {
        try{
            this.setId(id);
            this.setNom(nom);
        }catch(Exception e){
            throw e;
        }
    }

    public TypeStock() {
    }

    public void setcnf(Connection co) throws Exception{
        boolean estouvert = false;
        try{
            if(co == null){
                estouvert = true;
                Connect con = new Connect();
                co = con.dbConnect();
            }
            String sql = "select * from stock_cnf where idtype_stock=?";
            
            PreparedStatement stmt = co.prepareStatement(sql);
            stmt.setInt(1,this.getId());
            
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                this.setCnf(res.getString("order"));
            } 
        }
        catch(Exception e){
            throw e;
        }
        finally{
            if(estouvert == true){
                co.close();
            }
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception{
        this.id = id;
        try{
            this.setcnf(null);
        }catch(Exception e){
            throw e;
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCnf() {
        return cnf;
    }

    public void setCnf(String cnf) {
        this.cnf = cnf;
    }
    
    
}
